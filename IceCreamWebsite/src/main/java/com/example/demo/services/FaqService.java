package com.example.demo.services;

import com.example.demo.models.Faq;

import java.util.List;
import java.util.Optional;

public interface FaqService {
    List<Faq> findAll();
    Optional<Faq> getFaqById(Long fag_id);
    void saveOrUpdate(Faq fag);
    void deleteFaq(Long fag_id);

}
