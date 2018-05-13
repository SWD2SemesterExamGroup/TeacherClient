package dk.kea.teacher.artifacts.Models;

import dk.kea.teacher.artifacts.Models.Views.Student.StudentViewModel;

public class ViewModel
{
    private StudentViewModel mainModel;

    public ViewModel()
    {
        mainModel = new StudentViewModel();
    }

    public StudentViewModel getMainModel()
    {
        return mainModel;
    }

    public void setMainModel(StudentViewModel mainModel)
    {
        this.mainModel = mainModel;
    }
}
