package dk.kea.teacher.artifacts.Models;

import dk.kea.teacher.artifacts.Models.Views.Student.StudentViewModel;

/**
 * A view model used for html pages and thymeleaf
 */
public class ViewModel
{
    // Field
    private StudentViewModel mainModel;

    // Constructor
    public ViewModel()
    {
        mainModel = new StudentViewModel();
    }

    // Getter and Setter
    public StudentViewModel getMainModel()
    {
        return mainModel;
    }
    public void setMainModel(StudentViewModel mainModel)
    {
        this.mainModel = mainModel;
    }
}
