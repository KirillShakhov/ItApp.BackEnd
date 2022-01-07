package ru.itapp.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itapp.entity.Menu;
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
    public Map<String, Object> menus() {
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
    public Map<String, Object> menus(@RequestParam(value = "id") Long id) {
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
}