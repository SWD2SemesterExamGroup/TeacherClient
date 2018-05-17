package dk.kea.teacher.artifacts.Controllers.Persisters;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
@Service
public class StudentViewPersist
{
    private int studentID;
    private String key;
    private boolean accept = false;

    public StudentViewPersist()
    {
    }

    public boolean isAccept()
    {
        return accept;
    }
    public void setAccept(boolean accept)
    {
        this.accept = accept;
    }
    public int getStudentID()
    {
        return studentID;
    }
    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }
    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }

    public void clear() {
        this.studentID = 0;
        this.key = "";
        this.accept = false;
    }
}
