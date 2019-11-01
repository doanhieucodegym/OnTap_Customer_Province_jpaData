package com.DoanHieu.service;

import com.DoanHieu.model.Province;

public interface ProvinceService {
    Iterable<Province> findAll();
    Province findById(Long id);
    void save(Province province);
    void remove(Long id);
}
