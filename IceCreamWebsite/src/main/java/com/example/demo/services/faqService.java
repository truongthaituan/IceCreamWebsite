package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.faq;
public interface faqService {
    List<faq> findAll();
    Optional<faq> getFaqById(Long fag_id);
    void saveOrUpdate(faq fag);
    void deleteFaq(Long fag_id);
}
