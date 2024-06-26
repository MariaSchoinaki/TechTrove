package com.example.tecktrove.view.Product;

import com.example.tecktrove.dao.Initializer;
import com.example.tecktrove.memorydao.ComponentDAOMemory;
import com.example.tecktrove.memorydao.MemoryInitializer;
import com.example.tecktrove.memorydao.SynthesisDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductPresenterTest {

    private ProductViewStub view;
    private ProductPresenter presenter;
    private Initializer init;

    /**
     * Sets up the presenter and initializes the data
     */
    @Before
    public void setUp(){
        init = new MemoryInitializer();
        init.prepareData();

        view = new ProductViewStub();
        presenter = new ProductPresenter(new ComponentDAOMemory(), new SynthesisDAOMemory(),view);
    }

    /**
     * Tests the page navigation
     */
    @Test
    public void testShowInfo(){
        presenter.setInfo(1, init.getCustomerDAO().findCustomerByUsername("george"));
        Assert.assertEquals(0, view.getTimesShowInfoOfProduct());

        presenter.setInfo(2936, init.getCustomerDAO().findCustomerByUsername("george"));
        Assert.assertEquals(1, view.getTimesShowInfoOfProduct());

        Assert.assertEquals(0, view.getTimesExited());
        presenter.onExit();
        Assert.assertEquals(1, view.getTimesExited());
    }

    /**
     *
     */
    @Test
    public void test(){
        Assert.assertEquals(view, view.getProductView());
        Assert.assertNotNull(view.getProductView());
    }

    /**
     * Tests add to cart button functionality
     */
    @Test
    public void testCart(){
        presenter.setInfo(2936, init.getCustomerDAO().findCustomerByUsername("george"));

        Assert.assertEquals(0, view.getTimesShowingMessage());
        presenter.goToCart(80, 2936);
        Assert.assertEquals(1, view.getTimesShowingMessage());
        Assert.assertEquals(75, view.getQuantity());

        presenter.goToCart(2, 2936);
        Assert.assertEquals(73, view.getQuantity());

        presenter.setInfo(9787, init.getCustomerDAO().findCustomerByUsername("george"));
        presenter.goToCart(1, 9787);
        presenter.goToCart(100, 9787);
        Assert.assertEquals(2, view.getTimesShowingMessage());
    }

    /**
     * Tests increase and decrease quantity buttons
     */
    @Test
    public void testIncreaseDecrease(){
        presenter.setInfo(3260, init.getCustomerDAO().findCustomerByUsername("george"));
        Assert.assertEquals(0,view.getTimesDecreasedQuantity());
        Assert.assertEquals(0, view.getTimesIncreasedQuantity());

        presenter.onIncreaseQuantity();
        presenter.onDecreaseQuantity();
        Assert.assertEquals(1,view.getTimesDecreasedQuantity());
        Assert.assertEquals(1, view.getTimesIncreasedQuantity());
    }
    /**
     * Test synthesis rating
     */
    @Test
    public void testRating(){
        presenter.setRating(9787, 4, init.getCustomerDAO().findCustomerByUsername("george"));
        Assert.assertEquals(2.5, init.getSynthesisDAO().find(9787).getRating(), 0);

        presenter.setInfo(9787,  init.getCustomerDAO().findCustomerByUsername("george"));
        Assert.assertFalse(view.isShowRating());

        presenter.setInfo(9485, init.getCustomerDAO().findCustomerByUsername("chris"));
        Assert.assertFalse(view.isShowRating());
    }
}
