package com.apoorv.resqliciousbackend.repository;

import com.apoorv.resqliciousbackend.entity.PasswordResetToken;
import com.apoorv.resqliciousbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
