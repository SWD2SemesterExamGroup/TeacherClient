package dk.kea.teacher.artifacts.LoginPackage;

/**
 * Authorization class for fake login testing
 */
public class Authorization
{
    /**
     * login as teacher
     * @param username
     * @param password
     * @return id or 0
     */
    public static int login(String username, String password) {
        HardCodedUsers users = new HardCodedUsers();
        for (HardCodedUser usr: users.getUsers())
            if (usr.getUsername().equalsIgnoreCase(username))
                if (usr.getPassword().equals(password))
                    return usr.getID();
        return 0;
    }

    /**
     * Login as student
     * @param username
     * @param password
     * @return id or 0
     */
    public static int loginStudent(String username, String password) {
        HardCodedUsers users = new HardCodedUsers();
        for (HardCodedUser usr: users.getStudents())
            if (usr.getUsername().equalsIgnoreCase(username))
                if (usr.getPassword().equals(password))
                    return usr.getID();
        return 0;
    }
}
