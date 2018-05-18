package dk.kea.teacher.artifacts.Models.Views;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * A class both for Course and Class objects
 */
@Service
@JsonComponent
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@ID", scope = BaseCModel.class)
public class BaseCModel implements Serializable
{
    // Fields
    @JsonInclude
    private long ID;
    @JsonInclude
    private String title;
    @JsonInclude
    private BaseCModel courseClass;

    // Constructors
    public BaseCModel()
    {
    }
    public BaseCModel(long ID, String title)
    {
        this.ID = ID;
        this.title = title;
    }
    public BaseCModel(long ID, String title, BaseCModel courseClass)
    {
        this.ID = ID;
        this.title = title;
        this.courseClass = courseClass;
    }

    // Getters and Setters
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

    @Override
    public String toString()
    {
        return "BaseCModel{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", courseClass=" + courseClass +
                "},";
    }
}
