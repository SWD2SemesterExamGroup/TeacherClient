package dk.kea.teacher.artifacts.ViewModels;

import dk.kea.teacher.artifacts.ViewModels.Models.Student.StudentViewModel;

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
