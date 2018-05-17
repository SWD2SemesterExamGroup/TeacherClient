package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.student.JmsStudentProducer;
import dk.kea.teacher.artifacts.Controllers.Persisters.StudentViewPersist;
import dk.kea.teacher.artifacts.LoginPackage.Authorization;
import dk.kea.teacher.artifacts.LoginPackage.LoginModel;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import dk.kea.teacher.artifacts.ProjectLocals.KeyPlaceHolder;
import dk.kea.teacher.artifacts.Models.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    private JmsStudentProducer producer;

    @Resource
    private StudentViewPersist persist;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        return "student/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(@ModelAttribute LoginModel login) throws Exception {
        // 1. Check username and password
        int id = 0;
        if ((id = Authorization.loginStudent(login.getUsername(), login.getPassword())) != 0)
        {
            // Catch ID
            System.out.println("Persisting ID: " + id);
            persist.setStudentID(id);
            // Redirecting to the teacher Home folder
            return "redirect:/student/home";
        }

        return "redirect:/student/";
    }

    @RequestMapping("/home")
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
                                Model model) throws InterruptedException {
        System.out.println(studentID);
        System.out.println(key);
        model.addAttribute("studentID", studentID);
        // Saves key string for later use
        persist.setKey(key);
        producer.send(persist);
        // Check Key to Static right now
        // TODO: Change to get information from MOM if key is accepted

        // Sleep and wait for message back
        Thread.sleep(3500);
        boolean accept = persist.isAccept(), isError = persist.isAccept() == true ? false : true;
        System.out.println("Is student key accepted");
        System.out.println(accept);
        System.out.println(isError);

        String errorMessage = "Wrong Key";
        // TODO: Add other checks to Key input from html
        // TODO: Change static key call to Call ESB/Mediator to confirm key
        // CAN BE DELETED WHEN CONNECTED TO MQ

        model.addAttribute("isAccepted", accept);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("attKey", "");
        model.addAttribute("isError", isError);

        return "student/index";
    }
}
