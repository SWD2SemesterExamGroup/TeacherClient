package dk.kea.teacher.artifacts.Models.Views.Student;

public class StudentViewModel
{
    private int studentID;
    private boolean isAccepted, accept = false, isError = false;
    private String errorMessage;

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
