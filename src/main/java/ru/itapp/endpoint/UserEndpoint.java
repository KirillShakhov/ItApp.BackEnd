package ru.itapp.endpoint;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itapp.entity.InstaUserDetails;
import ru.itapp.entity.User;
import ru.itapp.entity.View;
import ru.itapp.exeptions.ResourceNotFoundException;
import ru.itapp.payload.UserSummary;
import ru.itapp.services.UserDataService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserEndpoint {

    @Autowired
    private UserDataService userService;

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);

        return  userService
                .getById(username)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    @JsonView(View.User.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
//        Map<String, Object> map = new HashMap<>();
//        log.info("retrieving all users");
//        map.put("list", userService.findAll());
//        map.put("status", "ok");
//        return map;
        log.info("retrieving all users");

        return ResponseEntity
                .ok(userService.findAll());
    }

    @GetMapping(value = "/users/summaries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllUserSummaries(
            @AuthenticationPrincipal InstaUserDetails userDetails) {
        log.info("retrieving all users summaries");

        return ResponseEntity.ok(userService
                .findAll()
                .stream()
                .filter(user -> !user.getUsername().equals(userDetails.getUsername()))
                .map(this::convertTo));
    }

    @GetMapping(value = "/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('FACEBOOK_USER')")
    @ResponseStatus(HttpStatus.OK)
    public UserSummary getCurrentUser(@AuthenticationPrincipal InstaUserDetails userDetails) {
        return UserSummary
                .builder()
                .username(userDetails.getUsername())
//                .name(userDetails.getUserProfile().getDisplayName())
//                .profilePicture(userDetails.getUserProfile().getProfilePictureUrl())
                .build();
    }

    @GetMapping(value = "/users/summary/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserSummary(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);

        return  userService
                .getById(username)
                .map(user -> ResponseEntity.ok(convertTo(user)))
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    private UserSummary convertTo(User user) {
        return UserSummary
                .builder()
                .username(user.getUsername())
//                .name(user.getUserProfile().getDisplayName())
//                .profilePicture(user.getUserProfile().getProfilePictureUrl())
                .build();
    }
}
