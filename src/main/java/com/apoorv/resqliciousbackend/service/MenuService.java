package com.apoorv.resqliciousbackend.service;

import com.apoorv.resqliciousbackend.dto.MenuDTO;
import com.apoorv.resqliciousbackend.entity.Category;
import com.apoorv.resqliciousbackend.entity.Menu;
import com.apoorv.resqliciousbackend.entity.Restaurant;
import com.apoorv.resqliciousbackend.exception.ResourceNotFoundException;
import com.apoorv.resqliciousbackend.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
public class MenuService {
    @Autowired
    @Lazy
    private MenuRepository menuRepository;
    @Autowired
    @Lazy
    private RestaurantService restaurantService;

    @Transactional
    public MenuDTO addMenu(MenuDTO menuDTO) {
        Long restaurantId = menuDTO.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurantOrThrowException(restaurantId);

        Menu menu = Menu.builder()
                .restaurant(restaurant)
                .categories(Collections.emptyList())
                .build();

        Menu savedMenu = menuRepository.save(menu);

        restaurant.setMenu(savedMenu);

        return MenuDTO.builder()
                .id(menu.getId())
                .restaurantId(restaurantId)
                .categoryIdList(menu.getCategories().stream().map(Category::getId).toList())
                .build();
    }

    public MenuDTO getMenu(Long id){
        Menu menu = getMenuOrThrowException(id);
        return MenuDTO.builder()
                .id(menu.getId())
                .restaurantId(menu.getRestaurant().getId())
                .categoryIdList(menu.getCategories().stream().map(Category::getId).toList())
                .build();
    }

    public Menu getMenuOrThrowException(long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Menu", "Menu ID", menuId));
    }
}
