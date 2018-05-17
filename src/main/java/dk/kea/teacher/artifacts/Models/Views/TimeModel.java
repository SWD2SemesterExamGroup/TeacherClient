package dk.kea.teacher.artifacts.Models.Views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Used as placeholder for ids and name fo dropdown couse start time
 */
public class TimeModel
{
    // Variables
    private int id;
    private String timeDisplay;
    private String date;

    public TimeModel() {
        this.date = generateDate();
    }
    public TimeModel(int id, String timeDisplay) {
        this.id = id;
        this.timeDisplay = timeDisplay;
        this.date = generateDate();
    }

    // Getters and Setters
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getTimeDisplay()
    {
        return timeDisplay;
    }
    public void setTimeDisplay(String timeDisplay)
    {
        this.timeDisplay = timeDisplay;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    private String generateDate() {
        return LocalDate.now().toString();
    }
}
