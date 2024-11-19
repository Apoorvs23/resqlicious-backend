package com.apoorv.resqliciousbackend.repository;

import com.apoorv.resqliciousbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
