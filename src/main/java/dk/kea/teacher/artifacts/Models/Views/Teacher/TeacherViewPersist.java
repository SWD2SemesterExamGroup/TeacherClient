package dk.kea.teacher.artifacts.Models.Views.Teacher;

import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.CourseTimeSchedule;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherViewPersist
{
    private TeacherModel teacherModel;
    private List<BaseCModel> listClasses;
    private int teacherID, courseID, classID, startTimeID, noOfLessons;
    private CourseTimeSchedule courseTimeSchedule;

    // Constructors
    public TeacherViewPersist()
    {
        this.listClasses = new ArrayList<BaseCModel>();
        this.courseTimeSchedule = new CourseTimeSchedule();
    }
    public TeacherViewPersist(TeacherModel teacherModel)
    {
        this.teacherModel = teacherModel;
        this.listClasses = new ArrayList<BaseCModel>();
        this.courseTimeSchedule = new CourseTimeSchedule();
    }

    // Getters & Setters
    public List<BaseCModel> getListClasses()
    {
        return listClasses;
    }
    public void setListClasses(List<BaseCModel> listClasses)
    {
        this.listClasses = listClasses;
    }
    public int getClassID()
    {
        return classID;
    }
    public void setClassID(int classID)
    {
        this.classID = classID;
    }
    public int getStartTimeID()
    {
        return startTimeID;
    }
    public void setStartTimeID(int startTimeID)
    {
        this.startTimeID = startTimeID;
    }
    public int getNoOfLessons()
    {
        return noOfLessons;
    }
    public void setNoOfLessons(int noOfLessons)
    {
        this.noOfLessons = noOfLessons;
    }
    public CourseTimeSchedule getCourseTimeSchedule()
    {
        return courseTimeSchedule;
    }
    public void setCourseTimeSchedule(CourseTimeSchedule courseTimeSchedule)
    {
        this.courseTimeSchedule = courseTimeSchedule;
    }
    public TeacherModel getTeacherModel()
    {
        return teacherModel;
    }
    public void setTeacherModel(TeacherModel teacherModel)
    {
        this.teacherModel = teacherModel;
    }
    public int getCourseID()
    {
        return courseID;
    }
    public void setCourseID(int courseID)
    {
        this.courseID = courseID;
    }
    public int getTeacherID()
    {
        return teacherID;
    }
    public void setTeacherID(int teacherID)
    {
        this.teacherID = teacherID;
    }
}
