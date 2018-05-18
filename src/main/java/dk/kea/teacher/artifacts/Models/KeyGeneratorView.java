package dk.kea.teacher.artifacts.Models;

import dk.kea.teacher.artifacts.Models.Views.*;

import java.util.ArrayList;
import java.util.List;

/**
 * View model for the teacher
 */
public class KeyGeneratorView
{
    // Fields
    private int teacherID, courseID, classID, startTimeID, noOfLessons;
    private CourseTimeSchedule courseTimeSchedule;
    private TeacherModel teacherModel;
    private List<BaseCModel> listCourses;
    private List<BaseCModel> listClasses;


    // Constructors
    public KeyGeneratorView()
    {
        this.listClasses = new ArrayList<>();
        this.listCourses = new ArrayList<>();
        courseTimeSchedule = new CourseTimeSchedule();
        this.teacherModel = new TeacherModel();
    }

    public KeyGeneratorView(int teacherID)
    {
        this.teacherID = teacherID;
        this.listClasses = new ArrayList<>();
        this.listCourses = new ArrayList<>();
        courseTimeSchedule = new CourseTimeSchedule();
        this.teacherModel = new TeacherModel();
    }
    public KeyGeneratorView(int teacherID, int courseID, int classID, int startTimeID, int noOfLessons)
    {
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.classID = classID;
        this.startTimeID = startTimeID;
        this.noOfLessons = noOfLessons;
        this.listClasses = new ArrayList<>();
        this.listCourses = new ArrayList<>();
        this.courseTimeSchedule = new CourseTimeSchedule();
        this.teacherModel = new TeacherModel();
    }

    // Getters and Setters
    public TeacherModel getTeacherModel()
    {
        return teacherModel;
    }
    public void setTeacherModel(TeacherModel teacherModel)
    {
        this.teacherModel = teacherModel;
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
    public int getTeacherID()
    {
        return teacherID;
    }
    public int getCourseID()
    {
        return courseID;
    }
    public void setCourseID(int courseID)
    {
        this.courseID = courseID;
    }
    public int getClassID()
    {
        return classID;
    }
    public void setClassID(int classID)
    {
        this.classID = classID;
    }
    public List<BaseCModel> getListClasses()
    {
        return listClasses;
    }
    public void setListClasses(List<BaseCModel> listClasses)
    {
        this.listClasses = listClasses;
    }
    public List<BaseCModel> getListCourses()
    {
        return listCourses;
    }
    public void setListCourses(List<BaseCModel> listCourses)
    {
        this.listCourses = listCourses;
    }

    /**
     * Gets course by id
     * @param id
     * @return Course as BaseCModel
     */
    public BaseCModel getCourseBy(int id) {
        BaseCModel course = null;
        for (BaseCModel c: this.listCourses)
            if (c.getID() == id)
            {
                course = c;
                break;
            }
        return course;
    }

    /**
     * Get Course by title
     * @param title
     * @return Course as BaseCModel
     */
    public BaseCModel getCourseBy(String title) {
        BaseCModel course = null;
        for (BaseCModel c: this.listCourses)
            if (c.getTitle().equalsIgnoreCase(title))
            {
                course = c;
                break;
            }
        return course;
    }

    /**
     * Get class by id
     * @param id
     * @return Class as BaseCModel
     */
    public BaseCModel getClassBy(int id) {
        BaseCModel studentClass = null;
        for (BaseCModel c: this.listClasses)
            if (c.getID() == id)
            {
                studentClass = c;
                break;
            }
        return studentClass;
    }

    /**
     * Gets class by title
     * @param title
     * @return Class as BaseCModel
     */
    public BaseCModel getClassBy(String title) {
        BaseCModel studentClass = null;
        for (BaseCModel c: this.listClasses)
            if (c.getTitle().equalsIgnoreCase(title))
            {
                studentClass = c;
                break;
            }
        return studentClass;
    }
}