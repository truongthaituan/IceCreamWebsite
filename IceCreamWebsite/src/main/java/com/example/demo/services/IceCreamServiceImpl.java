package com.example.demo.services;

import com.example.demo.models.IceCream;
import com.example.demo.repositories.IceCreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IceCreamServiceImpl implements IceCreamService {

    @Autowired
    IceCreamRepository icecreamRepository;
    @Override
    public List<IceCream> findAll() {
        return (List<IceCream>) icecreamRepository.findAll();
    }

    @Override
    public Optional<IceCream> getIceCreamById(Long icecream_id) {
        return icecreamRepository.findById(icecream_id);
    }

    @Override
    public void saveOrUpdate(IceCream icecream) {
        icecreamRepository.save(icecream);
    }

    @Override
    public void deleteIceCream(Long icecream_id) {
        icecreamRepository.deleteById(icecream_id);
    }
}
