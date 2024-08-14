package pl.lodz.p.bicycle_management.rental.api;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import pl.lodz.p.bicycle_management.BaseIT;
import pl.lodz.p.bicycle_management.TestBicycleFactory;
import pl.lodz.p.bicycle_management.TestUserFactory;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.payment.command.application.WalletDepositCommand;
import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletDto;
import pl.lodz.p.bicycle_management.rental.command.application.RentCommand;
import pl.lodz.p.bicycle_management.rental.command.application.ReturnCommand;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsNotFoundException;
import pl.lodz.p.bicycle_management.report.domain.PageRentalPaymentReport;
import pl.lodz.p.bicycle_management.report.domain.PageRentalReport;
import pl.lodz.p.bicycle_management.user.api.UserDto;
import pl.lodz.p.bicycle_management.user.domain.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class RentalControllerTest extends BaseIT {

    @Test
    void user_rentals_should_be_created_when_user_is_created() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);
        UserDto newUser = new UserDto(null,"user@bestusers.com","BestUser","12341234", "USER");

        // when
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/users",
                token,
                newUser,
                UserDto.class);

        // then
        try {
            UserRentals userRentals = rentalService.findByUserId(UserId.of(response.getBody().id()));
            assertEquals(0,userRentals.getBicycles().size());
        } catch (UserRentalsNotFoundException e) {
            fail();
        }
    }

    @Test
    void user_rentals_should_be_deleted_when_user_is_deleted() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);
        UserDto newUser = new UserDto(null,"user@bestusers.com","BestUser","12341234", "USER");

        // when
        var response = callHttpMethod(HttpMethod.DELETE,
                "/api/v1/users/" + adminId.toString(),
                token,
                null,
                Void.class);

        // then
        try {
            UserRentals userRentals = rentalService.findByUserId(UserId.of(adminId));
            fail();
        } catch (UserRentalsNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void admin_should_be_able_to_rent_bicycle_when_his_wallet_has_less_than_minimal_funds() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);

        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),adminId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.OK,response.getStatusCode());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(adminId));
        assertEquals(1,userRentals.getBicycles().size());
        assertEquals(bicycle.getBicycleNumber().asString(), userRentals.getBicycles().get(0).asString());
    }

    @Test
    void user_should_not_be_able_to_rent_bicycle_when_his_wallet_has_less_than_minimal_funds() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.PAYMENT_REQUIRED,response.getStatusCode());
    }

    @Test
    void user_should_be_able_to_rent_bicycle_when_his_wallet_has_at_least_minimal_funds() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));

        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.OK,response.getStatusCode());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(userId));
        assertEquals(1,userRentals.getBicycles().size());
        assertEquals(bicycle.getBicycleNumber().asString(), userRentals.getBicycles().get(0).asString());
    }

    @Test
    void user_should_be_able_to_rent_bicycle_for_another_user_even_when_his_wallet_has_funds() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        User anotherUser = TestUserFactory.createUser();
        Integer anotherUserId = userService.save(anotherUser).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),anotherUserId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED,response.getStatusCode());
    }

    @Test
    void admin_should_be_able_to_rent_bicycle_for_another_user_even_when_his_wallet_has_no_funds() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        User anotherUser = TestUserFactory.createUser();
        Integer anotherUserId = userService.save(anotherUser).getId();
        String token = getAccessTokenForUser(admin);

        paymentService.depositToWallet(new WalletDepositCommand(adminId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),anotherUserId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.OK,response.getStatusCode());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(anotherUserId));
        assertEquals(1,userRentals.getBicycles().size());
        assertEquals(bicycle.getBicycleNumber().asString(), userRentals.getBicycles().get(0).asString());
    }

    @Test
    void already_rented_bicycles_cannot_be_rented() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        User anotherUser = TestUserFactory.createUser();
        Integer anotherUserId = userService.save(anotherUser).getId();
        String token = getAccessTokenForUser(user);
        String anotherToken = getAccessTokenForUser(anotherUser);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        paymentService.depositToWallet(new WalletDepositCommand(anotherUserId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        RentCommand anotherRentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),anotherUserId);
        var anotherResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                anotherToken,
                anotherRentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(HttpStatus.CONFLICT,anotherResponse.getStatusCode());
        UserRentals anotherUserRentals = rentalService.findByUserId(UserId.of(anotherUserId));
        assertEquals(0,anotherUserRentals.getBicycles().size());
    }

    @Test
    void nonexistent_bicycles_cannot_be_rented() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));

        // when
        RentCommand rentCommand = new RentCommand("nonexistent",userId);
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        // then
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    // RETURN RELATED

    @Test
    void user_should_be_able_to_return_bicycle_that_was_rented_by_him() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);
        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(userId));
        assertEquals(0,userRentals.getBicycles().size());
    }

    @Test
    void user_should_not_be_able_to_return_bicycle_that_he_did_not_rent() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED,returnResponse.getStatusCode());
    }

    @Test
    void user_should_not_be_able_to_return_another_users_bicycle() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        User anotherUser = TestUserFactory.createUser();
        Integer anotherUserId = userService.save(anotherUser).getId();
        String anotherToken = getAccessTokenForUser(anotherUser);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);
        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                anotherToken,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED,returnResponse.getStatusCode());
    }

    @Test
    void admin_should_be_able_to_return_another_users_bicycle() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String adminToken = getAccessTokenForUser(admin);

        paymentService.depositToWallet(new WalletDepositCommand(userId,10.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);
        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                adminToken,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(userId));
        assertEquals(0,userRentals.getBicycles().size());
    }

    @Test
    void user_should_be_charged_after_return_of_bicycle() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,50.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        clock.addMinutes(10); // time change

        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());
        UserWalletDto userWalletDto = userWalletFacade.findByUserId(userId);
        assertTrue(userWalletDto.money().compareTo(BigDecimal.valueOf(50.00)) < 0);
        UserRentals userRentals = rentalService.findByUserId(UserId.of(userId));
        assertEquals(0,userRentals.getBicycles().size());
    }

    @Test
    void admin_should_not_be_charged_after_return_of_bicycle() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);

        paymentService.depositToWallet(new WalletDepositCommand(adminId,50.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),adminId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        clock.addMinutes(10); // time change

        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),adminId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        // then
        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());
        UserWalletDto userWalletDto = userWalletFacade.findByUserId(adminId);
        assertEquals(BigDecimal.valueOf(50.00).setScale(2, RoundingMode.HALF_UP), userWalletDto.money());
        UserRentals userRentals = rentalService.findByUserId(UserId.of(adminId));
        assertEquals(0,userRentals.getBicycles().size());
    }

    // REPORTING TESTS

    @Test
    void returning_bicycle_should_generate_rental_report() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,50.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        clock.addMinutes(10); // time change

        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());

        PageRentalReport pageRentalReport = rentalReportService.findAllByUserId(
                pl.lodz.p.bicycle_management.report.domain.UserId.of(userId),
                PageRequest.of(0,2));

        assertEquals(1,pageRentalReport.getTotalElements());
        assertEquals(bicycle.getBicycleNumber().asString(),pageRentalReport.getReports().get(0).getBicycleNumber().asString());
        assertEquals(userId,pageRentalReport.getReports().get(0).getUserId().value());
    }

    @Test
    void returning_bicycle_should_generate_rental_payment_report() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        paymentService.depositToWallet(new WalletDepositCommand(userId,50.00));
        Bicycle bicycle = bicycleService.save(TestBicycleFactory.createBicycle());

        // when
        RentCommand rentCommand = new RentCommand(bicycle.getBicycleNumber().asString(),userId);
        var rentResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/rent",
                token,
                rentCommand,
                RentCommand.class);

        clock.addMinutes(10); // time change

        ReturnCommand returnCommand = new ReturnCommand(bicycle.getBicycleNumber().asString(),userId);
        var returnResponse = callHttpMethod(HttpMethod.POST,
                "/api/v1/rentals/return",
                token,
                returnCommand,
                ReturnCommand.class);

        assertEquals(HttpStatus.OK,rentResponse.getStatusCode());
        assertEquals(HttpStatus.OK,returnResponse.getStatusCode());

        PageRentalPaymentReport pageRentalPaymentReport = rentalPaymentReportService.findAllByUserId(
                pl.lodz.p.bicycle_management.report.domain.UserId.of(userId),
                PageRequest.of(0,2));

        assertEquals(1,pageRentalPaymentReport.getTotalElements());
        assertEquals(userId,pageRentalPaymentReport.getReports().get(0).getUserId().value());
        UserWalletDto userWalletDto = userWalletFacade.findByUserId(userId);
        Money paid = Money.of(50.00).subtract(Money.of(userWalletDto.money()));
        assertEquals(paid.amount(),pageRentalPaymentReport.getReports().get(0).getPaidPrice().amount());
    }
}
