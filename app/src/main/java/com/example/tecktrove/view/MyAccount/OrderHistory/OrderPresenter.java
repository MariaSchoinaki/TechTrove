package com.example.tecktrove.view.MyAccount.OrderHistory;

public class OrderPresenter {
    private OrderView view;

    /**
     * Constructor
     * @param view OrderView
     */
    public OrderPresenter(OrderView view){
        this.view = view;

    }

    /**
     * navigate to home
     * */
    public void onHome() {
        view.home();
    }

    /**
     * navigate to cart
     * */
    public void onCart() {
        view.cart();
    }

    /**
     * navigate to Saved
     * */
    public void onSaved() {
        view.Saved();
    }
    /**
     * navigate to MyAcount
     * */
    public void onMyAcount() {
        view.MyAcount();
    }
}
