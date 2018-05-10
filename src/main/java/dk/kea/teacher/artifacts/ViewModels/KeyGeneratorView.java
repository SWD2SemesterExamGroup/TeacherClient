package dk.kea.teacher.artifacts.ViewModels;

import dk.kea.teacher.artifacts.ProjectLocals.ContainerTEMP;
import dk.kea.teacher.artifacts.ViewModels.Models.ClassModel;
import dk.kea.teacher.artifacts.ViewModels.Models.CourseModel;
import dk.kea.teacher.artifacts.ViewModels.Models.CourseTimeSchedule;

import java.util.List;

/**
 * View model for the teacher
 */
public class KeyGeneratorView
{
    private int teacherID, courseID, classID, startTimeID, noOfLessons;
    private List<CourseModel> listCourses;
    private List<ClassModel> listClasses;
    private CourseTimeSchedule courseTimeSchedule;
    private final ContainerTEMP generator = new ContainerTEMP();


    public KeyGeneratorView()
    {
        this.listClasses = generator.getListClasses();
        this.listCourses = generator.getListCourses();
        courseTimeSchedule = new CourseTimeSchedule();
        System.out.println(getCourseBy(2));
    }

    public KeyGeneratorView(int teacherID)
    {
        this.teacherID = teacherID;
        this.listClasses = generator.getListClasses();
        this.listCourses = generator.getListCourses();
        courseTimeSchedule = new CourseTimeSchedule();
    }

    public KeyGeneratorView(int teacherID, List<CourseModel> listCourses, List<ClassModel> listClasses)
    {
        this.teacherID = teacherID;
        this.listCourses = listCourses;
        this.listClasses = listClasses;
        courseTimeSchedule = new CourseTimeSchedule();
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
    public List<CourseModel> getListCourses()
    {
        return listCourses;
    }
    public List<ClassModel> getListClasses()
    {
        return listClasses;
    }

    public void addCourse(int id, String name) {
        this.addCourse(new CourseModel(id, name));
    }
    public void addCourse(CourseModel course) {
        this.listCourses.add(course);
    }

    public void addClass(int id, String name) {
        this.addClass(new ClassModel(id, name));
    }
    public void addClass(ClassModel model) {
        this.listClasses.add(model);
    }

    public CourseModel getCourseBy(int id) {
        CourseModel course = null;
        for (CourseModel c: this.listCourses)
            if (c.getId() == id)
            {
                course = c;
                break;
            }
        return course;
    }
    public CourseModel getCourseBy(String title) {
        CourseModel course = null;
        for (CourseModel c: this.listCourses)
            if (c.getTitle().equalsIgnoreCase(title))
            {
                course = c;
                break;
            }
        return course;
    }

    public ClassModel getClassBy(int id) {
        ClassModel studentClass = null;
        for (ClassModel c: this.listClasses)
            if (c.getId() == id)
            {
                studentClass = c;
                break;
            }
        return studentClass;
    }
    public ClassModel getClassBy(String title) {
        ClassModel studentClass = null;
        for (ClassModel c: this.listClasses)
            if (c.getTitle().equalsIgnoreCase(title))
            {
                studentClass = c;
                break;
            }
        return studentClass;
    }
}