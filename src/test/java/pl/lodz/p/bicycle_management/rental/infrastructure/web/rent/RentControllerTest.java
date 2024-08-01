package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import pl.lodz.p.bicycle_management.BaseIT;
import pl.lodz.p.bicycle_management.TestUserFactory;
import pl.lodz.p.bicycle_management.rental.application.RentService;
import pl.lodz.p.bicycle_management.rental.domain.*;
import pl.lodz.p.bicycle_management.user.application.UserService;
import pl.lodz.p.bicycle_management.user.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentControllerTest extends BaseIT {
    @Autowired
    RentService rentService;
    @Autowired
    UserService userService;

    @Test
    void admin_should_be_able_to_create_multiple_rents_for_himself() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);

        CreateCommand cmd = new CreateCommand(
                adminId,
                Arrays.asList(1,2,3)
        );

        //when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents/create",
                token,
                cmd,
                CreateCommand.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PageRent pageRent = rentService.findAll(Pageable.ofSize(3));
        int i = 1;
        for(Rent rent : pageRent.getRents()) {
            assertEquals(adminId,rent.getUserId().id());
            assertEquals(i,rent.getBicycleId().id());
            i++;
        }
    }

    @Test
    void admin_should_be_able_to_create_multiple_rents_for_different_user() {
        // given
        User user = TestUserFactory.createUser();
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(admin);
        assertNotEquals(adminId, userId);

        CreateCommand cmd = new CreateCommand(
                userId,
                Arrays.asList(1,2,3)
        );

        //when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents/create",
                token,
                cmd,
                CreateCommand.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PageRent pageRent = rentService.findAll(Pageable.ofSize(3));
        int i = 1;
        for(Rent rent : pageRent.getRents()) {
            assertEquals(userId,rent.getUserId().id());
            assertEquals(i,rent.getBicycleId().id());
            i++;
        }
    }

    @Test
    void user_should_not_be_able_to_create_rent_for_different_user() {
        // given
        User user = TestUserFactory.createUser();
        User vip = TestUserFactory.createVIP();
        Integer userId = userService.save(user).getId();
        Integer vipId = userService.save(vip).getId();
        String token = getAccessTokenForUser(user);
        assertNotEquals(vipId, userId);

        CreateCommand cmd = new CreateCommand(
                vipId,
                Arrays.asList(1)
        );

        //when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents/create",
                token,
                cmd,
                CreateCommand.class
        );

        // then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    void user_should_be_able_to_create_rent_for_himself() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        CreateCommand cmd = new CreateCommand(
                userId,
                Arrays.asList(1)
        );

        //when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents/create",
                token,
                cmd,
                CreateCommand.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PageRent pageRent = rentService.findAll(Pageable.ofSize(1));
        assertEquals(userId,pageRent.getRents().get(0).getUserId().id());
        assertEquals(1,pageRent.getRents().get(0).getBicycleId().id());
    }

    @Test
    void user_should_not_be_able_to_create_multiple_rents_for_himself() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        CreateCommand cmd = new CreateCommand(
                userId,
                Arrays.asList(1,2,3)
        );

        //when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents/create",
                token,
                cmd,
                CreateCommand.class
        );

        // then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    // CRUD

    @Test
    void admin_should_be_able_to_save_rent() {
        // given
        User admin = TestUserFactory.createAdmin();
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);

        RentDto rentDto = new RentDto(null, "TEST11",1,1, null);

        // when
        var response = callHttpMethod(
                HttpMethod.POST,
                "/api/v1/rents",
                token,
                rentDto,
                RentDto.class
        );
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
        Integer adminId = userService.save(admin).getId();
        String token = getAccessTokenForUser(admin);

        // when
        var response = callHttpMethod(
                HttpMethod.GET,
                "/api/v1/rents",
                token,
                null,
                PageRent.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void user_should_not_be_able_to_get_rents() {
        // given
        User user = TestUserFactory.createUser();
        Integer userId = userService.save(user).getId();
        String token = getAccessTokenForUser(user);

        // when
        var response = callHttpMethod(
                HttpMethod.GET,
                "/api/v1/rents",
                token,
                null,
                PageRent.class
        );

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
