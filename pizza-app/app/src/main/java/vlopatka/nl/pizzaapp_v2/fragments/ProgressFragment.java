package vlopatka.nl.pizzaapp_v2.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.services.ProgressBarService;

public class ProgressFragment extends android.support.v4.app.Fragment {

    public static TextView part;
    public static TextView totalAmount;
    private static ProgressBar progressBar;
    public static final String TAG = "ProgressFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_progress, null);

        totalAmount = view.findViewById(R.id.tv_total_price);
        progressBar = view.findViewById(R.id.progressBar);
        part = view.findViewById(R.id.tv_part);

        return view;
    }

    public static void startProgress() {
        new ProgressBarAsyncTask().execute();
    }

    static class ProgressBarAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int start = ProgressBarService.model.getStart();
            int end = ProgressBarService.model.getEnd();
            while (start < end) {
                start ++;
                publishProgress(start);
                SystemClock.sleep(1);
            }
            return null;
        }
    }

}
