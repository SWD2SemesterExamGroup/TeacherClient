package dk.kea.teacher.artifacts.ViewModels.Models;

public class CourseModel
{
    private int id;
    private String title;
    // add lessons time and start time lesson (Done)
    // Start time from 8.30 to 18.00 interval 15 min
    // lesson = 45 min
    // 2 lessons and a break for 15 min
    // Generate keys by number of lessons (DONE)

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
