package com.apoorv.resqliciousbackend.repository;

import com.apoorv.resqliciousbackend.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
