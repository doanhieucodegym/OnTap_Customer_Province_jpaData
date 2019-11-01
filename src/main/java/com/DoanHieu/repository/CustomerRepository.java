package com.DoanHieu.repository;

import com.DoanHieu.model.Customer;
import com.DoanHieu.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);
}
