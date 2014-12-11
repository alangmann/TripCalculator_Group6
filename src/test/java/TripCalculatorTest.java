
import static org.hamcrest.CoreMatchers.equalTo;
import bl.Calculator;
import bl.Car;
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
    public void testIfDieselWithConsumption5l100kmonAMotorwayWithASlope5mon10KmReturns1Komma3256625() {
        Route route = new Route(10, 5, 1, RouteType.HIGHWAY);
        Car car = new Car(5, 0, FuelType.DIESEL);
        assertThat((float)this.cal.calculateCo2Consumption(route, car), equalTo(1.3256625f));
    }

    @Test
    public void testIfPatrolWith20kmReturns0Point53WhenSlopeIs1Return1Point0192519199999999() {
        // km x CO2 x slope x Factor of Route type
        Car car = new Car(1.1, 1, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(1.0192519199999999));
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