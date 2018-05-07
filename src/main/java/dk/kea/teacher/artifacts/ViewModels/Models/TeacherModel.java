package dk.kea.teacher.artifacts.ViewModels.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = TeacherModel.class)
public class TeacherModel implements Serializable
{
    private int teacherID;
    private String teacherName;
    private List<BaseCModel> courses;

    public TeacherModel()
    {
    }
    public TeacherModel(int id)
    {
        teacherID = id;
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

    /**
     * show detail view
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
                        subJson.put("ID", course.getID());
                        subJson.put("Title", course.getTitle());

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
