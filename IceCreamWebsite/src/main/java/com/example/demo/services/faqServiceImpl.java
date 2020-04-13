package com.example.demo.services;

import com.example.demo.models.Faq;
import com.example.demo.repositories.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService {
    @Autowired
    FaqRepository faqRepository;
    @Override
    public List<Faq> findAll() {
        return (List<Faq>) faqRepository.findAll();
    }

    @Override
    public Optional<Faq> getFaqById(Long faq_id) {
        return faqRepository.findById(faq_id);
    }

    @Override
    public void saveOrUpdate(Faq faq) {
        faqRepository.save(faq);
    }
    @Override
    public void deleteFaq(Long faq_id) {
        faqRepository.deleteById(faq_id);
    }
}
