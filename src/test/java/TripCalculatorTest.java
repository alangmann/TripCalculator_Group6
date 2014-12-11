
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
        //If you drive your vehicle fueled with diesel with an average consumption of 5l/100km on a motorway with a slope of 5m on 10 km, you generate a CO2 output of 1,3256625kg.
        Route route = new Route(10, 5, 1, RouteType.HIGHWAY);
        Car car = new Car(5, 1, FuelType.DIESEL);

        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(1.3256625));
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