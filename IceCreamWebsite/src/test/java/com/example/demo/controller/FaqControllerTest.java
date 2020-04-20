package com.example.demo.controller;


import com.example.demo.controllers.FaqController;
import com.example.demo.filter.CORSFilter;
import com.example.demo.models.Faq;
import com.example.demo.models.IceCream;
import com.example.demo.services.FaqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.*;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class FaqControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private FaqService faqService;
    @InjectMocks
    private FaqController faqController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(faqController)
                .addFilters(new CORSFilter())
                .build();
    }


    // =========================================== Get All Faqs ==========================================

    @Test
    public void test_get_all_success() throws Exception {
        List<Faq> faqs = Arrays.asList(
                new Faq((long) 1, "Where can I get coupons for Parlor products?",
                        "Occasionally we offer coupons and samples on our website and Facebook page.", true),
                new Faq((long) 2, "Do your products contain allergens or gluten?",
                        "Some products do contain allergens, including gluten.", false));

        when(faqService.findAll()).thenReturn(faqs);
        mockMvc.perform(get("/faqs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].faqId", is(1)))
                .andExpect(jsonPath("$[0].question", is("Where can I get coupons for Parlor products?")))
                .andExpect(jsonPath("$[0].answer", is("Occasionally we offer coupons and samples on our website and Facebook page.")))
                .andExpect(jsonPath("$[0].status", is(true)))
                .andExpect(jsonPath("$[1].faqId", is(2)))
                .andExpect(jsonPath("$[1].question", is("Do your products contain allergens or gluten?")))
                .andExpect(jsonPath("$[1].answer", is("Some products do contain allergens, including gluten.")))
                .andExpect(jsonPath("$[1].status", is(false)));

        verify(faqService, times(1)).findAll();
        verifyNoMoreInteractions(faqService);
    }
    // =========================================== Get Faq By ID =========================================

    @Test
    public void test_get_by_id_success() throws Exception {
        Faq faq = new Faq((long) 1, "Where can I get coupons for Parlor products?",
                "Occasionally we offer coupons and samples on our website and Facebook page.", true);

        when(faqService.getFaqById((long) 1)).thenReturn(java.util.Optional.ofNullable(faq));

        mockMvc.perform(get("/faqs/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.faqId", is(1)))
                .andExpect(jsonPath("$.question", is("Where can I get coupons for Parlor products?")))
                .andExpect(jsonPath("$.answer", is("Occasionally we offer coupons and samples on our website and Facebook page.")))
                .andExpect(jsonPath("$.status", is(true)));

        verify(faqService, times(1)).getFaqById((long) 1);
        verifyNoMoreInteractions(faqService);
    }

    //    @Test
//    public void test_get_by_id_fail_404_not_found() throws Exception {
//        Faq faq = new Faq((long)1, "Where can I get coupons for Parlor products?",
//                "Occasionally we offer coupons and samples on our website and Facebook page.", true);
//        when(faqService.getFaqById((long)2)).thenReturn(java.util.Optional.ofNullable(faq));
//
//        mockMvc.perform(get("/faqs/{id}", 2))
//                .andExpect(status().isNotFound());
//
//        verify(faqService, times(1)).getFaqById((long)2);
//        verifyNoMoreInteractions(faqService);
//    }
    // =========================================== Create New User ========================================

    @Test
    public void test_create_faq_success() throws Exception {
        Faq faq = new Faq("If my recipe is Prized what way i receive my prize money?",
                "You can go direct to Parlor Shop or your banking...",true);

        doNothing().when(faqService).saveOrUpdate(faq);

        mockMvc.perform(
                post("/faqs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(faq)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/faqs/")));

//        verify(faqService, times(1)).saveOrUpdate(faq);
//        verifyNoMoreInteractions(faqService);
    }
    // =========================================== CORS Headers ===========================================

    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/faqs"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
