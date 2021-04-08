package com.shridhar.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shridhar.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
