package pl.lodz.p.bicycle_management.user.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.user.application.UserService;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private UserDtoMapper userDtoMapper;

    @PostMapping
    ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        User user = userService.addUser(userDtoMapper.toDomain(userDto));
        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers()
                .stream()
                .map(userDtoMapper::toDto)
                .toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(userDtoMapper.toDto(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.updateUser(userDtoMapper.toDomain(userDto));
            return ResponseEntity.ok(userDtoMapper.toDto(user));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    void deleteUser(@PathVariable Integer id) { userService.deleteUser(id); }

}
