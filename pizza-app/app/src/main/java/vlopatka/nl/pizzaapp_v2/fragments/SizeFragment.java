package vlopatka.nl.pizzaapp_v2.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import vlopatka.nl.pizzaapp_v2.services.ProgressBarService;
import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;

public class SizeFragment extends Fragment {

    public static final String TAG = "SizeFragment";
    private CreateFragment createFragment;
    private SauceFragment sauceFragment;
    private HomeFragment homeFragment;
    private RadioGroup radioGroup;
    private PizzaService service;
    private Pizza pizza;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_size, null);
        ProgressBarService progressBarService = new ProgressBarService();
        createFragment = new CreateFragment();
        sauceFragment = new SauceFragment();
        homeFragment = new HomeFragment();
        manager = getFragmentManager();

        service = new PizzaService();
        pizza = PizzaService.pizza;

        rb1 = view.findViewById(R.id.rb_1);
        rb2 = view.findViewById(R.id.rb_2);
        rb3 = view.findViewById(R.id.rb_3);

        Button btnBack = view.findViewById(R.id.btn_back);
        Button btnNext = view.findViewById(R.id.btn_next);
        radioGroup = view.findViewById(R.id.radio_group);

        setChecked(service.getAmount());
        progressBarService.start(TAG);

        // Listener for radio buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        service.setAmount(Constants.PIZZA_SMALL_PRICE);
                        pizza.setSize(Constants.PIZZA_SIZE_SMALL);
                        break;
                    case R.id.rb_2:
                        service.setAmount(Constants.PIZZA_MEDIUM_PRICE);
                        pizza.setSize(Constants.PIZZA_SIZE_MEDIUM);
                        break;
                    case R.id.rb_3:
                        service.setAmount(Constants.PIZZA_BIG_PRICE);
                        pizza.setSize(Constants.PIZZA_SIZE_BIG);
                        break;
                }
            }
        });

        // Listener for button BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(ProgressFragment.TAG) != null) {
                    transaction.replace(R.id.topContainer, createFragment, CreateFragment.TAG);
                }

                if (manager.findFragmentByTag(SizeFragment.TAG) != null) {
                    transaction.replace(R.id.container, homeFragment, HomeFragment.TAG);
                }
                service.setAmount(Constants.PIZZA_START_PRICE);
                transaction.commit();
            }
        });

        // Listener for button NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    transaction = manager.beginTransaction();
                    if (manager.findFragmentByTag(SizeFragment.TAG) != null) {
                        transaction.replace(R.id.container, sauceFragment, SauceFragment.TAG);
                        transaction.addToBackStack(TAG);
                    }
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please select the size of pizza", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    // Set previous checked state for radiobutton
    public void setChecked(double price) {
        if (price != 0) {
            if (price == Constants.PIZZA_SMALL_PRICE) {
                rb1.setChecked(true);
            } else if (price == Constants.PIZZA_MEDIUM_PRICE) {
                rb2.setChecked(true);
            } else if (price == Constants.PIZZA_BIG_PRICE) {
                rb3.setChecked(true);
            }
        }
    }
}
