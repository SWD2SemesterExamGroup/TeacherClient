package dk.kea.teacher.artifacts.Models.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for generation of
 */
public class CourseTimeSchedule
{
    // Generate List of starttimes interval 15 min
    // Generate List of lessons
    private final int noOfQuatersSP = 48;
    private final int noOfLesson = 8;
    private List<TimeModel> startPoints;
    private List<Integer> lessons;

    public CourseTimeSchedule()
    {
        startPoints = new ArrayList<>();
        lessons = new ArrayList<>();
        generateList();
    }

    /**
     * Generating list for course start dropdown in view
     */
    private void generateList() {
        // Start time from 8:00 to 20:00 by 00:15
        startPoints.add(new TimeModel(0, "08:30"));
        startPoints.add(new TimeModel(1, "08:45"));
        for (int i = 0; i < noOfQuatersSP; i++)
            startPoints.add(new TimeModel(i + 2, 9 + i / 4 + ":" + (15 * (i % 4) + (i % 4 == 0 ? "0":""))));
        // Start time from 7:45 to 23:00 by 00:15
        for (int i = 0; i < noOfLesson; i++)
            lessons.add(i);
    }

    public List<TimeModel> getStartPoints()
    {
        return startPoints;
    }

    public List<Integer> getLessons()
    {
        return lessons;
    }
}
