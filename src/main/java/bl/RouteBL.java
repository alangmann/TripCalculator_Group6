package bl;

import enums.DayOfTheWeek;
import enums.RouteType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

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
        this.prices = new LinkedList<Price>();
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
                line=line.replaceAll("," , ".");

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
            switch(dayToInt(spl[0])) {
                case 0: day = DayOfTheWeek.Monday;break;
                case 1: day = DayOfTheWeek.Tuesday;    break;
                case 2: day=DayOfTheWeek.Wednesday;        break;

                case 3: day = DayOfTheWeek.Thursday;break;
                case 4: day = DayOfTheWeek.Friday;break;
                case 5: day = DayOfTheWeek.Saturday;break;
                case 6: day = DayOfTheWeek.Sunday;break;
            }

            double priceDiesel = Double.parseDouble(spl[1]);
            double pricePetrol = Double.parseDouble(spl[2]);
            Price pr = new Price(day, pricePetrol, priceDiesel);
            this.prices.add(pr);

        }
        return true;

    }

    public int dayToInt(String day)
    {
        if(day.equals("Montag"))
        {
          return 0;
        } else if(day.equals("Dienstag"))
        {
            return 1;
        }else if(day.equals("Mittwoch"))
        {
            return 2;
        }else if(day.equals("Donnerstag"))
        {
            return 3;
        }else if(day.equals("Freitag"))
        {
            return 4;
        }else if(day.equals("Samstag"))
        {
            return 5;
        }else if(day.equals("Sonntag"))
        {
            return 6;
        }
        return -1;

    }

    public Price getPriceOfAktualDay() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.GERMAN);
        String day = sdf.format(d);

        switch(dayToInt(day))
        {
            case 0: return this.prices.get(0);
            case 1: return this.prices.get(1);
            case 2: return this.prices.get(2);
            case 3: return this.prices.get(3);
            case 4: return this.prices.get(4);
            case 5: return this.prices.get(5);
            case 6: return this.prices.get(6);
        }
        return null;
    }

    public LinkedList<Price> getPrices() { return this.prices; }

}
