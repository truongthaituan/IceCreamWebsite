package com.example.demo.services;

import com.example.demo.models.icecream;

import java.util.List;
import java.util.Optional;

public interface icecreamService {
    List<icecream> findAll();
    Optional<icecream> getIceCreamById(Long icecream_id);
    void saveOrUpdate(icecream icecream);
    void deleteIceCream(Long icecream_id);
}
