package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ProjectLocals.ContainerTEMP;
import dk.kea.teacher.artifacts.Helpers.RandomString;
import dk.kea.teacher.artifacts.ViewModels.KeyGeneratorView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{
    private ContainerTEMP generator = new ContainerTEMP();

    @RequestMapping("/")
    public String home() {
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
        RandomString key = new RandomString(10);

        // Do hash and call create to database
        model.addAttribute("className", viewmodel.getClassBy(viewmodel.getClassID()).getTitle());
        model.addAttribute("courseName", viewmodel.getCourseBy(viewmodel.getCourseID()).getTitle());
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("key", key.nextString());
        return "keygenerator";
    }
}