package com.example.menuservice.controller;


import com.example.menuservice.dto.MenuItemDTO;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Slf4j
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping("/add-item")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMenuItem(@RequestBody MenuItemDTO dto) {
        log.info("why tf you not working reeeeeeeeeeeeeeeeeeeeeeeetard");
        menuItemService.addItem(dto);
    }

    @DeleteMapping("/delete-item")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void removeNewMenuItem(@PathVariable() String id) {
        menuItemService.removeItem(id);
    }

    @GetMapping("/full-menu")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getFullMenu() {
        return menuItemService.getFullMenu();
    }

}
