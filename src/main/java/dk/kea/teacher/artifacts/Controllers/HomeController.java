package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsConsumer;
import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsPersister;
import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsProducer;
import dk.kea.teacher.artifacts.Helpers.KeyGeneratorController;
import dk.kea.teacher.artifacts.LoginPackage.Authorization;
import dk.kea.teacher.artifacts.LoginPackage.LoginModel;
import dk.kea.teacher.artifacts.ProjectLocals.ContainerTEMP;
import dk.kea.teacher.artifacts.ProjectLocals.KeyPlaceHolder;
import dk.kea.teacher.artifacts.ViewModels.KeyGeneratorView;
import dk.kea.teacher.artifacts.ViewModels.Models.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
public class HomeController
{
    private ContainerTEMP generator = new ContainerTEMP();
    @Autowired
    private JmsProducer producer;
    @Autowired
    private JmsConsumer consumer;
    @Resource
    private JmsPersister persist;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        /*model.addAttribute("username", "");
        model.addAttribute("password", "");*/
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(@ModelAttribute LoginModel login) throws Exception {
        // 1. Check username and password
        int id = 0;
        if ((id = Authorization.login(login.getUsername(), login.getPassword())) != 0)
        {
            producer.send(new TeacherModel(id));
            //System.out.println("producer Received: " + producer.receive());
            System.out.println("Sleeping 3 seconds");
            Thread.sleep(3000);
            System.out.println("Consumer Messages");
            System.out.println(persist.get());
            System.out.println(persist.getLatest());

            System.out.println("Redirecting");
            return "redirect:/home/";
        }
        // 2. Retreive teacher id
        // 3. Produce a queue json/xml/text message
        return "redirect:/";
    }

    @RequestMapping("/home/")
    public String homeIndex() {
        return "index";
    }

    @RequestMapping("/home/key")
    public String generateKey() {
        return "keygenerator";
    }

    @RequestMapping(value = "/key-gen", method = RequestMethod.GET)
    public String generate(Model model) {
        model.addAttribute("teacherID", 1);
        model.addAttribute("key", "");
        System.out.println(persist.get());
        // TEST
        model.addAttribute("viewmodel", new KeyGeneratorView());
        return "keygenerator";
    }

    @RequestMapping(value = "/key-gen", method = RequestMethod.POST)
    public String generate(@RequestParam("teacherID")int teacherID,
                           @ModelAttribute("viewmodel")KeyGeneratorView viewmodel,
                           Model model) {
        System.out.println(teacherID);
        System.out.println(viewmodel.getClassID());
        System.out.println(viewmodel.getCourseID());
        System.out.println("Start time ID: " + viewmodel.getStartTimeID());
        System.out.println("No of Lessons: " + viewmodel.getNoOfLessons());

        // Do hash and call create to database
        model.addAttribute("className", viewmodel.getClassBy(viewmodel.getClassID()).getTitle());
        model.addAttribute("courseName", viewmodel.getCourseBy(viewmodel.getCourseID()).getTitle());
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("startTime", viewmodel.getCourseTimeSchedule().getStartPoints().get(viewmodel.getStartTimeID()).getTimeDisplay());
        model.addAttribute("endpoint", viewmodel.getCourseTimeSchedule().getStartPoints().get(viewmodel.getStartTimeID() + viewmodel.getNoOfLessons() * 3).getTimeDisplay());

        // Remake to take list of keys
        System.out.println(Math.ceil(((viewmodel.getNoOfLessons() - viewmodel.getStartTimeID())) / 3.0));
        model.addAttribute("keys", KeyGeneratorController.GenerateKeys(viewmodel.getNoOfLessons()));
        return "keygenerator";
    }
}