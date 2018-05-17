package dk.kea.teacher.artifacts.Models.Views.Teacher;

import dk.kea.teacher.artifacts.Models.Views.TimeModel;

public class KeyModel
{
    // Variables
    private int index;
    private String key;
    private TimeModel startPoint;
    private TimeModel expirationPoint;

    // Constructors
    public KeyModel()
    {
    }
    public KeyModel(int index, String key)
    {
        this.index = index;
        this.key = key;
    }
    public KeyModel(int index, String key, TimeModel startPoint)
    {
        this.index = index;
        this.key = key;
        this.startPoint = startPoint;
    }
    public KeyModel(int index, String key, TimeModel startPoint, TimeModel expirationPoint)
    {
        this.index = index;
        this.key = key;
        this.startPoint = startPoint;
        this.expirationPoint = expirationPoint;
    }

    // Getters and Setters
    public int getIndex()
    {
        return index;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }
    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public TimeModel getStartPoint()
    {
        return startPoint;
    }
    public void setStartPoint(TimeModel startPoint)
    {
        this.startPoint = startPoint;
    }
    public TimeModel getExpirationPoint()
    {
        return expirationPoint;
    }
    public void setExpirationPoint(TimeModel expirationPoint)
    {
        this.expirationPoint = expirationPoint;
    }
}