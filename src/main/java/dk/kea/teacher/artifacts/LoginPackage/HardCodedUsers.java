package dk.kea.teacher.artifacts.LoginPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Hard coded users container
 */
public class HardCodedUsers
{
    // Fields
    private List<HardCodedUser> users = generatedUsers();
    private List<HardCodedUser> students = generateStudents();

    // Getters
    public List<HardCodedUser> getUsers() { return users; }
    public List<HardCodedUser> getStudents() {return students; }

    /**
     * Generate teachers for login
     * @return list of teachers
     */
    private List<HardCodedUser> generatedUsers() {
        List<HardCodedUser> values = new ArrayList<>();
        values.add(new HardCodedUser(1,"trhj", "1234"));
        values.add(new HardCodedUser(2,"ascb", "1234"));

        return values;
    }

    /**
     * Generate Students for login
     * @return List of students
     */
    private List<HardCodedUser> generateStudents() {
        List<HardCodedUser> values = new ArrayList<>();
        values.add(new HardCodedUser(1,"troe2725", "1234"));
        values.add(new HardCodedUser(2,"abcd1234", "1234"));

        return values;
    }

}
