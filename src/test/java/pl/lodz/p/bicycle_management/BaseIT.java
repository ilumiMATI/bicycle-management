package pl.lodz.p.bicycle_management;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService;
import pl.lodz.p.bicycle_management.availability.command.infrastructure.storage.JpaBicycleAvailabilityRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleService;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.JpaBicycleRepository;
import pl.lodz.p.bicycle_management.clock.Clock;
import pl.lodz.p.bicycle_management.clock.ClockService;
import pl.lodz.p.bicycle_management.payment.command.application.PaymentService;
import pl.lodz.p.bicycle_management.payment.command.infrastructure.storage.JpaUserWalletRepository;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletFacade;
import pl.lodz.p.bicycle_management.rental.command.application.RentalService;
import pl.lodz.p.bicycle_management.rental.command.infrastructure.storage.JpaUserRentalsRepository;
import pl.lodz.p.bicycle_management.report.domain.RentalPaymentReportService;
import pl.lodz.p.bicycle_management.report.domain.RentalReportService;
import pl.lodz.p.bicycle_management.report.infrastructure.storage.JpaRentalPaymentReportRepository;
import pl.lodz.p.bicycle_management.report.infrastructure.storage.JpaRentalReportRepository;
import pl.lodz.p.bicycle_management.security.JWTUtil;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserService;
import pl.lodz.p.bicycle_management.user.infrastructure.storage.JpaUserRepository;

@ActiveProfiles("it")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BicycleManagementApplication.class
)
@ExtendWith(SpringExtension.class)
@Import(TestClockConfiguration.class)
public class BaseIT {

    @Autowired
    protected Environment environment;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RentalService rentalService;

    @Autowired
    protected BicycleService bicycleService;

    @Autowired
    protected UserWalletFacade userWalletFacade;

    @Autowired
    protected PaymentService paymentService;

    @Autowired
    protected RentalReportService rentalReportService;

    @Autowired
    protected RentalPaymentReportService rentalPaymentReportService;

    @Autowired
    protected Clock clock;

    @Autowired
    protected AvailabilityService availabilityService;

    protected BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected JWTUtil jwtUtil;

    @Autowired
    private ServerPortService serverPortService;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaBicycleRepository jpaBicycleRepository;

    @Autowired
    private JpaUserRentalsRepository jpaUserRentalsRepository;

    @Autowired
    private JpaUserWalletRepository jpaUserWalletRepository;

    @Autowired
    private JpaRentalReportRepository jpaRentalReportRepository;

    @Autowired
    private JpaRentalPaymentReportRepository jpaRentalPaymentReportRepository;

    @Autowired
    private JpaBicycleAvailabilityRepository jpaBicycleAvailabilityRepository;


    @BeforeEach
    void init() {
        jpaBicycleRepository.deleteAll();
        jpaBicycleAvailabilityRepository.deleteAll();
        jpaUserRepository.deleteAll();
        jpaUserRentalsRepository.deleteAll();
        jpaUserWalletRepository.deleteAll();
        jpaRentalReportRepository.deleteAll();
        jpaRentalPaymentReportRepository.deleteAll();
    }

    protected String localUrl(String endpoint) {
        int port = serverPortService.getPort();
        return "http://localhost:" + port + endpoint;
    }

    protected String getAccessTokenForUser(User user) {
        String token = jwtUtil.issueToken(user.getEmail(), "ROLE_" + user.getRole());
        return "Bearer " + token;
    }

    protected <T, U> ResponseEntity<U> callHttpMethod(
            HttpMethod httpMethod,
            String url,
            String accessToken,
            T body,
            Class<U> mapToObject
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, accessToken);
        headers.add(HttpHeaders.ACCEPT, "application/json");
        HttpEntity<T> requestEntity;
        if (body == null) {
            requestEntity = new HttpEntity<>(headers);
        } else {
            requestEntity = new HttpEntity<>(body, headers);
        }
        return restTemplate.exchange(
                localUrl(url),
                httpMethod,
                requestEntity,
                mapToObject
        );
    }

}
