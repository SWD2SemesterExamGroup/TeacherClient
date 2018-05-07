package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.teacher.artifacts.ViewModels.Models.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Used to convert JMS messages from/to JSON. Registered in Spring-JMS automatically via auto configuration
 */
@Component
public class JsonMessageConverter implements MessageConverter
{

    @Autowired
    private ObjectMapper mapper;

    /**
     * Converts message to JSON. Used mostly by {@link org.springframework.jms.core.JmsTemplate}
     */
    @Override
    public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException
    {
        String json;

        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MessageConversionException("Message cannot be parsed. ", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(json);

        return message;
    }

    /**
     * Extracts JSON payload for further processing by JacksonMapper.
     */
    @Override
    public Object fromMessage(javax.jms.Message message) throws JMSException, MessageConversionException {
        System.out.println("Message:\n" + message);
        String value = ((TextMessage) message).getText();
        System.out.println("Message To String: " + value);

        String result = value.replace("<string>", "");
        result = result.replace("</string>", "");
        result = result.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
        System.out.println("Message result:\n" + result);

        // <?xml version="1.0" encoding="utf-8"?><string>{"teacherID":1,"teacherName":"Troels Helbo Jensen","courses":[{"courseClass":{"courseClass":null,"ID":1,"title":"SWD Team 11"},"ID":1,"title":"Construction 1"},{"courseClass":{"courseClass":null,"ID":2,"title":"Datamatiker 17B"},"ID":3,"title":"Python"}]}</string>
        return result;
        //return ((TextMessage) message).getText();
    }
}
