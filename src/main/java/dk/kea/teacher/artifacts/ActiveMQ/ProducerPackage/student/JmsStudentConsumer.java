package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.student;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.helpers.TextJsonExtraction;
import dk.kea.teacher.artifacts.Controllers.Persisters.LegacyPersister;
import dk.kea.teacher.artifacts.Controllers.Persisters.StudentViewPersist;
import org.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Jms Student Consumer
 */
@Component
public class JmsStudentConsumer
{
    // Fields
    @Resource
    private StudentViewPersist persist;
    @Resource
    private LegacyPersister persistLegacy;
    private TextJsonExtraction jsonExtraction = new TextJsonExtraction();

    /**
     * Student consumer of ActiveMQ
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = "${student.key.reponse}", containerFactory = "jsaFactory")
    public void receive(Message message) throws JMSException
    {
        String jsonStudent = ((TextMessage) message).getText();
        System.out.println("Student Message");
        System.out.println("Received Object!! " + message);
        System.out.println("Received Message!! " + jsonStudent);

        JSONObject json = new JSONObject(jsonExtraction.filterActiveMQMsg(jsonStudent));//persist.setAccept(true);
        System.out.println("Json Extraction!! " + json.toString());
        System.out.println("Specific value!! " + json.get("success"));
        persist.setAccept(Boolean.parseBoolean(json.get("success").toString()));
    }

    @JmsListener(destination="${legacy.godkode.html}", containerFactory = "jsaFactory")
    public void legacyReceive(Message html) throws JMSException {
        System.out.println("i got this");
        System.out.println("Message:\n" + html);
        System.out.println("Text:\n" + ((TextMessage) html).getText());

        persistLegacy.add((TextMessage) html);

        System.out.println("persistence object: " + persistLegacy.getLatest());
    }
    /*
    @JmsListener(destination = "${jsa.activemq.queue}", containerFactory="jsaFactory")
    public void receive(Message teacher) throws JMSException, IOException
    {
        String json = ((TextMessage)teacher).getText();
        System.out.println("Received!! Object : " + teacher);
        System.out.println("Received!! Message: " + json);

        //System.out.println(generateFromJson(json));
        persist.add(generateFromJson(json));
    }
    */
}
