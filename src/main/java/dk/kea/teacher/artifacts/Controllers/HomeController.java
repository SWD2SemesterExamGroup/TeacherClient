package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsPersister;
import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsProducer;
import dk.kea.teacher.artifacts.LoginPackage.Authorization;
import dk.kea.teacher.artifacts.LoginPackage.LoginModel;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class HomeController
{
    @Autowired
    private JmsProducer producer;
    @Resource
    private JmsPersister persist;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
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
            System.out.println("Sleeping 0.5 seconds");
            Thread.sleep(500);

            /*System.out.println("Consumer Messages");
            System.out.println(persist.get());
            System.out.println(persist.getLatest());*/

            // Redirecting to the teacher Home folder
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
}