package com.DoanHieu.service;

import com.DoanHieu.model.Customer;
import com.DoanHieu.model.Province;

import java.util.List;

public interface CustomerService {
   // List<Customer> findAll();
    Iterable<Customer> findAll();

    Customer findById(Long id);
     void save(Customer customer);
     void remove(Long id);
    Iterable<Customer> findAllByProvince(Province province);
}
