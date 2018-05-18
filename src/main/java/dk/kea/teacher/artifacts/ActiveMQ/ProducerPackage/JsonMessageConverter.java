package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to convert JMS messages from/to JSON. Registered in Spring-JMS automatically via auto configuration
 */
@Component
@Service
public class JsonMessageConverter implements MessageConverter
{
    // Fields
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts message to JSON. Used mostly by {@link org.springframework.jms.core.JmsTemplate}
     */
    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException
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
    public Object fromMessage(Message message) throws JMSException, MessageConversionException
    {
        // TODO: Redo code to make it more perfect. by ObjectMapper
        System.out.println("Message:\n" + message);
        String value = ((TextMessage) message).getText();
        System.out.println("Message To String: " + value);

        String result = value.replace("<string>", "");
        result = result.replace("</string>", "");
        result = result.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");

        //System.out.println("Message result:\n" + result);
        List<BaseCModel> courses = new ArrayList<>();
        JSONObject jo = new JSONObject(result);
        //System.out.println(jo.get("teacherID").toString());
        JSONArray ja = jo.getJSONArray("courses");
        System.out.println(ja);
        if (ja.toString() != null || !ja.toString().isEmpty()) {
            System.out.println("Size: " + ja.length());
            for (int i = 0; i < ja.length(); i++)
            {
                BaseCModel course = new BaseCModel(), courseclass = new BaseCModel();
                JSONObject courseObj = ja.getJSONObject(i);

                course.setID((int)courseObj.get("ID"));
                course.setTitle((String)courseObj.get("title"));

                JSONObject studentclass = courseObj.getJSONObject("courseClass");

                courseclass.setID((int)studentclass.get("ID"));
                courseclass.setTitle((String)studentclass.get("title"));

                course.setCourseClass(courseclass);
                courses.add(course);
            }
        }

        return new TeacherModel((int)jo.get("teacherID"),(String)jo.get("teacherName"), courses);
    }
}
