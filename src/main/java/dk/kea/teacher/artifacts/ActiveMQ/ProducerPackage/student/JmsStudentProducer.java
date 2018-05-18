package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.student;

import dk.kea.teacher.artifacts.Controllers.Persisters.StudentViewPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Student producer
 */
@Component
public class JmsStudentProducer
{
    // Fields
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jsa.activemq.student.topic}")
    private String keyqueue;

    /**
     * Send student view persist
     * @param studentView
     */
    public void send(StudentViewPersist studentView) {
        jmsTemplate.convertAndSend(keyqueue, studentView);
    }
}
