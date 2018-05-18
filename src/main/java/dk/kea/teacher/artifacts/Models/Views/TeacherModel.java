package dk.kea.teacher.artifacts.Models.Views;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = TeacherModel.class)
@Service
@JsonComponent
public class TeacherModel implements Serializable
{
    // Fields
    private int teacherID;
    private String teacherName;
    @JsonInclude
    private List<BaseCModel> courses;

    // Constructors
    public TeacherModel()
    {
    }
    public TeacherModel(int id)
    {
        teacherID = id;
    }
    public TeacherModel(int teacherID, String teacherName)
    {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
    }
    public TeacherModel(int teacherID, List<BaseCModel> courses)
    {
        this.teacherID = teacherID;
        this.courses = courses;
    }
    public TeacherModel(int teacherID, String teacherName, List<BaseCModel> courses)
    {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.courses = courses;
    }

    // Getters and Setters
    public String getTeacherName()
    {
        return teacherName;
    }
    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }
    public int getTeacherID()
    {
        return teacherID;
    }
    public void setTeacherID(int teacherID)
    {
        this.teacherID = teacherID;
    }
    public List<BaseCModel> getCourses()
    {
        return courses;
    }
    public void setCourses(List<BaseCModel> courses)
    {
        this.courses = courses;
    }

    // Extra functionality
    public void addCourseToList(BaseCModel course) {
        this.courses.add(course);
    }

    /**
     * Get Course by id
     * @param id
     * @return
     */
    public BaseCModel getCourseBy(int id) {
        BaseCModel course = null;
        for (BaseCModel c: this.courses)
            if (c.getID() == id)
            {
                course = c;
                break;
            }
        return course;
    }

    /**
     * Get course by title
     * @param title
     * @return Course as BaseCModel
     */
    public BaseCModel getCourseBy(String title) {
        BaseCModel course = null;
        for (BaseCModel c: this.courses)
            if (c.getTitle().equalsIgnoreCase(title))
            {
                course = c;
                break;
            }
        return course;
    }

    // Display and to string

    public String toStringDisplay()
    {
        String value = "TeacherModel{" +
                "teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                ", courses=";
        for (BaseCModel bcm: courses)
            value += bcm + ", ";
        value += "}\n";
        return value;
    }

    /**
     * show detail view in JSON
     */
    @Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject(this);

        try {

            jsonInfo.put("teacherID", this.teacherID);

            JSONArray courseArray = new JSONArray(this.courses);

            if (this.courses != null) {
                this.courses.forEach(course -> {
                    JSONObject subJson = new JSONObject();
                    try {
                        subJson = new JSONObject(course);
                        courseArray.put(subJson);
                    } catch (JSONException eJSON) {
                        System.out.println("toString() : inner : " + this.getClass());
                        eJSON.printStackTrace();
                    }
                });
                jsonInfo.put("courses", courseArray);
            }
        } catch (JSONException eJSON) {
            System.out.println("toString() : " + this.getClass());
            eJSON.printStackTrace();
        }

        return jsonInfo.toString();
    }
}
