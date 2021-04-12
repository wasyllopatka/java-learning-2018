package vlopatka.nl.pizzaapp_v2.services;

import vlopatka.nl.pizzaapp_v2.decorators.ProgressBarModel;
import vlopatka.nl.pizzaapp_v2.fragments.ProgressFragment;
import vlopatka.nl.pizzaapp_v2.utils.ProgressBarHelper;

public class ProgressBarService {

    private ProgressBarHelper helper;
    public static ProgressBarModel model = new ProgressBarModel();

    public ProgressBarService() {
        helper = new ProgressBarHelper();
    }
    public void start(String tag) {
       helper.setValues(tag);
        ProgressFragment.startProgress();
    }

}
