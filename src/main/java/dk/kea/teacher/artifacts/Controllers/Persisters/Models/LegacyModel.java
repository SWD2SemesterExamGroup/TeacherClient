package dk.kea.teacher.artifacts.Controllers.Persisters.Models;

public class LegacyModel
{
    private String text;

    // Constructor
    public LegacyModel()
    {
    }
    public LegacyModel(String text)
    {
        this.text = text;
    }

    // Getters and Setters
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "LegacyModel{" +
                "text='" + text + '\'' +
                "}\n";
    }
}
