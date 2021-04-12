package vlopatka.nl.pizzaapp_v2.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import vlopatka.nl.pizzaapp_v2.services.ProgressBarService;
import vlopatka.nl.pizzaapp_v2.utils.Constants;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.BBQSauce;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.CremeFraicheSauce;
import vlopatka.nl.pizzaapp_v2.adapters.SauceAdapter;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.TomatoSauce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SauceFragment extends Fragment {

    private GridView grid;
    private Pizza pizza;
    private PizzaService service;
    private ProgressBarService progressBarService;
    private SizeFragment sizeFragment;
    private ToppingFragment toppingFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static double saucePrice;
    public static int previousSelectedPosition = -1;
    public static final String TAG = "SauceFragment";
    private int[] image = {R.drawable.tomato, R.drawable.bbq, R.drawable.cf};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_sauce, null);

        pizza = PizzaService.pizza;
        sizeFragment = new SizeFragment();
        toppingFragment = new ToppingFragment();
        saucePrice = Constants.SAUCE_START_PRICE;
        progressBarService = new ProgressBarService();
        service = new PizzaService();
        manager = getFragmentManager();
        final List<String> saucesText = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.saucesText)));
        final List<String> saucesPrice = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.saucesPrice)));

        Button btnBack = view.findViewById(R.id.btn_back);
        Button btnNext = view.findViewById(R.id.btn_next);

        assert container != null;
        grid = view.findViewById(R.id.sauceGrid);
        grid.setAdapter(new SauceAdapter(getContext(), image, saucesText, saucesPrice));

        ProgressFragment.part.setText("2 / 3");
        progressBarService.start(TAG);

        // Listener for grid item
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.parseColor("#A3E4D7"));
                pizza.setCurrentSaucePosition(position);
                switch (position) {
                    case 0:
                        TomatoSauce tomatoSauce = new TomatoSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.TOMATO_SAUCE_PRICE;
                        pizza.setSauce(Constants.PIZZA_SAUCE_TOMATO);
                        service.setAmount(tomatoSauce.getPrice());
                        break;
                    case 1:
                        BBQSauce bbqSauce = new BBQSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.BBQ_SAUCE_PRICE;
                        pizza.setSauce(Constants.PIZZA_SAUCE_BBQ);
                        service.setAmount(bbqSauce.getPrice());
                        break;
                    case 2:
                        CremeFraicheSauce cremeFraicheSauce = new CremeFraicheSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.CREME_FRAICHE_SAUCE_PRICE;
                        pizza.setSauce(Constants.PIZZA_SAUCE_CREME_FRAICHE);
                        service.setAmount(cremeFraicheSauce.getPrice());
                        break;
                }

                View previousSelectedView = grid.getChildAt(previousSelectedPosition);
                if (previousSelectedPosition != -1) {
                    previousSelectedView.setSelected(false);
                    previousSelectedView.setBackgroundColor(Color.parseColor("#F9E3C0"));
                }
                pizza.setCurrentSaucePosition(position);
                previousSelectedPosition = position;
            }
        });

        // Listener for button BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(SauceFragment.TAG) != null) {
                    transaction.replace(R.id.container, sizeFragment, SizeFragment.TAG);
                    service.reducePrice(saucePrice);
                    pizza.setCurrentSaucePosition(-1);
                    ProgressFragment.part.setText("1 / 3");
                }
                transaction.commit();
            }
        });

        // Listener for button NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pizza.getCurrentSaucePosition() != -1) {
                    transaction = manager.beginTransaction();
                    if (manager.findFragmentByTag(SauceFragment.TAG) != null) {
                        transaction.replace(R.id.container, toppingFragment, ToppingFragment.TAG);
                        ProgressFragment.part.setText("3 / 3");
                    }
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please select the sauce", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public static void setSaucePrice(int pos) {
        if (pos == 0) saucePrice = Constants.TOMATO_SAUCE_PRICE;
        if (pos == 1) saucePrice = Constants.BBQ_SAUCE_PRICE;
        if (pos == 2) saucePrice = Constants.CREME_FRAICHE_SAUCE_PRICE;
    }
}
