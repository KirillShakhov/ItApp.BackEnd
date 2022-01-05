package ru.itapp.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itapp.entity.User;
import ru.itapp.entity.View;
import ru.itapp.services.UserDataService;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {
    private final UserDataService userDataService;

    @Autowired
    public AuthController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @GetMapping("/auth/reg")
    public Map<String, String> registration(@RequestParam("login") String login,
                                            @RequestParam("email") String email,
                                            @RequestParam("pass") String pass) {

        Map<String, String> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            if (userDataService.getById(login).isEmpty()) {
                User user = new User(login, email, pass);
                userDataService.save(user);
            } else throw new Exception("Логин занят");
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @GetMapping("/auth")
    public Map<String, String> auth(@RequestParam("login") String login,
                                    @RequestParam("pass") String pass) {
        Map<String, String> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            if (userDataService.getById(login).isEmpty()) throw new Exception("Аккаунта не существует");
            else if (!userDataService.getById(login).get().getPass().equals(pass))
                throw new Exception("Пароль неправильный");
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @JsonView(View.User.class)
    @GetMapping("/info")
    public Map<String, Object> info(@RequestParam("login") String login,
                                    @RequestParam("pass") String pass,
                                    @RequestParam(value = "id", required = false) String user_id) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Optional<User> user = userDataService.getById(login);
            if (user.isEmpty()) throw new Exception("Аккаунта не существует");
            else if (!user.get().getPass().equals(pass)) throw new Exception("Пароль неправильный");
            if (user_id == null) {
                map.put("list", user.get());
            } else {
                Optional<User> u = userDataService.getById(user_id);
                if (u.isEmpty()) throw new Exception("Искомого аккаунта не существует");
                map.put("list", u.get());
            }
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

//    @JsonView(View.User.class)
//    @GetMapping("/info/edit")
//    public Map<String, Object> infoedit(@RequestParam("login") String login,
//                                        @RequestParam("pass") String pass,
//                                        @RequestParam("username") String username,
//                                        @RequestParam("email") String email,
//
//    ) {
//        Map<String, Object> map = new ManagedMap<>();
//        map.put("status", "ok");
//        try {
//            Optional<User> user = userDataService.getById(login);
//            if (user.isEmpty()) throw new Exception("Аккаунта не существует");
//            else if (!user.get().getPass().equals(pass)) throw new Exception("Пароль неправильный");
//            User u = user.get();
//            u.setUsername(username);
//            u.setEmail(email);
//            userDataService.save(u);
//            return map;
//        } catch (Exception e) {
//            map.put("status", "error");
//            map.put("message", e.getMessage());
//            return map;
//        }
//    }
}