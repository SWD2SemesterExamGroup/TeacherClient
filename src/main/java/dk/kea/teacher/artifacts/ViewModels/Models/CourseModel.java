package dk.kea.teacher.artifacts.ViewModels.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//TODO: Remove if not used
public class CourseModel
{
    private int id;
    private String title;
    private CourseModel courseClass;

    public CourseModel()
    {
    }

    public CourseModel(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public CourseModel(int id, String title, CourseModel courseClass)
    {
        this.id = id;
        this.title = title;
        this.courseClass = courseClass;
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

    public CourseModel getCourseClass()
    {
        return courseClass;
    }

    public void setCourseClass(CourseModel courseClass)
    {
        this.courseClass = courseClass;
    }
}
