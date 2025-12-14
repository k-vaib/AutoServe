package com.car_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_backend.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
