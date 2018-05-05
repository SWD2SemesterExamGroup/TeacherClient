package dk.kea.teacher.artifacts.ViewModels.Models;

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
