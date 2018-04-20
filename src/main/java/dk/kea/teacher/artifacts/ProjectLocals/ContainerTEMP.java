package dk.kea.teacher.artifacts.ProjectLocals;

import dk.kea.teacher.artifacts.ViewModels.Models.ClassModel;
import dk.kea.teacher.artifacts.ViewModels.Models.CourseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContainerTEMP
{
    private final int[] arrClasses = { 1, 2, 3, 5, 6 };
    private final String[] arrClassNames = {"Dat14v1", "Dat14v2", "SWD10", "SWD11", "SWD12", "Dat17B" };

    private final int[] arrCourses = { 1, 2, 3, 5, 6 };
    private final String[] arrCourseNames = {"Construction 1", "Design 1", "Construction 2", "Design 2", "ITO", "Tech" };

    private Map<Integer, String> mapClasses;
    private Map<Integer, String> mapCourses;

    private List<ClassModel> listClasses;
    private List<CourseModel> listCourses;

    public ContainerTEMP()
    {
        this.mapClasses = new TreeMap<>();
        this.mapCourses = new TreeMap<>();
        this.listClasses = new ArrayList<>();
        this.listCourses = new ArrayList<>();
        createMaps();
        createLists();
    }

    private void createLists() {
        for (int i = 0; i < arrClassNames.length; i++)
            listClasses.add(new ClassModel(i+1, arrClassNames[i]));
        for (int i = 0; i < arrCourses.length; i++)
            listCourses.add(new CourseModel(i+1, arrCourseNames[i]));
    }

    private void createMaps() {
        for (int i = 0; i < arrClasses.length; i++)
            mapClasses.put(i+1, arrClassNames[i]);
        for (int i = 0; i < arrCourses.length; i++)
            mapCourses.put(i+1, arrCourseNames[i]);
    }

    public Map<Integer, String> getMapClasses()
    {
        return mapClasses;
    }

    public Map<Integer, String> getMapCourses()
    {
        return mapCourses;
    }

    public String[] getArrClassNames()
    {
        return arrClassNames;
    }

    public String[] getArrCourseNames()
    {
        return arrCourseNames;
    }

    public List<ClassModel> getListClasses()
    {
        return listClasses;
    }

    public List<CourseModel> getListCourses()
    {
        return listCourses;
    }
}
