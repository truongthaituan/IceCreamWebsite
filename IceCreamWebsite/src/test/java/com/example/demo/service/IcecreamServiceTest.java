package com.example.demo.service;
import com.example.demo.services.IceCreamService;
import com.example.demo.services.IceCreamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
public class IcecreamServiceTest {
    @InjectMocks
    IceCreamServiceImpl icecreamService;
    @MockBean
    private IceCreamRepository icecreamRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllIcecreamsTest()
    {
        List<IceCream> icecreams = new ArrayList<IceCream>();
        IceCream icecream1 = new IceCream((long)1, "John", "John");
        IceCream icecream2 = new IceCream((long)2, "Alex", "kolenchiski");
        IceCream icecream3 = new IceCream((long)3, "Steve", "Waugh");
        icecreams.add(icecream1);
        icecreams.add(icecream2);
        icecreams.add(icecream3);
        when(icecreamRepository.findAll()).thenReturn(icecreams);
        //test
        List<IceCream> icecreamList = icecreamService.findAll();
        assertEquals(3, icecreamList.size());
        verify(icecreamRepository, times(1)).findAll();
    }
    @Test
    public void getIcecreamByIdTest()
    {
        when(icecreamRepository.findById((long)1))
                .thenReturn(Optional.of(new IceCream((long)1,"Lokesh","Gupta")));
        Optional<IceCream> icecream = icecreamService.getIceCreamById((long)1);
        assertEquals("Lokesh", icecream.get().getName());
        assertEquals("Gupta", icecream.get().getDescription());
    }
    @Test
    public void createIcecreamTest()
    {
        IceCream icecream = new IceCream((long)1,"Lokesh","Gupta");
        icecreamService.saveOrUpdate(icecream);
        verify(icecreamRepository, times(1)).save(icecream);
    }

}
