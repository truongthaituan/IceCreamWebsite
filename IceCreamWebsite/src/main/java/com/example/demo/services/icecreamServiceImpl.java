package com.example.demo.services;

import com.example.demo.models.icecream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.icecreamRepository;
import java.util.List;
import java.util.Optional;

@Service
public class icecreamServiceImpl implements icecreamService{

    @Autowired
    icecreamRepository icecreamRepository;
    @Override
    public List<icecream> findAll() {
        return (List<icecream>) icecreamRepository.findAll();
    }

    @Override
    public Optional<icecream> getIceCreamById(Long icecream_id) {
        return icecreamRepository.findById(icecream_id);
    }

    @Override
    public void saveOrUpdate(icecream icecream) {
        icecreamRepository.save(icecream);
    }

    @Override
    public void deleteIceCream(Long icecream_id) {
        icecreamRepository.deleteById(icecream_id);
    }
}
