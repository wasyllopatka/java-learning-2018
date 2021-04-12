package nl.vlopatka.weather.models;

import lombok.Data;

@Data
public class Weather {
    private String plaats;
    private String temp;
    private String luchtdruk;
    private String bewolking;
    private String windrichting;
    private String windsnelheid;
    private String image;
}
