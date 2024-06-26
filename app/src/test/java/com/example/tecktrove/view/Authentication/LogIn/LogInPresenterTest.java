package com.example.tecktrove.view.Authentication.LogIn;

import com.example.tecktrove.dao.CustomerDAO;
import com.example.tecktrove.dao.EmployerDAO;
import com.example.tecktrove.dao.Initializer;
import com.example.tecktrove.memorydao.CustomerDAOMemory;
import com.example.tecktrove.memorydao.EmployerDAOMemory;
import com.example.tecktrove.memorydao.MemoryInitializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LogInPresenterTest {
    private Initializer init;
    private LogInPresenter presenter;
    private LogInViewStub view;
    private CustomerDAO customerDAO;
    private EmployerDAO employerDAO;

    /**
     * Sets up the presenter and initializes the data
     */
    @Before
    public void setUp(){
        init = new MemoryInitializer();
        init.prepareData();
        view = new LogInViewStub();
        this.presenter = new LogInPresenter(view, new CustomerDAOMemory(), new EmployerDAOMemory());
    }

    /**
     * Tests start process method for logging in a user
     */
    @Test
    public void testStartProcess() {
        //empty username field
        view.setUser("", "ok123456", false);
        presenter.startProcess();
        Assert.assertEquals(0, view.timesLogedIn());

        //empty password field
        view.setUser("george", "", false);
        presenter.startProcess();
        Assert.assertEquals(0, view.timesLogedIn());

        //all fields empty
        view.setUser("", "", false);
        presenter.startProcess();
        Assert.assertEquals(0, view.timesLogedIn());
        Assert.assertEquals(3, view.getErrorMessage());


        //existing user customer
        view.setUser("george", "ok123456", false);
        presenter.startProcess();
        Assert.assertEquals(1, view.timesLogedIn());

        //existing user employer
        view.setUser("eleni3", "elen!562", true);
        presenter.startProcess();
        Assert.assertEquals(2, view.timesLogedIn());

        //non existing customer
        view.setUser("yolo5", "hehe4567", false);
        presenter.startProcess();
        Assert.assertEquals(4, view.getErrorMessage());

        //non existing employer
        view.setUser("eleni3", "elen!552", true);
        presenter.startProcess();
        Assert.assertEquals(5, view.getErrorMessage());


    }

    /**
     * Tests navigating the user to sign up
     */
    @Test
    public void testSignUp(){
        presenter.onSignUp();
        Assert.assertEquals(1,view.getTimesVisitedSignUp());
    }
}
