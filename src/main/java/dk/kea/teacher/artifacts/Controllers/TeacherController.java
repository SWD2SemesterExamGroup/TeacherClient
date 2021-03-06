package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsPersister;
import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsProducer;
import dk.kea.teacher.artifacts.Controllers.Persisters.ViewPersister;
import dk.kea.teacher.artifacts.Helpers.KeyGeneratorController;
import dk.kea.teacher.artifacts.Models.KeyGeneratorView;
import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.CourseTimeSchedule;
import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The teacher Controller
 */
@Controller
public class TeacherController
{
    // Fields
    @Autowired
    private JmsProducer producer;
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
        // Get latest message from ActiveMQ and persist object in new view
        persistView.setTeacherViewPersist(new TeacherViewPersist(persist.getLatest()));
        // Initiate teacher model
        TeacherModel teacher = persistView.getTeacherViewPersist().getTeacherModel();

        // Is still used but should be fixed
        KeyGeneratorView view = new KeyGeneratorView(teacher.getTeacherID());
        view.setListCourses(teacher.getCourses());
        view.setTeacherModel(teacher);

        // Add view data
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

    /**
     * Ready Model for view
     * @param model
     * @return String, view path
     */
    @RequestMapping(value = "/key-gen", method = RequestMethod.GET)
    public String generate(Model model) {
        TeacherModel teacher = persistView.getTeacherViewPersist().getTeacherModel();

        model.addAttribute("teacherID", teacher.getTeacherID());
        model.addAttribute("key", "");

        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        model.addAttribute("generated", false);
        return "teacher/keygenerator";
    }

    /**
     * Post Key Generation view
     * @param teacherView
     * @param model
     * @return String, view path
     * @throws Exception
     */
    @RequestMapping(value = "/key-gen", method = RequestMethod.POST)
    public String generate(@ModelAttribute TeacherViewPersist teacherView, Model model) throws Exception {
        BaseCModel course = persistView.getTeacherViewPersist().getTeacherModel().getCourseBy(persistView.getTeacherViewPersist().getCourseID());
        persistView.getTeacherViewPersist().setNoOfLessons(teacherView.getNoOfLessons());
        persistView.getTeacherViewPersist().setStartTimeID(teacherView.getStartTimeID());
        model.addAttribute("courseName", course.getTitle());
        model.addAttribute("className", course.getCourseClass().getTitle());
        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        model.addAttribute("startTimeID", persistView.getTeacherViewPersist().getCourseTimeSchedule().getStartPoints().get(teacherView.getStartTimeID()).getTimeDisplay());
        // Generate keys by lessons
        List<String> keys = KeyGeneratorController.GenerateKeys(teacherView.getNoOfLessons());
        model.addAttribute("keys", keys);
        persistView.getTeacherViewPersist().setClassID(teacherView.getClassID()); // Persist class id

        // add keys and timestamp to the persistView
        persistView.addKeysToList(
                keys,
                new CourseTimeSchedule().getStartPoints().get(teacherView.getStartTimeID()),
                teacherView.getNoOfLessons());
        producer.send(persistView);

        Thread.sleep(3500);
        System.out.println(persistView.getResponseMessage());
        model.addAttribute("generated", true);

        return "teacher/keygenerator";
    }
}