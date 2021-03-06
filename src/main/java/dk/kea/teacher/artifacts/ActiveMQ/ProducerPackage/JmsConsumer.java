package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.helpers.TextJsonExtraction;
import dk.kea.teacher.artifacts.Controllers.Persisters.ViewPersister;
import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;

import javax.jms.JMSException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Component for spring boot
 * Jms Consumer as teacher consumer
 */
@Component
public class JmsConsumer {
    // Fields
    @Resource
    private JmsPersister persist;
    @Resource
    private ViewPersister teacherKeyPersist;
    private TextJsonExtraction jsonExtraction = new TextJsonExtraction();

    /**
     * Listener on destination
     * @param teacher
     * @throws JMSException
     * @throws IOException
     */
    @JmsListener(destination = "${jsa.activemq.queue}", containerFactory="jsaFactory")
    public void receive(Message teacher) throws JMSException, IOException
    {
        String json = ((TextMessage)teacher).getText();
        System.out.println("Received!! Object : " + teacher);
        System.out.println("Received!! Message: " + json);

        persist.add(generateFromJson(json));
    }

    /**
     * Listener on destination
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination="${jsa.activemq.keytopic}", containerFactory = "jsaFactory")
    public void receiveKeyConfirmation(Message message) throws JMSException {
        String text = ((TextMessage)message).getText();
        System.out.println("Received!! Object : " + message);
        System.out.println("Received!! Message: " + text);

        // Add data to ViewPersister
        teacherKeyPersist.setResponseMessage(text);
    }

    // TODO: Remake in accordance to other redundant class file
    private TeacherModel generateFromJson(String json) throws IOException
    {
        String result = jsonExtraction.filterActiveMQMsg(json);

        List<BaseCModel> courses = new ArrayList<>();
        JSONObject jo = new JSONObject(result);
        JSONArray ja = null;
        try
        {
            ja = jo.getJSONArray("courses");

            if (ja.toString() != null || !ja.toString().isEmpty()) {
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
        } catch (JSONException eJSON) {

        }

        return new TeacherModel((int)jo.get("teacherID"),
                (jo.get("teacherName") != null ? jo.get("teacherName").toString() : ""),
                courses);
    }
}
