package dk.kea.teacher.artifacts.LoginPackage;

public class Authorization
{
    public static int login(String username, String password) {
        HardCodedUsers users = new HardCodedUsers();
        for (HardCodedUser usr: users.getUsers())
            if (usr.getUsername().equalsIgnoreCase(username))
                if (usr.getPassword().equals(password))
                    return usr.getID();
        return 0;
    }
}
