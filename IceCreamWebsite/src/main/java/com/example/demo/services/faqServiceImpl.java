package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.faq;
import java.util.List;
import java.util.Optional;
import com.example.demo.repositories.faqRepository;
@Service
public class faqServiceImpl implements faqService{
    @Autowired
    faqRepository faqRepository;
    @Override
    public List<faq> findAll() {
        return (List<faq>) faqRepository.findAll();
    }

    @Override
    public Optional<faq> getFaqById(Long faq_id) {
        return faqRepository.findById(faq_id);
    }

    @Override
    public void saveOrUpdate(faq faq) {
        faqRepository.save(faq);
    }
    @Override
    public void deleteFaq(Long faq_id) {
        faqRepository.deleteById(faq_id);
    }
}
