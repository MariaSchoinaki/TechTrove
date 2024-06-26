package com.example.tecktrove.view.Synthesis;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tecktrove.R;
import com.example.tecktrove.dao.ComponentDAO;
import com.example.tecktrove.dao.Initializer;
import com.example.tecktrove.dao.SynthesisDAO;
import com.example.tecktrove.domain.Component;
import com.example.tecktrove.domain.ProductType;
import com.example.tecktrove.domain.Synthesis;
import com.example.tecktrove.memorydao.ComponentDAOMemory;
import com.example.tecktrove.memorydao.MemoryInitializer;
import com.example.tecktrove.memorydao.SynthesisDAOMemory;
import com.example.tecktrove.view.Authentication.LogIn.LogInActivity;
import com.example.tecktrove.view.Authentication.SignUp.SignUpActivity;
import com.example.tecktrove.view.CartProductAdapter;
import com.example.tecktrove.view.CategoryAdapter;
import com.example.tecktrove.view.Customer.Cart.CartActivity;
import com.example.tecktrove.view.Customer.HomeScreen.HomeScreenActivity;
import com.example.tecktrove.view.Customer.SavedProducts.SavedProductsActivity;
import com.example.tecktrove.view.MyAccount.MyAccountActivity;
import com.example.tecktrove.view.ProductAdapter;
import com.example.tecktrove.view.SharedViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SynthesisActivity extends AppCompatActivity implements SynthesisView, CategoryAdapter.OnCategoryClickListener, ProductAdapter.OnProductClickListener {
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private Initializer init;
    private SynthesisPresenter presenter;
    private ComponentDAO componentDAO;
    private CartProductAdapter CartproductAdapter;

    private SynthesisDAO synthesisDao;
    private ProductAdapter productAdapter;

    private CategoryAdapter categoryAdapter;

    private Synthesis synthesis;

    private SharedViewModel model;

    /**
     * Initializes the classes attributes
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesis);

        recyclerView1 = findViewById(R.id.recyclerView5);
        recyclerView2 = findViewById(R.id.recyclerView7);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,recyclerView1.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(layoutManager);

        model =  new ViewModelProvider(this).get(SharedViewModel.class);


        init = new MemoryInitializer();
        componentDAO = new ComponentDAOMemory();
        synthesisDao = new SynthesisDAOMemory();

        productAdapter = new ProductAdapter(new ArrayList<ProductType>(componentDAO.findAll()), this);
        categoryAdapter = new CategoryAdapter(generateCategories(),this);

        recyclerView1.setAdapter(categoryAdapter);
        recyclerView2.setAdapter(productAdapter);

        presenter = new SynthesisPresenter(componentDAO,synthesisDao,this,model);

        TabLayout tabLayout = findViewById(R.id.SynthesisHomePageTabLayout);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        presenter.onHome();
                        break;
                    case 1:
                        presenter.onCart();
                        break;
                    case 2:
                        presenter.onSaved();
                        break;
                    case 3:
                        presenter.onMyAcount();
                        break;
                        // Add cases for other tabs as needed
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                presenter.onHome();
            }
        });


        findViewById(R.id.completeted).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onSynthesisDisplay();
            }
        });

    }

    /**
     * Puts in an ArrayList the categories of the eshop products
     *
     * @return the catogories
     */
    private ArrayList<String> generateCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("box");
        categories.add("cpu");
        categories.add("motherboard");
        categories.add("ram");
        categories.add("gpu");
        categories.add("disk");
        categories.add("psu");
        categories.add("cooler");
        return categories;
    }


    /**
     * Sets infos about a product of the synthesis
     *
     * @param product
     */
    @Override
    public void onProductClick(ProductType product) {
        if (SharedViewModel.getSynthesis().getComponentList().contains(product)){
            showErrorMessage("Προσοχή!","Το προϊόν θα αφεραιθεί από την σύνθεση!");
            presenter.removeFromSynthesis(product);
        }else if(presenter.compatibilityCheck((Component)product)){
            showErrorMessage("Προσοχή!","Το προϊόν θα αποθηκευτεί στην σύνθεση!");
            presenter.saveToSynthesis((Component) product);
        }else {
            showErrorMessage("Προσοχή!","Το προϊόν δεν είναι συμβατό με την σύνθεση!");
        }

    }

    /**
     * Displays the clicked categories products
     *
     * @param category
     */
    @Override
    public void onCategoryClick(String category) {
        presenter.onDisplayProducts(category);
    }

    /**
     * Shows a custom message
     *
     * @param title the title of the window
     * @param msg   the message of the window
     */
    @Override
    public void showErrorMessage(String title, String msg) {
        new AlertDialog.Builder(SynthesisActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Displayes the products
     * @param products the products
     */
    @Override
    public void displayProducts(ArrayList<ProductType> products) {
        updateUI(products);
    }

    /**
     * Updates the screen based on the search results
     * @param searchResults
     */
    public void updateUI(ArrayList<ProductType> searchResults) {
            productAdapter = new ProductAdapter(searchResults, this);
            recyclerView2.setAdapter(productAdapter);
    }

    /**
     * Displays a synthesis
     */
    @Override
    public void synthesisDisplay(){
        Intent intent = new Intent(this, SynthesisContainerActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates the user to the home screen
     */
    @Override
    public void home() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates the user to the cart screen
     */
    @Override
    public void Cart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    /**
     * Navigates the user to the my saved synthesis
     */
    @Override
    public void Saved() {
        Intent intent = new Intent(this, SavedProductsActivity.class);
        startActivity(intent);

    }

    /**
     * Navigates the user to the my account
     */
    @Override
    public void MyAcount() {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }


}
