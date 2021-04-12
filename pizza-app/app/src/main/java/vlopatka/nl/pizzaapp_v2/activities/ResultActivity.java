package vlopatka.nl.pizzaapp_v2.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.adapters.ResultListAdapter;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;

public class ResultActivity extends FragmentActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Pizza pizza = PizzaService.pizza;
        Button btnNew = findViewById(R.id.btn_new);
        Button btnExit = findViewById(R.id.btn_exit);

        TextView size = findViewById(R.id.sizeValue);
        size.setText(pizza.getSize());

        TextView sauce = findViewById(R.id.sauceValue);
        sauce.setText(pizza.getSauce());

        ListView listView = (ListView) findViewById(R.id.listToppings);
        ResultListAdapter adapter = new ResultListAdapter(this, pizza.getToppings());
        listView.setAdapter(adapter);

        TextView totalPrice = findViewById(R.id.price_value);
        totalPrice.setText(String.format("%1$,.2f", pizza.getPrice()));

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
