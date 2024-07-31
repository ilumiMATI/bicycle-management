package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import pl.lodz.p.bicycle_management.BaseIT;
import pl.lodz.p.bicycle_management.TestUserFactory;
import pl.lodz.p.bicycle_management.rental.application.RentService;
import pl.lodz.p.bicycle_management.rental.domain.PageRent;
import pl.lodz.p.bicycle_management.rental.domain.RentNumber;
import pl.lodz.p.bicycle_management.rental.domain.UserId;
import pl.lodz.p.bicycle_management.user.application.UserService;
import pl.lodz.p.bicycle_management.user.domain.User;

import static org.junit.jupiter.api.Assertions.*;

public class RentControllerTest extends BaseIT {
    @Autowired
    RentService rentService;
    @Autowired
    UserService userService;

    @Test
    void admin_should_be_able_to_save_rent() {
        // given
        User admin = TestUserFactory.createAdmin();
        userService.save(admin);
        String token = getAccessTokenForUser(admin);

        RentDto rentDto = new RentDto(null, "TEST11",1,1, null);

        // when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents",
                token,
                rentDto,
                RentDto.class);
        RentDto responseRentDto = (RentDto) response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rentDto.rentNumber(), responseRentDto.rentNumber());
        assertEquals(rentDto.userId(), responseRentDto.userId());
        assertEquals(rentDto.bicycleId(), responseRentDto.bicycleId());
    }

    @Test
    void admin_should_be_able_to_get_rents() {
        // given
        User admin = TestUserFactory.createAdmin();
        userService.save(admin);
        String token = getAccessTokenForUser(admin);

        // when
        var response = callHttpMethod(
                HttpMethod.GET,
                "/api/v1/rents",
                token,
                null,
                PageRent.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void user_should_not_be_able_to_get_rents() {
        // given
        User user = TestUserFactory.createUser();
        userService.save(user);
        String token = getAccessTokenForUser(user);

        // when
        var response = callHttpMethod(
                HttpMethod.GET,
                "/api/v1/rents",
                token,
                null,
                PageRent.class);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
