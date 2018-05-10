package dk.kea.teacher.artifacts.ViewModels.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceProvider;
import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = TeacherModel.class)
@Service
@JsonComponent
public class TeacherModel implements Serializable
{
    private int teacherID;
    private String teacherName;
    @JsonInclude
    private List<BaseCModel> courses;

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
    public void addCourseToList(BaseCModel course) {
        this.courses.add(course);
    }

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
