/**
 * Created by dominik on 04.12.2014.
 */
import static org.hamcrest.CoreMatchers.equalTo;
import bl.RouteBL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-di-sample-annotation-context.xml")
public class RouteBLTest
{
    @Resource
    private ApplicationContext context;

    @Before
    public void init()
    {
       RouteBL bl = context.getBean(RouteBL.class);
    }

    @Test
    public void testIfReadOutOfFileReturnsNoError() throws IOException
    {
        RouteBL bl = context.getBean(RouteBL.class);
        boolean load = bl.loadRoute();
        Assert.assertThat(load, equalTo(true));
    }

    @Test
    public void testIfReadOutOfFileSpritsReturnsNoError() throws IOException
    {
        RouteBL bl = context.getBean(RouteBL.class);
       boolean load = bl.readSpritDB("sprit_db.csv");
        Assert.assertThat(load,equalTo(true));
    }

    @Test (expected = IOException.class)
    public void testIfReadOutOfFileSpritsFails() throws IOException
    {
        RouteBL bl = context.getBean(RouteBL.class);
        boolean load = bl.readSpritDB("sprit");
    }
}
