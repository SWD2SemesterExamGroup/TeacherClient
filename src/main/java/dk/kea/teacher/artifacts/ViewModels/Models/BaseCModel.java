package dk.kea.teacher.artifacts.ViewModels.Models;

import java.io.Serializable;

public class BaseCModel implements Serializable
{
    private long ID;
    private String title;
    private BaseCModel courseClass;

    public BaseCModel()
    {
    }

    public BaseCModel(long ID, String title, BaseCModel courseClass)
    {
        this.ID = ID;
        this.title = title;
        this.courseClass = courseClass;
    }

    public long getID()
    {
        return ID;
    }

    public void setID(long ID)
    {
        this.ID = ID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BaseCModel getCourseClass()
    {
        return courseClass;
    }

    public void setCourseClass(BaseCModel courseClass)
    {
        this.courseClass = courseClass;
    }
}
