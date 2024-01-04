package com.example.tecktrove.domain;

import android.util.Pair;

import com.example.tecktrove.contacts.Email;
import com.example.tecktrove.contacts.Telephone;
import com.example.tecktrove.view.SharedViewModel;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Synthesis> savedSynthesis;
    private ArrayList<Pair<ProductType,Integer>> cart;

    public Customer(){}

    /**
     * Constructor of Customer
     *
     *
     * @param savedSynthesis   a list that contains all saved synthesis of a customer
     * @param cart             the customer's cart
     */
    public Customer( int id, String username, String password, String firstName, String lastName, Email email, Telephone telephone, ArrayList<Synthesis> savedSynthesis, ArrayList<Pair<ProductType,Integer>>cart){
        super(id,username,password, firstName,lastName,email,telephone);
        this.savedSynthesis=savedSynthesis;
        this.cart=cart;
    }
    /**
     * Gets the id of the Customer
     *
     * @return the customer id
     */

    public ArrayList<Synthesis> getSavedSynthesis() {
        return this.savedSynthesis;
    }
    /**
     * Gets the list of the customer's cart
     *
     * @return the cart
     */
    public ArrayList<Pair<ProductType,Integer>> getCart() {
        return this.cart;
    }

    /**
     * Sets the  customer's saved synthesis list
     *
     * @param savedSynthesis the customer's saved synthesis list
     */
    public void setSavedSynthesis(ArrayList<Synthesis> savedSynthesis) {
        this.savedSynthesis = savedSynthesis;
    }
    /**
     * Sets the customer's cart
     *
     * @param cart the cart of the customer
     */
    public void setCart(ArrayList<Pair<ProductType,Integer>>cart) {
        this.cart = cart;
    }

    public void addToSavedSynthesis(Synthesis synthesis){
        this.savedSynthesis.add(synthesis);
    }

    public void removeFromSaved(Synthesis synthesis){
        this.savedSynthesis.remove(synthesis);
    }

    public void addToCart(Pair<ProductType,Integer> pair){
        SharedViewModel model =  new SharedViewModel();
        ArrayList<Pair<ProductType,Integer>> cart = model.getCustomer().getCart();
        if(cart.contains(pair)){
            Pair<ProductType,Integer> pair1 = getProductFromCart(pair.first.getModelNo());
            cart.remove(pair);
            int q = pair1.second;
            q=q+ pair.second;
            Pair<ProductType,Integer> Pair = new Pair<ProductType,Integer>(pair1.first,q);
            model.getCustomer().getCart().add(Pair);
        }else{
            this.cart.add(pair);
        }

    }

    public void removeFromCart(ProductType product){
        this.cart.remove(product);
    }

    public Pair<ProductType,Integer> getProductFromCart(int ProductID){
        int i = this.cart.size() - 1;
        Pair<ProductType,Integer> p;
        while(i>=0){
            p = this.cart.get(i);
            if(p.first.getModelNo() == ProductID){
                return p;
            }
            i-=1;
        }
        return null;
    }
    public ProductType getProductFromSaved(int ProductID){

        int j = this.savedSynthesis.size()-1;
        ProductType p;

        while(j>=0){
            p = this.savedSynthesis.get(j);
            if(p.getModelNo() == ProductID){
                return p;
            }
            j-=1;
        }
        return null;
    }
}
