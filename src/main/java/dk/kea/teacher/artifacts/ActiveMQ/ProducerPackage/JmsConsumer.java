package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.ViewModels.Models.TeacherModel;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "${jsa.activemq.queue}", containerFactory="jsaFactory")
    public void receive(TeacherModel teacher) {
        System.out.println("Recieved Message: " + teacher);
    }
}
