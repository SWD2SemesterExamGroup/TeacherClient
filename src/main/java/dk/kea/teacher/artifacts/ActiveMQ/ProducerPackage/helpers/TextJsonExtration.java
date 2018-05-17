package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.helpers;

public class TextJsonExtration
{
    public String filterActiveMQMsg(String str) {
        String result = str.replace("<string>", "");
        result = result.replace("</string>", "");
        result = result.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
        return result;
    }
}
