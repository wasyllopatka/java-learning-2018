package vlopatka.nl.pizzaapp_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.toppings.ToppingModel;

import java.util.List;

public class ResultListAdapter extends BaseAdapter {

    private List<ToppingModel> toppings;
    private LayoutInflater layoutInflater;

    public ResultListAdapter(Context context, List<ToppingModel> toppings) {
        this.toppings = toppings;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return toppings.size();
    }

    @Override
    public Object getItem(int position) {
        return toppings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.result_item, parent, false);
        }

        ToppingModel model = getToppingModel(position);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(model.getName());

        return view;
    }

    private ToppingModel getToppingModel(int position) {
        return (ToppingModel) getItem(position);
    }
}
