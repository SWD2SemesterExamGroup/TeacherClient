package dk.kea.teacher.artifacts;

import dk.kea.teacher.artifacts.Controllers.StudentController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;

/**
 * Help: https://dzone.com/articles/unit-and-integration-tests-in-spring-boot
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest
{
    @Test
    public void indexTest() {
        // Assign
        StudentController _controller = new StudentController();
        // Act
        String result = _controller.home(new ConcurrentModel());
        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "index");
    }

    @Test
    public void attendanceKeyPostTest() {
        // Assign
        StudentController _controller = new StudentController();
        // Act
        String result = _controller.attendanceKey(1, "", new ConcurrentModel());
        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "index");
    }
}
