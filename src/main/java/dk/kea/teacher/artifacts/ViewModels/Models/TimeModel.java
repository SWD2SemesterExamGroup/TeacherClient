package dk.kea.teacher.artifacts.ViewModels.Models;

/**
 * Used as placeholder for ids and name fo dropdown couse start time
 */
public class TimeModel
{
    private int id;
    private String timeDisplay;

    public TimeModel(int id, String timeDisplay)
    {
        this.id = id;
        this.timeDisplay = timeDisplay;
    }

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
}
