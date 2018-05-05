package dk.kea.teacher.artifacts.LoginPackage;

public class HardCodedUser
{
    private int ID;
    private String username, password;

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
