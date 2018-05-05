package dk.kea.teacher.artifacts.ViewModels.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@JsonRootName("teacherModel")
public class TeacherModel
{
    private int teacherID;
    private List<CourseModel> listCourses;
    private List<ClassModel> listClasses;

    public TeacherModel()
    {
    }
    public TeacherModel(int id)
    {
        teacherID = id;
    }
    public TeacherModel(int teacherID, List<CourseModel> listCourses, List<ClassModel> listClasses)
    {
        this.teacherID = teacherID;
        this.listCourses = listCourses;
        this.listClasses = listClasses;
    }

    public int getTeacherID()
    {
        return teacherID;
    }

    public void setTeacherID(int teacherID)
    {
        this.teacherID = teacherID;
    }

    public List<CourseModel> getListCourses()
    {
        return listCourses;
    }

    public void setListCourses(List<CourseModel> listCourses)
    {
        this.listCourses = listCourses;
    }

    public List<ClassModel> getListClasses()
    {
        return listClasses;
    }

    public void setListClasses(List<ClassModel> listClasses)
    {
        this.listClasses = listClasses;
    }

    /**
     * show detail view
     */
    @Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject();

        try {

            jsonInfo.put("teacherID", this.teacherID);

            JSONArray courseArray = new JSONArray();
            JSONArray classArray = new JSONArray();

            if (this.listCourses != null) {
                this.listCourses.forEach(course -> {
                    JSONObject subJson = new JSONObject();
                    try {
                        subJson.put("courseID", course.getId());
                        subJson.put("courseTitle", course.getTitle());

                    } catch (JSONException eJSON) {
                        System.out.println("toString() : inner : " + this.getClass());
                        eJSON.printStackTrace();
                    }
                });
                jsonInfo.put("courses", courseArray);
            }

            if (this.listClasses != null) {
                this.listClasses.forEach(studentClass -> {
                    JSONObject subJson = new JSONObject();
                    try {
                        subJson.put("classID", studentClass.getId());
                        subJson.put("classTitle", studentClass.getTitle());
                    } catch (JSONException eJSON) {
                        System.out.println("toString() : inner : " + this.getClass());
                        eJSON.printStackTrace();
                    }
                });

                jsonInfo.put("classes", classArray);
            }
        } catch (JSONException eJSON) {
            System.out.println("toString() : " + this.getClass());
            eJSON.printStackTrace();
        }

        return jsonInfo.toString();
    }
}
