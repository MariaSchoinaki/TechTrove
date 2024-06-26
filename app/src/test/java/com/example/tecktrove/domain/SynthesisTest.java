package com.example.tecktrove.domain;

import com.example.tecktrove.util.Money;
import com.example.tecktrove.util.Port;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SynthesisTest {

    Synthesis s1,s2;

    /**
     * Sets up the data used
     */
    @Before
    public void setUp(){
        s1 = new Synthesis(235456, Money.euros(1500), "Best Synthesis");
        s2 = new Synthesis(456379, Money.euros(850), "Average Synthesis");
    }

    /**
     * Tests setters and getters
     */
    @Test
    public void checkSetGet(){
        Assert.assertEquals("Best Synthesis", s1.getName());
        Assert.assertEquals(Money.euros(850), s2.getPrice());
        Assert.assertEquals(456379, s2.getModelNo());

        s2.setPublishState(true);
        Assert.assertTrue(s2.getPublishState());

        s2.setSubRating(5, new Customer());
        Assert.assertEquals(5,s2.getSubRating(),0.5);
    }

    /**
     * Tests default constructor, setters and getters
     */
    @Test
    public void checkDefaultConstructor(){
        Synthesis s = new Synthesis();
        s.setModelNo(1234);
        Assert.assertEquals(1234,s.getModelNo());
        s.setName("abcd");
        Assert.assertEquals("abcd", s.getName());
    }

    /**
     * Tests calculating rating of a synthesis
     */
    @Test
    public void checkCalculateRating(){

        s1.setSubRating(5, new Customer());
        s1.setSubRating(3, new Customer());
        Assert.assertEquals(4.0, s1.getRating(),0.0001);
        Assert.assertEquals(2, s1.getNumberOfRatings());
    }

    /**
     * Tests list of the ratings of a synthesis
     */
    @Test
    public void checkListRatings(){

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        s1.setSubRating(5, c1);
        Assert.assertEquals(c1,s1.getCustomer());
        s1.setSubRating(3, c2);
        Assert.assertEquals(c2,s1.getCustomer());
        Assert.assertEquals(2, s1.getRatingsList().size());
    }

    /**
     * Tests the components of a synthesis
     */
    @Test
    public void checkComponents(){
        Component c = new Component(235456,Money.euros(BigDecimal.valueOf(73.45)) , "AMD Ryzen 7", "Επεξεργαστής 8 Πυρήνων για Socket AM4 σε Κουτί", "AMD", new Port(), new Port() ,9);
        s1.add(c);
        Assert.assertTrue(s1.getComponentList().get(0).getName().equals(c.getName()));

        Component k = new Component(678542,Money.euros(BigDecimal.valueOf(45)) , "CPU 7", "", "AMD", new Port(), new Port() ,7);
        s1.add(k);
        Assert.assertEquals(2, s1.getComponentList().size());
        Assert.assertEquals(7, s1.getQuantity());

        s1.remove(c);
        Assert.assertEquals(1, s1.getComponentList().size());
    }
}
