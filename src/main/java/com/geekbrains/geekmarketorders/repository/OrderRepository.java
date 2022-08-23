package com.geekbrains.geekmarketorders.repository;

import com.geekbrains.geekmarketorders.entity.GeekOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<GeekOrder, Long> {
}
