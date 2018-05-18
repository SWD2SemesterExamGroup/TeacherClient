package dk.kea.teacher.artifacts.LoginPackage;

/**
 * Hard coded user for both teacher and student
 */
public class HardCodedUser
{
    // Fields
    private int ID;
    private String username, password;

    // Constructors
    public HardCodedUser()
    {
    }
    public HardCodedUser(int ID, String username, String password)
    {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }
    public HardCodedUser(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public int getID()
    {
        return ID;
    }
}
