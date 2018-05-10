package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.ViewModels.Models.TeacherModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@Service
public class JmsPersister
{
    private Map<Long, TeacherModel> allOfMyJmsObjects;

    public JmsPersister()
    {
        this.allOfMyJmsObjects = new TreeMap<>();
    }

    void add(TeacherModel t) {
        allOfMyJmsObjects.put(System.currentTimeMillis(), t);
    }

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
