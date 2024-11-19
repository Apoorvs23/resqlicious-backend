package com.apoorv.resqliciousbackend.controller;

import com.apoorv.resqliciousbackend.dto.CartDTO;
import com.apoorv.resqliciousbackend.dto.RestaurantDTO;
import com.apoorv.resqliciousbackend.dto.UserDTO;
import com.apoorv.resqliciousbackend.entity.User;
import com.apoorv.resqliciousbackend.service.CartService;
import com.apoorv.resqliciousbackend.service.RestaurantService;
import com.apoorv.resqliciousbackend.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserDetailsService userDetailsService;
    private final RestaurantService restaurantService;
    private final CartService cartService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDTO> getUser(@PathVariable long userId){
        UserDTO user = userDetailsService.loadUserByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/restaurants")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantOfUser(@PathVariable long userId){
        User user = userDetailsService.getUserOrThrowException(userId);

        List<RestaurantDTO> restaurantByUserId = restaurantService.getRestaurantByUserId(user.getId());

        return ResponseEntity.ok(restaurantByUserId);
    }

    @GetMapping("/{userId}/carts")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable long userId){
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

}