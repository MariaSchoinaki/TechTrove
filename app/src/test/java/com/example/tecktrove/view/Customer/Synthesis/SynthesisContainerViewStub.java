package com.example.tecktrove.view.Customer.Synthesis;

import com.example.tecktrove.view.Synthesis.SynthesisContainerView;

public class SynthesisContainerViewStub implements SynthesisContainerView {

    int home=0;
    int cart=0;
    int saved = 0;

    int myAccount =0 ;

    private int messages=0;
    private int completedSynthesis=0;

    private int savedSynthesis=0;
    @Override
    public void showErrorMessage(String title, String msg) {
        messages++;
    }

    @Override
    public void completeSynthesis() {

    }

    @Override
    public void save() {
        savedSynthesis++;
    }

    @Override
    public String getName() {
        return null;
    }

    /**
     * Show home
     */
    @Override
    public void home() {
        home++;
    }

    /**
     * Show cart
     */
    @Override
    public void cart() {
        cart++;
    }

    /**
     * Show saved
     */
    @Override
    public void saved() {
        saved++;
    }

    /**
     * Show Myacount
     */
    @Override
    public void Myacount() {
        myAccount++;
    }

    /**
     * @return times navigated to cart;
     */
    public int getCart() {
        return cart;
    }

    /**
     * @return times navigated to home
     */
    public int getHome() {
        return home;
    }
    /**
     * @return times navigated to myAccount
     */
    public int getMyAccount() {
        return myAccount;
    }
    /**
     * @return times navigated to SavedProducts
     */
    public int getSaved() {
        return saved;
    }
}
