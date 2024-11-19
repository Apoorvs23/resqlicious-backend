package com.apoorv.resqliciousbackend.repository;

import com.apoorv.resqliciousbackend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
