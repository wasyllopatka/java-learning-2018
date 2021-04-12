package vlopatka.nl.pizzaapp_v2.fragments;

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
import vlopatka.nl.pizzaapp_v2.R;

public class CreateFragment extends Fragment {

    public static final String TAG = "CreateFragment";

    private SizeFragment sizeFragment;
    private ProgressFragment progressFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, null);

        sizeFragment = new SizeFragment();
        progressFragment = new ProgressFragment();
        manager = getFragmentManager();
        Button button = view.findViewById(R.id.btnCreate);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(SizeFragment.TAG) == null) {
                    transaction.replace(R.id.container, sizeFragment, SizeFragment.TAG);
                }
                if (manager.findFragmentByTag(ProgressFragment.TAG) == null) {
                    transaction.replace(R.id.topContainer, progressFragment, ProgressFragment.TAG);
                }
                transaction.commit();

            }
        });
        return view;
    }
}
