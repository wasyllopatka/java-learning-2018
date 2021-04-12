package vlopatka.nl.pizzaapp_v2.utils;

import vlopatka.nl.pizzaapp_v2.decorators.ProgressBarModel;
import vlopatka.nl.pizzaapp_v2.services.ProgressBarService;

public class ProgressBarHelper {
    private ProgressBarModel model;

    public ProgressBarHelper() {
        model = ProgressBarService.model;
    }

    public void setValues(String tag) {
        switch (tag) {
            case "SizeFragment":
                model.setStart(0);
                model.setEnd(33);
                break;
            case "SauceFragment":
                model.setStart(33);
                model.setEnd(66);
                break;
            case "ToppingFragment":
                model.setStart(66);
                model.setEnd(100);
                break;
        }
    }
}
