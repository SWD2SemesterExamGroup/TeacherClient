package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.teacher.artifacts.ViewModels.Models.BaseCModel;
import dk.kea.teacher.artifacts.ViewModels.Models.TeacherModel;

import javax.jms.JMSException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JmsConsumer {
    @Resource
    private JmsPersister persist;

    @JmsListener(destination = "${jsa.activemq.queue}", containerFactory="jsaFactory")
    public void receive(Message teacher) throws JMSException, IOException
    {
        String json = ((TextMessage)teacher).getText();
        System.out.println("Received!! Object : " + teacher);
        System.out.println("Received!! Message: " + json);

        System.out.println(generateFromJson(json));
        persist.add(generateFromJson(json));
    }

    private String filterActiveMQMsg(String str) {
        String result = str.replace("<string>", "");
        result = result.replace("</string>", "");
        result = result.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
        return result;
    }

    private TeacherModel generateFromJson(String json) throws IOException
    {
        String result = filterActiveMQMsg(json);

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
