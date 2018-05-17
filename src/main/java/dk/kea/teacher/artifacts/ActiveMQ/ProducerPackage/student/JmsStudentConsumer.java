package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.student;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.helpers.TextJsonExtration;
import dk.kea.teacher.artifacts.Controllers.Persisters.StudentViewPersist;
import org.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class JmsStudentConsumer
{
    @Resource
    private StudentViewPersist persist;

    private TextJsonExtration jsonExtraction = new TextJsonExtration();

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
