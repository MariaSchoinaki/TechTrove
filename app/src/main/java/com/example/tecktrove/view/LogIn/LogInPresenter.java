package com.example.tecktrove.view.LogIn;

import android.util.Log;

import com.example.tecktrove.contacts.Email;
import com.example.tecktrove.contacts.Telephone;
import com.example.tecktrove.dao.CustomerDAO;
import com.example.tecktrove.dao.EmployerDAO;
import com.example.tecktrove.domain.Customer;
import com.example.tecktrove.domain.Employer;
import com.example.tecktrove.domain.ProductType;
import com.example.tecktrove.domain.Synthesis;


import java.util.ArrayList;

public class LogInPresenter {
    LogInView view;
    CustomerDAO customers;
    EmployerDAO employers;
    Employer employer;
    Customer customer;

    public LogInPresenter(LogInView view, CustomerDAO customers, EmployerDAO employers){
        this.view = view;
        this.customers = customers;
        this.employers = employers;
    }

    public LogInPresenter(LogInView view){
        this.view = view;
    }

    void onSignUp(){
        view.signUp();
    }

    public void startProcess(){
        String username = view.getUsername();
        String password = view.getPassword();
        boolean isEmployer = view.isEmployer();


        if(username.equals("")){}
        if(password.equals("")){}
        if(!(password.equals("") || username.equals(""))){
            if(isEmployer){
                employer = employers.findEmployerByUsernameAndPassword(username,password);
                if (employer != null){
                    view.login();
                }else{
                    view.showErrorMessage("", "User does not exist with this combo. Try again");
                }
            }else{
                customer = customers.findCustomerByUsernameAndPassword(username,password);
                if (customer != null){
                    view.login();
                }else{
                    view.showErrorMessage("", "User does not exist with this combo. Try again");
                }
            }


        }

    }

    public String getUserName(){
        if(view.isEmployer()){
            if(employer != null){
                return employer.getName();
            }
        }else{
            if(customer != null){
                return customer.getName();
            }
        }
        return "";
    }
}
