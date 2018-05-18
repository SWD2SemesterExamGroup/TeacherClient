package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.helpers;

/**
 * Text to Json Extraction
 */
public class TextJsonExtraction
{
    /**
     * Filter message from ActiveMQ
     * @param str
     * @return Filtered String
     */
    public String filterActiveMQMsg(String str) {
        String result = str.replace("<string>", "");
        result = result.replace("</string>", "");
        result = result.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
        return result;
    }
}
