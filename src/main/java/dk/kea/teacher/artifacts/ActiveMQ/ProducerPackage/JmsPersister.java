package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * First try to make a persist class
 */
@Component
@Service
public class JmsPersister
{
    // Fields
    private Map<Long, TeacherModel> allOfMyJmsObjects;
    public JmsPersister()
    {
        this.allOfMyJmsObjects = new TreeMap<>();
    }

    // add teacher to object
    public void add(TeacherModel t) {
        allOfMyJmsObjects.put(System.currentTimeMillis(), t);
    }

    // Getter
    public TeacherModel getLatest() {
        Long l = new Long(0);
        for (Map.Entry<Long, TeacherModel> entry: allOfMyJmsObjects.entrySet())
            if (entry.getKey() > l)
                l = entry.getKey();

        return get(l);
    }

    public TeacherModel get(long timestamp) {
        return allOfMyJmsObjects.get(timestamp);
    }

    public List<TeacherModel> get(){
        List<TeacherModel> value = new ArrayList<>();
        value.addAll(allOfMyJmsObjects.values());
        return value;
    }
}
