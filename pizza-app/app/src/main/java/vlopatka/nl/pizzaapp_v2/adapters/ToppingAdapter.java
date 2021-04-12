package vlopatka.nl.pizzaapp_v2.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.decorators.toppings.*;

import java.util.ArrayList;

public class ToppingAdapter extends RecyclerView.Adapter<ToppingHolder> {

    private ToppingModel[] toppings;
    private PizzaService service;
    private Pizza pizza;
    public static ArrayList<ToppingModel> checkedToppings = new ArrayList<>();

    public ToppingAdapter(Context c, ToppingModel[] toppings) {
        this.toppings = toppings;
        service = new PizzaService();
        pizza = PizzaService.pizza;
        if (checkedToppings.size() > 0) checkedToppings.clear();
    }

    @NonNull
    @Override
    public ToppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topping_model, null);
        return new ToppingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingHolder holder, int position) {
        final ToppingModel model = toppings[position];
        holder.checkBox.setChecked(model.isSelected());
        holder.nameTxt.setText(model.getName());
        holder.posTxt.setText(model.getPrice());
        holder.img.setImageResource(model.getImage());

        holder.setItemClickListener(new ToppingHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox checkBox = (CheckBox) v;
                ToppingModel currentTopping = toppings[pos];
                pizza.getToppings().add(currentTopping);
                if (checkBox.isChecked()) {
                    currentTopping.setSelected(true);
                    switch (currentTopping.getName()) {
                        case "Salami":
                            SalamiTopping salamiTopping = new SalamiTopping(pizza);
                            service.setAmount(salamiTopping.getPrice());
                            break;
                        case "Chicken":
                            ChickenTopping chickenTopping = new ChickenTopping(pizza);
                            service.setAmount(chickenTopping.getPrice());
                            break;
                        case "Ui":
                            UiTopping uiTopping = new UiTopping(pizza);
                            service.setAmount(uiTopping.getPrice());
                            break;
                        case "Paprika":
                            PaprikaTopping paprikaTopping = new PaprikaTopping(pizza);
                            service.setAmount(paprikaTopping.getPrice());
                            break;
                    }
                    checkedToppings.add(currentTopping);
                } else if (!checkBox.isChecked()) {
                    currentTopping.setSelected(false);
                    pizza.getToppings().remove(currentTopping);
                    service.reducePrice(Double.valueOf(currentTopping.getPrice()));
                    checkedToppings.remove(currentTopping);
                }
            }
        });
    }

    public static double getTotalToppingsPrice() {
        double total = 0;
        for (ToppingModel model: checkedToppings) {
            total += Double.valueOf(model.getPrice());
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return toppings.length;
    }

}
