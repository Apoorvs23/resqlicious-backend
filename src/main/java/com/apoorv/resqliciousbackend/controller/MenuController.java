package com.apoorv.resqliciousbackend.controller;

import com.apoorv.resqliciousbackend.dto.MenuDTO;
import com.apoorv.resqliciousbackend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MenuDTO> addMenu(@RequestBody MenuDTO menuDTO) {
        MenuDTO addedMenu = menuService.addMenu(menuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMenu);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable Long id) {
        MenuDTO menuDTO = menuService.getMenu(id);
        return ResponseEntity.ok(menuDTO);
    }
}
