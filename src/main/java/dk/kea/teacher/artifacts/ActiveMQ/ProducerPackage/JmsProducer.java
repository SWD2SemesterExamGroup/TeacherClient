package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jsa.activemq.queue}")
    private String queue;

    public void send(TeacherModel teacher){
        System.out.println("Queue and TeacherModel");
        System.out.println(queue);
        System.out.println(teacher.toString());
        jmsTemplate.convertAndSend(queue, teacher);
    }

    //public TeacherModel receive() { return (TeacherModel)jmsTemplate.receiveAndConvert(queue); }
}
