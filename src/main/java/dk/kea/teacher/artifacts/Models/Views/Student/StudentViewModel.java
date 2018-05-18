package dk.kea.teacher.artifacts.Models.Views.Student;

/**
 * Student view model
 */
public class StudentViewModel
{
    // Fields
    private int studentID;
    private boolean isAccepted, accept = false, isError = false;
    private String errorMessage;

    // Getters and Setters
    public StudentViewModel()
    {
        this.isAccepted = false;
    }
    public int getStudentID()
    {
        return studentID;
    }
    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }
    public boolean isAccepted()
    {
        return isAccepted;
    }
    public void setAccepted(boolean accepted)
    {
        isAccepted = accepted;
    }
}
