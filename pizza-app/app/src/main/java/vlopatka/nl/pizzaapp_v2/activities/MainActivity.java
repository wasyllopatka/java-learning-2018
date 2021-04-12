package vlopatka.nl.pizzaapp_v2.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.fragments.CreateFragment;
import vlopatka.nl.pizzaapp_v2.fragments.HomeFragment;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pizza pizza = PizzaService.pizza;
        pizza.setCurrentSaucePosition(-1);

        FragmentManager manager = getSupportFragmentManager();
        CreateFragment createFragment = new CreateFragment();
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = manager.beginTransaction();


        if (manager.findFragmentByTag(CreateFragment.TAG) == null) {
            transaction.add(R.id.topContainer, createFragment, CreateFragment.TAG);

        }
        if (manager.findFragmentByTag(HomeFragment.TAG) == null) {
            transaction.add(R.id.container, homeFragment, HomeFragment.TAG);
        }

        transaction.commit();
    }

}
