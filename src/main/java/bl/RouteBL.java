package bl;

import enums.RouteType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Veronika on 27.11.2014.
 */
public class RouteBL
{
    private LinkedList<Route> routes;

    public RouteBL()
    {
        this.routes = new LinkedList<Route>();
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




}
