
import static org.hamcrest.CoreMatchers.equalTo;
import bl.Calculator;
import bl.Route;
import bl.Vehicle;
import enums.FuelType;
import enums.RouteType;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TripCalculatorTest {

    Calculator cal = new Calculator();

    @Test (expected = IllegalArgumentException.class)
    public void testIfCalculateRouteWithDistanceMinusOneReturnsError() {
        cal.calculateCo2ConsumptionBasedOnDistance(new Route(-1, 0, 0, RouteType.HIGHWAY), new Vehicle(6, 1, FuelType.DIESEL));
    }

    @Test
    public void testIfDieselWith10KmReturnsZeroKomma236() {
        assertThat(this.cal.calculateCo2ConsumptionBasedOnDistance(new Route(10, 0, 0, RouteType.HIGHWAY), new Vehicle(6, 1, FuelType.DIESEL)), equalTo(0.236));
    }


}