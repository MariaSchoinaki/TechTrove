package com.example.tecktrove.domain;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void checkDefaultConstructor(){
        Item item = new Item();
        item.setSerialNo(123456);
        Assert.assertEquals(123456, item.getSerialNo());
    }
    @Test
    public void checkItem(){
        Item i = new Item(123476);
        Assert.assertEquals(123476, i.getSerialNo());
    }
}