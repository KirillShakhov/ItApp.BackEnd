package ru.itapp.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itapp.entity.Profile;
import ru.itapp.entity.Role;
import ru.itapp.entity.User;
import ru.itapp.exeptions.BadRequestException;
import ru.itapp.exeptions.EmailAlreadyExistsException;
import ru.itapp.exeptions.UsernameAlreadyExistsException;
import ru.itapp.payload.ApiResponse;
import ru.itapp.payload.JwtAuthenticationResponse;
import ru.itapp.payload.LoginRequest;
import ru.itapp.payload.SignUpRequest;
import ru.itapp.services.UserDataService;
import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class AuthenticationEndpoint {
    @Autowired
    private UserDataService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        User user = User
                .builder()
                .username(payload.getUsername())
                .email(payload.getEmail())
                .password(payload.getPassword())
//                .userProfile(Profile
//                        .builder()
//                        .displayName(payload.getName())
//                        .profilePictureUrl(payload.getProfilePicUrl())
//                        .build())
                .build();

        try {
            userService.registerUser(user, Role.USER);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true,"User registered successfully"));
    }
}
