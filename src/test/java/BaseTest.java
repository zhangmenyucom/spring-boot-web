import com.taylor.ApplicationStarter;
import com.taylor.starter.StarterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class BaseTest {

    @Autowired
    private StarterService starterService;

    @Test
    public void starterTest() {
        String[] splitArray = starterService.split(",");
        System.out.println(Arrays.toString(splitArray));
    }

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void printRulest() {
        System.out.println("test");
    }
}