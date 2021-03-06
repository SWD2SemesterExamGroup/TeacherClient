package dk.kea.teacher.artifacts.Controllers.Persisters;

import dk.kea.teacher.artifacts.Models.Views.CourseTimeSchedule;
import dk.kea.teacher.artifacts.Models.Views.Teacher.KeyModel;
import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import dk.kea.teacher.artifacts.Models.Views.TimeModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class ViewPersister
{
    // Variables
    private TeacherViewPersist teacherViewPersist;
    private List<KeyModel> listKeys;
    private CourseTimeSchedule courseTimeSchedule = new CourseTimeSchedule();
    private String responseMessage = "";

    // Constructors
    public ViewPersister()
    {
        this.listKeys = new ArrayList<>();
    }

    // Getters and Setters
    public TeacherViewPersist getTeacherViewPersist()
    {
        return teacherViewPersist;
    }
    public void setTeacherViewPersist(TeacherViewPersist teacherViewPersist)
    {
        this.teacherViewPersist = teacherViewPersist;
    }
    public List<KeyModel> getListKeys()
    {
        return listKeys;
    }
    public String getResponseMessage()
    {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    /**
     * Add keys to list keys
     * @param keys
     * @param time
     * @param lessons
     */
    public void addKeysToList(List<String> keys, TimeModel time, int lessons) {
        int lessonJump = 6; // Jump in hours for two lessons
        int breaks = 1; // No of breaks
        int noOfLessons = 0;

        // Add first key with time model implemented
        this.listKeys.add(
                new KeyModel(
                        1,
                        keys.get(0) + "",
                        time,
                        courseTimeSchedule.getStartPoints().get(time.getId() + lessonJump)
                )
        );
        noOfLessons += 2; // adds to lessons to move cursor in list
        // adds one for break to move index cursor
        breaks += addBreakEntry(courseTimeSchedule.getStartPoints().get(time.getId() + lessonJump));
        // Adds last of keys
        for (int i = 1; i < keys.size(); i++) {
            int index = i * lessonJump + breaks + time.getId();
            this.listKeys.add(
                    new KeyModel(
                            i + 1,
                            keys.get(i),
                            courseTimeSchedule.getStartPoints().get(index),
                            courseTimeSchedule.getStartPoints().get(index + lessonJump)
                    )
            );
            noOfLessons += 2;
            if ( noOfLessons < lessons)
                breaks += addBreakEntry(courseTimeSchedule.getStartPoints().get(index + lessonJump));
        }
    }

    /**
     * Add a break point i key list
     * @param start
     * @return 1
     */
    private int addBreakEntry(TimeModel start) {
        this.listKeys.add(
                new KeyModel(0,"BREAK",
                        courseTimeSchedule.getStartPoints().get(start.getId()),
                        courseTimeSchedule.getStartPoints().get(start.getId() + 1)
                )
        );
        return 1;
    }
}
