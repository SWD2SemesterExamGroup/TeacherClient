package dk.kea.teacher.artifacts.ViewModels.Models;

public class CourseModel
{
    private int id;
    private String title;

    public CourseModel()
    {
    }

    public CourseModel(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
