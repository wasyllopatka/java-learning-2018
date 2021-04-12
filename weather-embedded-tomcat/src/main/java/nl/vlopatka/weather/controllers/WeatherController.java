package nl.vlopatka.weather.controllers;

import lombok.NonNull;
import nl.vlopatka.weather.models.DateTime;
import nl.vlopatka.weather.models.Weather;
import nl.vlopatka.weather.services.DateTimeService;
import nl.vlopatka.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    private final WeatherService service;
    private final DateTimeService dateTimeService;

    @Autowired
    public WeatherController(WeatherService service, DateTimeService dateTimeService) {
        this.service = service;
        this.dateTimeService = dateTimeService;
    }

    @GetMapping("/")
    public String getWeather(@NonNull Model model){
        Weather weather;
        DateTime dateTime;
        weather = service.getWeather();
        dateTime = dateTimeService.getDateTime();
        model.addAttribute("weather", weather);
        model.addAttribute("dateTime", dateTime);
        return "weather";

    }
}
