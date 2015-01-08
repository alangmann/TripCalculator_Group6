package bl;

import enums.DayOfTheWeek;
import enums.RouteType;

import java.io.*;
import java.time.DayOfWeek;
import java.util.LinkedList;

/**
 * Created by Veronika on 27.11.2014.
 */
public class RouteBL
{
    private LinkedList<Route> routes;
    private LinkedList<Price> prices;

    public RouteBL()
    {
        this.routes = new LinkedList<Route>();
        this.prices = new LinkedList<>();
    }

    public void setRoutes(LinkedList<Route> routes) {
        this.routes = routes;
    }

    public boolean loadRoute() throws IOException
    {
        String pfad = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator +"routes.csv";

        BufferedReader br = new BufferedReader(new FileReader(new File(pfad)));
        String line="";

        try
        {
            while((line=br.readLine())!=null)
            {
                line=line.replaceAll(",",".");

                String[] splits = line.split(";");
                //45;96,8;CountryRoad;0
                // public Route(double distance, double slope, double specialFee, RouteType typeOfRoute) {
                //km;slope;routtype;special fee
                if (splits[2].equals("Highway"))
                {
                    routes.add(new Route(Double.parseDouble(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[3]), RouteType.HIGHWAY));
                }
                else if(splits[2].equals("CountryRoad"))
                {
                    routes.add(new Route(Double.parseDouble(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[3]), RouteType.COUNTRYROAD));
                }
                else if(splits[2].equals("GravelRoad"))
                {
                    routes.add(new Route(Double.parseDouble(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[3]), RouteType.GRAVELROAD));
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }

    public LinkedList<Route> getRoutes() {
        return routes;
    }

    public boolean readSpritDB(String filename) throws IOException {
        String pfad = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator + filename;
        BufferedReader br = new BufferedReader(new FileReader(new File(pfad)));
        br.readLine();
        String zeile="";
        while((zeile = br.readLine()) != null) {
            zeile = zeile.replace(",", ".");
            String[] spl = zeile.split(";");
            DayOfTheWeek day = null;
            switch(spl[0]) {
                case "Monday": day = DayOfTheWeek.Monday;
                case "Tuesday": day = DayOfTheWeek.Tuesday;
                case "Wednesday": day=DayOfTheWeek.Wednesday;
                case "Thursday": day = DayOfTheWeek.Thursday;
                case "Friday": day = DayOfTheWeek.Friday;
                case "Saturday": day = DayOfTheWeek.Saturday;
                case "Sunday": day = DayOfTheWeek.Sunday;
            }

            double priceDiesel = Double.parseDouble(spl[1]);
            double pricePetrol = Double.parseDouble(spl[2]);
            Price pr = new Price(day, pricePetrol, priceDiesel);
            this.prices.add(pr);

        }
        return true;

    }

    public LinkedList<Price> getPrices() { return this.prices; }

}
