package dk.kea.teacher.artifacts.ProjectLocals;

import java.util.ArrayList;
import java.util.List;

public class KeyPlaceHolder
{
    private static List<String> KEYS = new ArrayList<>();

    public static void addKeys(List<String> keys) {
        for (String key: keys)
            KEYS.add(key);
    }

    public static boolean KEY_CHECK(String keyTry) {
        for (String key: KEYS)
            if (key.equals(keyTry))
                return true;
        return false;
    }
}
