package ru.itapp.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itapp.entity.InstaUserDetails;
import ru.itapp.entity.Menu;
import ru.itapp.entity.User;
import ru.itapp.entity.View;
import ru.itapp.services.MenuDataService;
import ru.itapp.services.UserDataService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MenuController {
    private final UserDataService userDataService;
    private final MenuDataService menuDataService;

    @Autowired
    public MenuController(UserDataService userDataService, MenuDataService menuDataService) {
        this.userDataService = userDataService;
        this.menuDataService = menuDataService;
    }

    @JsonView(View.Menu.class)
    @GetMapping("/menu")
    public Map<String, Object> getMenu() {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            List<Menu> recipes = menuDataService.findAll();
            map.put("list", recipes);
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @JsonView(View.MenuAllInfo.class)
    @GetMapping("/menu/info")
    public Map<String, Object> getMenuInfo(@RequestParam(value = "id") Long id) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Optional<Menu> menu = menuDataService.getById(id);
            map.put("item", menu);
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @GetMapping("/menu/select/set")
    @PreAuthorize("hasRole('USER') or hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> setSelectedMenu(@AuthenticationPrincipal InstaUserDetails userDetails, @RequestParam(value = "id") Long id) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Optional<User> user = userDataService.getById(userDetails.getUsername());
            if (user.isEmpty()) throw new Exception("Пользователь не найден");
            Optional<Menu> menu = menuDataService.getById(id);
            if (menu.isEmpty()) throw new Exception("Меню не найдено");
            user.get().setSelectedMenu(menu.get());
            userDataService.save(user.get());
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @GetMapping(value = "/menu/select")
    @PreAuthorize("hasRole('USER') or hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getSelectedMenu(@AuthenticationPrincipal InstaUserDetails userDetails) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Optional<User> user = userDataService.getById(userDetails.getUsername());
            if (user.isEmpty()) throw new Exception("Пользователь не найден");
            if(user.get().getSelectedMenu() == null) throw new Exception("Нет выбранного");
            map.put("id", user.get().getSelectedMenu().getId());
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }
}