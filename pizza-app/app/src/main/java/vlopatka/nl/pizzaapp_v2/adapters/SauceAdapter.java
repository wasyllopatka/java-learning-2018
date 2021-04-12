package vlopatka.nl.pizzaapp_v2.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import vlopatka.nl.pizzaapp_v2.services.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.fragments.SauceFragment;

import java.util.List;

public class SauceAdapter extends BaseAdapter {

    private Context context;
    private Pizza pizza;
    private final int[] images;
    private final List<String> saucesText;
    private final List<String> saucesPrice;

    public SauceAdapter(Context context, int[] images, List<String> saucesText, List<String> saucesPrice) {
        this.context = context;
        this.images = images;
        this.saucesText = saucesText;
        this.saucesPrice = saucesPrice;
        pizza = PizzaService.pizza;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);
            if (inflater != null) {
                gridView = inflater.inflate(R.layout.sauce_model, null);
            }

            gridView.setBackgroundColor(Color.parseColor("#F9E3C0"));

            ImageView image = (ImageView) gridView.findViewById(R.id.item_image);
            image.setImageResource(images[position]);

            TextView text = (TextView) gridView.findViewById(R.id.item_text);
            text.setText(saucesText.get(position));
            text.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

            TextView price = (TextView) gridView.findViewById(R.id.item_price);
            price.setText(saucesPrice.get(position));
        } else {
            gridView = (View) convertView;
        }

        if (position == pizza.getCurrentSaucePosition()) {
            gridView.setBackgroundColor(Color.parseColor("#A3E4D7"));
            SauceFragment.previousSelectedPosition = position;
            SauceFragment.setSaucePrice(position);
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return saucesText.size();
    }

    @Override
    public Object getItem(int position) {
        return saucesText.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

