package dk.kea.teacher.artifacts.Helpers;

import dk.kea.teacher.artifacts.ProjectLocals.KeyPlaceHolder;

import java.util.ArrayList;

public class KeyGeneratorController
{
    private static final RandomString key = new RandomString(10);
    // Generate key by lessons
    public static ArrayList<String> GenerateKeys(int lessons) {
        ArrayList<String> keys = new ArrayList<>();
        // 2 lessons = one key
        System.out.println("lessons: " + lessons);
        int modules = modulesFrom(lessons);
        System.out.println("Modules: " + modules);
        for (int i = 0; i < modules; i++)
            keys.add(key.nextString());
        KeyPlaceHolder.addKeys(keys);
        return keys;
    }

    private static int modulesFrom(int lessons) {
        return (int)Math.ceil(lessons / 2.0);
    }

}
