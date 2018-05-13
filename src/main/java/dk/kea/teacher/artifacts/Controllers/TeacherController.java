package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsPersister;
import dk.kea.teacher.artifacts.Controllers.Persisters.ViewPersister;
import dk.kea.teacher.artifacts.Helpers.KeyGeneratorController;
import dk.kea.teacher.artifacts.Models.KeyGeneratorView;
import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController
{
    @Resource
    private JmsPersister persist;

    @Resource
    private ViewPersister persistView;

    /**
     * First phase in generating a key
     * @param model
     * @return String
     */
    @RequestMapping(value = "/coursephase", method = RequestMethod.GET)
    public String pickCourse(Model model) {
        persistView.setTeacherViewPersist(new TeacherViewPersist(persist.getLatest()));
        TeacherModel teacher = persistView.getTeacherViewPersist().getTeacherModel();

        KeyGeneratorView view = new KeyGeneratorView(teacher.getTeacherID());
        view.setListCourses(teacher.getCourses());
        view.setTeacherModel(teacher);
        model.addAttribute("teacherView", persistView.getTeacherViewPersist());


        return "teacher/pickcourse";
    }

    /**
     * First phase in generating a key
     * Done for now ....
     * @param model
     * @param result
     * @return String
     */
    @RequestMapping(value = "/coursephase", method = RequestMethod.POST)
    public String pickCourse(@ModelAttribute TeacherViewPersist teacherView, Model model, BindingResult result) {
        TeacherModel t = persistView.getTeacherViewPersist().getTeacherModel();
        persistView.getTeacherViewPersist().setCourseID(teacherView.getCourseID());
        if (result.hasErrors()) {
            System.out.println(result);
        }
        List<BaseCModel> courses = new ArrayList<>();
        List<BaseCModel> courseClasses = new ArrayList<>();
        for (BaseCModel course: t.getCourses()) {
            if (course.getID() == teacherView.getCourseID())
            {
                courses.add(course);
                courseClasses.add(course.getCourseClass());
            }
        }

        persistView.getTeacherViewPersist().setListClasses(courseClasses);
        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        model.addAttribute("teacherID", teacherView.getTeacherID());
        model.addAttribute("key", "");

        return "redirect:/key-gen";
    }

    // Done for now
    @RequestMapping(value = "/key-gen", method = RequestMethod.GET)
    public String generate(Model model) {
        TeacherModel teacher = persistView.getTeacherViewPersist().getTeacherModel();

        model.addAttribute("teacherID", teacher.getTeacherID());
        model.addAttribute("key", "");

        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        return "teacher/keygenerator";
    }

    @RequestMapping(value = "/key-gen", method = RequestMethod.POST)
    public String generate(@ModelAttribute TeacherViewPersist teacherView, Model model) {
        BaseCModel course = persistView.getTeacherViewPersist().getTeacherModel().getCourseBy(persistView.getTeacherViewPersist().getCourseID());
        persistView.getTeacherViewPersist().setNoOfLessons(teacherView.getNoOfLessons());
        persistView.getTeacherViewPersist().setStartTimeID(teacherView.getStartTimeID());
        model.addAttribute("courseName", course.getTitle());
        model.addAttribute("className", course.getCourseClass().getTitle());
        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        model.addAttribute("startTimeID", persistView.getTeacherViewPersist().getCourseTimeSchedule().getStartPoints().get(teacherView.getStartTimeID()).getTimeDisplay());
        model.addAttribute("keys", KeyGeneratorController.GenerateKeys(teacherView.getNoOfLessons()));

        return "teacher/keygenerator";
    }
}
