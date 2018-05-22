package dk.kea.teacher.artifacts.Controllers;

import dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage.JmsProducer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dk.kea.teacher.artifacts.LoginPackage.LoginModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Test class for testing web layer of client
 * Ref.:
 * Post Model: https://stackoverflow.com/questions/47288674/spring-test-how-to-pass-model-into-controller-post-method
 * endpoint test: https://stackoverflow.com/questions/29053974/how-to-write-a-unit-test-for-a-spring-boot-controller-endpoint
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest
{
    private MockMvc mock;
    @MockBean
    private JmsProducer producer;
    @MockBean
    private Model model;
    @Autowired
    private HomeController controller;

    @Before
    public void setUp() throws Exception
    {
        this.mock = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void homeGet() throws Exception
    {
        // Assign
        controller = mock(HomeController.class);
        // Act Assert
        this.mock
                .perform(get("/"))
                .andDo(print()) // if you wnat no print then comment out this field
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("login"));
    }

    @Test
    public void homePost() throws Exception
    {

    }

    @Test
    public void homeIndex() throws Exception
    {
    }

    @Test
    public void generateKey() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
}