package nl.vlopatka.weather.services;

import nl.vlopatka.weather.models.DateTime;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

@Service
public class DateTimeService {

    // Set dateTime fields,
    // Return dateTime object
    public DateTime getDateTime(){
        DateTime dateTime = new DateTime();
        dateTime.setDay(getToday());
        dateTime.setDate(getDate());
        dateTime.setTime(getTime());
        return dateTime;
    }

    // Get current day of week in format: "Sunday"
    public String getToday() {
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        return dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    // Get current date in format: "12 NOV"
    public String getDate() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("dd-MMM").format(cal.getTime());
    }

    // Get current time in format: "18:05"
    public String getTime() {
        String time = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        return time;
    }
}
