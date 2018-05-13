package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ProjectLocals.KeyPlaceHolder;
import dk.kea.teacher.artifacts.Models.ViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController
{
    private ViewModel viewModel = new ViewModel();

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("studentID", 1);
        model.addAttribute("attKey", "");
        model.addAttribute("isAccepted", false);
        model.addAttribute("isError", false);

        return "student/index";
    }

    @RequestMapping(value = "/attendanceKey", method = RequestMethod.POST)
    public String attendanceKey(@RequestParam("studentID")int studentID,
                                @RequestParam("attKey")String key,
                                Model model) {
        System.out.println(studentID);
        System.out.println(key);
        model.addAttribute("studentID", studentID);
        // Check Key to Static right now
        // TODO: Change to get information from ESB/Mediator
        boolean accept = false, isError = false;
        String errorMessage = "Wrong Key";
        // TODO: Add other checks to Key input from html
        // TODO: Change static key call to Call ESB/Mediator to confirm key
        if (KeyPlaceHolder.KEY_CHECK(key))
            accept = true;
        else
            isError = true;
        model.addAttribute("isAccepted", accept);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("attKey", "");
        model.addAttribute("isError", isError);

        return "student/index";
    }
}
