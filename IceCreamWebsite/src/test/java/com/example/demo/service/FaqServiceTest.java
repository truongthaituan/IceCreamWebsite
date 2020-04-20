package com.example.demo.service;
import com.example.demo.models.Faq;
import com.example.demo.repositories.FaqRepository;
import com.example.demo.services.FaqServiceImpl;
import com.example.demo.services.IceCreamService;
import com.example.demo.services.IceCreamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.repositories.IceCreamRepository;
import com.example.demo.models.IceCream;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class FaqServiceTest {
    @InjectMocks
    FaqServiceImpl faqService;
    @MockBean
    private FaqRepository faqRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getAllFaqsTest()
    {
        List<Faq> faqs = new ArrayList<Faq>();
        Faq faq1 = new Faq((long)1, "Where can I get coupons for Parlor products?",
                "Occasionally we offer coupons and samples on our website and Facebook page.", true);
        Faq faq2 = new Faq((long)2, "Do your products contain allergens or gluten?",
                "Some products do contain allergens, including gluten.",false);
        faqs.add(faq1);
        faqs.add(faq2);
        when(faqRepository.findAll()).thenReturn(faqs);
        //test
        List<Faq> faqList = faqService.findAll();
        assertEquals(2, faqList.size());
        verify(faqRepository, times(1)).findAll();
    }
    @Test
    public void getFaqByIdTest()
    {
        when(faqRepository.findById((long)1))
                .thenReturn(Optional.of(new Faq((long)1, "Where can I get coupons for Parlor products?",
                        "Occasionally we offer coupons and samples on our website and Facebook page.", true)));
        Optional<Faq> faq = faqService.getFaqById((long)1);
        assertEquals("Where can I get coupons for Parlor products?", faq.get().getQuestion());
        assertEquals("Occasionally we offer coupons and samples on our website and Facebook page.", faq.get().getAnswer());
    }
    @Test
    public void createFaqTest()
    {
        Faq faq = new Faq((long)1, "Where can I get coupons for Parlor products?",
                "Occasionally we offer coupons and samples on our website and Facebook page.", true);
        faqService.saveOrUpdate(faq);
        verify(faqRepository, times(1)).save(faq);
    }

    @Test
    public void testDeleteFaq() {
        Faq faq = new Faq((long) 1, "Where can I get coupons for Parlor products?",
                "Occasionally we offer coupons and samples on our website and Facebook page.", true);
        Optional<Faq> optionalFaq = Optional.of(faq);
        when(faqRepository.findById((long) 1)).thenReturn(optionalFaq);
        // when
        faqService.deleteFaq(faq.getFaqId());
        // then
        verify(faqRepository, times(1)).deleteById(faq.getFaqId());
    }
}
