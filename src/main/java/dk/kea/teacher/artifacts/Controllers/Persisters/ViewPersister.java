package dk.kea.teacher.artifacts.Controllers.Persisters;

import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ViewPersister
{
    private TeacherViewPersist teacherViewPersist;

    public ViewPersister()
    {
    }

    public TeacherViewPersist getTeacherViewPersist()
    {
        return teacherViewPersist;
    }

    public void setTeacherViewPersist(TeacherViewPersist teacherViewPersist)
    {
        this.teacherViewPersist = teacherViewPersist;
    }
}
