package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.student;

import dk.kea.teacher.artifacts.Controllers.Persisters.StudentViewPersist;
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

    @JmsListener(destination = "${jsa.activemq.student.topic}", containerFactory = "jsaFactory")
    public void receive(Message message) throws JMSException
    {
        String jsonStudent = ((TextMessage) message).getText();
        System.out.println("Student Message");
        System.out.println("Received Object!! " + message);
        System.out.println("Received Message!! " + jsonStudent);
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
