package com.example.demo.services;

import com.example.demo.models.IceCream;

import java.util.List;
import java.util.Optional;

public interface IceCreamService {
    List<IceCream> findAll();
    Optional<IceCream> getIceCreamById(Long icecream_id);
    void saveOrUpdate(IceCream icecream);
    void deleteIceCream(Long icecream_id);
}
