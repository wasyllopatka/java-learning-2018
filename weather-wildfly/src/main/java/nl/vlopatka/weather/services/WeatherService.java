package nl.vlopatka.weather.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.vlopatka.weather.models.Weather;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class WeatherService {
    private final String urlName = "http://weerlive.nl/api/json-data-10min.php?key=demo&locatie=Amsterdam";
    private final String rootWeather = "liveweer";

    // parse given URL, set Weather fields
    // return Weather
    public Weather getWeather() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        Weather weather = new Weather();
        try {
            rootNode = mapper.readTree(new URL(urlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode weerNode = null;
        if (rootNode != null) {
            weerNode = rootNode.get(rootWeather);
        }
        if (weerNode != null) {
            for (JsonNode node : weerNode) {
                weather = parseNode(node);
                weather.setImage(getImage(node));
            }
        }
        return weather;
    }

    // Select the image source (String) according to the  possible description
    public String getImage(JsonNode node) {
        String samenv = String.valueOf(node.get("samenv"));
        String sv = samenv.substring(1, samenv.length()-1);
        String image = "";
        switch(sv) {
            case "Onbewolkt" :
                image = "/icons/onbewolkt.png";
                break;
            case "Licht bewolkt" :
                image = "/icons/halfBewolkt.png";
                break;
            case "Half bewolkt" :
                image = "/icons/halfBewolkt.png";
                break;
            case "Geheel bewolkt" :
                image = "/icons/geheelBewolkt.png";
                break;
            case "Zwaar bewolkt" :
                image = "/icons/geheelBewolkt.png";
                break;
            case "Motregen" :
                image = "/icons/lichteMotregen.png";
                break;
            case "Lichte motregen" :
                image = "/icons/lichteMotregen.png";
                break;
            case "Dichte motregen" :
                image = "/icons/lichteMotregen.png";
                break;
            case "Lichte motregen en regen" :
                image = "/icons/motregenRegen.png";
                break;
            case "Droog na motregen" :
                image = "/icons/onbewolkt.png";
                break;
            case "Motregen en regen" :
                image = "/icons/motregenRegen.png";
                break;
            case "Af en toe lichte regen" :
                image = "/icons/motregenRegen.png";
                break;
            case "Lichte regen" :
                image = "/icons/regen.png";
                break;
            case "Regen" :
                image = "/icons/regen.png";
                break;
            case "Droog na regen" :
                image = "/icons/onbewolkt.png";
                break;
            default :
                System.out.println("Invalid description");
        }
        return image;
    }
    // Parse node by element name and remove first and last symbol (""),
    // Set weather fields and return Weather Object
    private Weather parseNode(JsonNode node) {
        Weather weather = new Weather();
        String plaats = String.valueOf(node.get("plaats"));
        String temp = String.valueOf(node.get("temp"));
        String luchtd = String.valueOf(node.get("luchtd"));
        String samenv = String.valueOf(node.get("samenv"));
        String windr = String.valueOf(node.get("windr"));
        String windms = String.valueOf(node.get("windms"));
        weather.setPlaats(plaats.substring(1, plaats.length()-1));
        weather.setTemp(temp.substring(1, temp.length() -1));
        weather.setLuchtdruk(luchtd.substring(1, luchtd.length()-1));
        weather.setBewolking(samenv.substring(1, samenv.length()-1));
        weather.setWindrichting(windr.substring(1, windr.length()-1));
        weather.setWindsnelheid(windms.substring(1, windms.length()-1));
        return weather;
    }
}
