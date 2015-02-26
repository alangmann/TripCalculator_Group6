/**
 * Created by dominik on 04.12.2014.
 */
import static org.hamcrest.CoreMatchers.equalTo;
import bl.RouteBL;
import bl.Route;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import java.util.LinkedList;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:resources/spring-di-sample-annotation-context.xml")
public class RouteBLTest
{
    private RouteBL m_RouteBL;

    @Before
    public void init()
    {
        this.m_RouteBL = new RouteBL();
    }

    @Test
    public void testIfReadOutOfFileReturnsNoError() throws IOException
    {
        boolean load = m_RouteBL.loadRoute();
        Assert.assertThat(load, equalTo(true));
    }

    @Test
    public void testIfReadOutOfFileSpritsReturnsNoError() throws IOException
    {
       boolean load = m_RouteBL.readSpritDB("sprit_db.csv");
        Assert.assertThat(load,equalTo(true));
    }

    @Test (expected = IOException.class)
    public void testIfReadOutOfFileSpritsFails() throws IOException
    {
        boolean load = m_RouteBL.readSpritDB("sprit");
    }
}
