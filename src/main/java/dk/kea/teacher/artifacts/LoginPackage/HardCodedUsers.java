package dk.kea.teacher.artifacts.LoginPackage;

import java.util.ArrayList;
import java.util.List;

public class HardCodedUsers
{
    private List<HardCodedUser> users = generatedUsers();

    public List<HardCodedUser> getUsers() {return users; }

    private List<HardCodedUser> generatedUsers() {
        List<HardCodedUser> values = new ArrayList<>();
        values.add(new HardCodedUser(1,"troe2725", "1234"));
        values.add(new HardCodedUser(2,"ascb", "1234"));
        return values;
    }
}
