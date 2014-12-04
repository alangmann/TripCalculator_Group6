
import static org.hamcrest.CoreMatchers.equalTo;
import bl.Calculator;
import bl.Route;
import bl.Vehicle;
import enums.FuelType;
import enums.RouteType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TripCalculatorTest {

    Calculator cal = new Calculator();

    @Test (expected = IllegalArgumentException.class)
    public void testIfCalculateRouteWithDistanceMinusOneReturnsError() {
        cal.calculateCo2Consumption(new Route(-1, 0, 0, RouteType.HIGHWAY), new Vehicle(6, 1, FuelType.DIESEL));
    }

    @Test
    public void testIfDieselWith10KmReturnsZeroKomma236() {
        assertThat(this.cal.calculateCo2Consumption(new Route(10, 0, 0, RouteType.HIGHWAY), new Vehicle(6, 1, FuelType.DIESEL)), equalTo(0.236));
    }

    @Test
    public void testIfPatrolWith20kmReturns0Point53WhenSlopeIs1Return0Point53() {
        Vehicle vehicle = new Vehicle(1.1, 50, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        assertThat(this.cal.calculateCo2Consumption(route, vehicle), equalTo(0.53));
    }

    @Test
    public void testIfDieselWithSlopeLower5PercentReturnsZero() {
        Route route = new Route(12, -6, 0, RouteType.HIGHWAY);
        Vehicle veh = new Vehicle(12.2, 1, FuelType.DIESEL);

        double erg = cal.calculateCo2Consumption(route, veh);
        assertThat(erg, equalTo(0.0));
    }

    @Test
    public void testIfDieselWithSlope0Returns0Point2832() {
        Route route = new Route(12, 0, 0, RouteType.HIGHWAY);
        Vehicle veh = new Vehicle(12.2, 1, FuelType.DIESEL);
        assertThat(cal.calculateCo2Consumption(route, veh), equalTo(0.2832));
    }

}