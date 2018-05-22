package dk.kea.teacher.artifacts.Controllers.Persisters;

import dk.kea.teacher.artifacts.Controllers.Persisters.Models.LegacyModel;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Map;
import java.util.TreeMap;

@Component
@Service
public class LegacyPersister
{
    private Map<Long, LegacyModel> mapLegacyCode;

    public LegacyPersister()
    {
        this.mapLegacyCode = new TreeMap<>();;
    }

    // add teacher to object
    private void add(LegacyModel l) {
        this.mapLegacyCode.put(System.currentTimeMillis(), l);
    }
    public void add(TextMessage text) throws JMSException
    {
        add(new LegacyModel(text.getText()));
    }

    // Getter
    public LegacyModel getLatest() {
        Long l = new Long(0);
        for (Map.Entry<Long, LegacyModel> entry: this.mapLegacyCode.entrySet())
            if (entry.getKey() > l)
                l = entry.getKey();

        return get(l);
    }

    public LegacyModel get(long timestamp) {
        return this.mapLegacyCode.get(timestamp);
    }
}
