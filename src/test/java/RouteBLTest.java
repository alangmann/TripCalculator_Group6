/**
 * Created by dominik on 04.12.2014.
 */
import static org.hamcrest.CoreMatchers.equalTo;
import bl.RouteBL;
import bl.Route;
import bl.Vehicle;
import enums.FuelType;
import enums.RouteType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;

public class RouteBLTest {
    private RouteBL m_RouteBL;

    @Before
    public void init() {
        this.m_RouteBL = new RouteBL(new LinkedList<Route>());
    }

    @Test
    public void testIfReadOutOfFileReturnsNoError() throws IOException {
        boolean load = m_RouteBL.loadRoute();
        Assert.assertThat(load, equalTo(true));
    }
}
