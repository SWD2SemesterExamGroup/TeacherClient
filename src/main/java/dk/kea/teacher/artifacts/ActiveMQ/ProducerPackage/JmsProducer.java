package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.Controllers.Persisters.ViewPersister;
import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * The Java messaging system producer is used as
 * teacher producer
 */
@Component
public class JmsProducer {
    // Fields
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jsa.activemq.queue}")
    private String queue;
    @Value("${jsa.activemq.topic2}")
    private String topic2;

    /**
     * Sends teacher model to ActiveMQ
     * @param teacher
     */
    public void send(TeacherModel teacher){
        System.out.println("Queue and TeacherModel");
        System.out.println(queue);
        System.out.println(teacher.toString());
        jmsTemplate.convertAndSend(queue, teacher);
    }

    /**
     * sends teacher view to ActiveMQ
     * @param teacherView
     */
    public void send(ViewPersister teacherView) {
        System.out.println("Producer Send TeacherView");
        System.out.println(teacherView);
        jmsTemplate.convertAndSend(topic2, teacherView);
    }
}
