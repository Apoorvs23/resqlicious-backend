package com.apoorv.resqliciousbackend.repository;

import com.apoorv.resqliciousbackend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
