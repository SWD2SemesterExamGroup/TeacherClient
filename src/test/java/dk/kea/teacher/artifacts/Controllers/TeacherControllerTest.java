package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsPersister;
import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsProducer;
import dk.kea.teacher.artifacts.ArtifactsApplication;
import dk.kea.teacher.artifacts.Controllers.Persisters.ViewPersister;
import dk.kea.teacher.artifacts.Helpers.KeyGeneratorController;
import dk.kea.teacher.artifacts.Helpers.RandomString;
import dk.kea.teacher.artifacts.Models.Views.BaseCModel;
import dk.kea.teacher.artifacts.Models.Views.Teacher.TeacherViewPersist;
import dk.kea.teacher.artifacts.Models.Views.TeacherModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest
{
    private MockMvc mock;
    @Autowired
    private TeacherController controller;
    @MockBean
    private JmsProducer producer;

    private MockMvc mockPersist;
    @MockBean
    private JmsPersister persist;
    @MockBean
    private ViewPersister persistView;
    @MockBean
    private Model model;
    @MockBean
    private TeacherModel teacher;

    @Before
    public void setUp() throws Exception
    {
        this.mock = MockMvcBuilders.standaloneSetup(controller).build();
        this.mockPersist = MockMvcBuilders.standaloneSetup(persist).build();
    }

    /*
        @RequestMapping(value = "/coursephase", method = RequestMethod.GET)
        public String pickCourse(Model model) {
    */
    @Test
    public void pickCourseGet() throws Exception
    {
        // Assign
        teacher.setTeacherID(1);
        // Mock setup
        Mockito.when(persist.getLatest()).thenReturn(teacher);
        Mockito.when(persistView.getTeacherViewPersist()).thenReturn(new TeacherViewPersist(teacher));

        model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        controller = mock(TeacherController.class);

        // Act assert
        this.mock
                .perform(get("/coursephase"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("teacher/pickcourse"));
    }

    @Test
    public void pickCourse1Post() throws Exception
    {
        // Assign
        TeacherViewPersist teacherView = new TeacherViewPersist(teacher);
        teacherView.setCourseID(1);
        List<BaseCModel> courses = new ArrayList<>();
        courses.add(
                new BaseCModel(
                        1,
                        "Course 1",
                        new BaseCModel(1, "Class 1")));
        TeacherModel teacher = new TeacherModel(1,"Tom", courses);
        Mockito.when(persistView.getTeacherViewPersist()).thenReturn(teacherView);

        //model.addAttribute("teacherView", persistView.getTeacherViewPersist());
        model.addAttribute("teacherID", 1);
        model.addAttribute("key", "");

        // Act & Assert
        this.mock
                .perform(post("/coursephase"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("redirect:/key-gen"));
    }

    @Test
    public void generate() throws Exception
    {
    }

    @Test
    public void generate1() throws Exception
    {
    }
    @After
    public void tearDown() throws Exception
    {
    }
}