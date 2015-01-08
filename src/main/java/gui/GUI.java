package gui;

import bl.*;
import enums.FuelType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Jule on 13.11.2014.
 */
public class GUI extends JFrame
{
    private RouteBL routeBL;
    private LinkedList<Route> routes = new LinkedList<Route>();
    private LinkedList<Price> prices = new LinkedList<Price>();
    private Calculator m_Calculator;

    private String[] texts_car =
            {
                  "Fuel Consumption", "Fuel Type", "Cargo"
            };

    private String[] texts_truck =
            {
                  "Fuel Consumption", "Fuel Type", "Cargo", "Axles", "adBlue"
            };

    public GUI()
    {
        super("Trip Calculator");
        this.m_Calculator = new Calculator();
        initComponents();
        setSize(545, 360);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDesign("javax.swing.plaf.nimbus.NimbusLookAndFeel");


        routeBL = new RouteBL();
        try {
            routeBL.loadRoute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        routes = routeBL.getRoutes();

        for(Route r : routes)
        {
            System.out.println(r.toString());
        }

        try {
            this.routeBL.readSpritDB("sprit_db.csv");

            this.prices = this.routeBL.getPrices();
            for (Price p : prices) {
                System.out.println(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initComponents()
    {
        Container cont = getContentPane();
        setLayout(new BorderLayout());

        //JPanels
        panel_main = new JPanel(new BorderLayout());
        panel_main.setBorder(new EmptyBorder(5, 5, 5, 5));

        titledBorder = new TitledBorder("Type of Vehicle");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        panel_north = new JPanel(new GridLayout(1, 2, 10, 0));
        panel_north.setBorder(titledBorder);

        panel_south = new JPanel(new GridLayout(2, 1));

        panel_south_results = new JPanel(new GridLayout(1, 2));

        panel_center = new JPanel(new GridLayout(1, 2));

        panel_center_car = new JPanel(new GridLayout(texts_car.length, 2, 0, 35));
        panel_center_car.setBorder(new TitledBorder("Car"));

        panel_center_truck = new JPanel(new GridLayout(texts_truck.length, 2, 0,5));
        panel_center_truck.setBorder(new TitledBorder("Truck"));


        //JRadioButtons & ButtonGroup
        bg_vehicle = new ButtonGroup();
        rb_car = new JRadioButton("Car");
        rb_car.addActionListener(new MyActionListener());
        rb_car.setActionCommand("car");
        rb_car.setSelected(true);
        rb_car.setHorizontalAlignment(JRadioButton.CENTER);
        rb_truck = new JRadioButton("Truck");
        rb_truck.setActionCommand("truck");
        rb_truck.addActionListener(new MyActionListener());
        rb_truck.setHorizontalAlignment(JRadioButton.CENTER);

        bg_vehicle.add(rb_car);
        bg_vehicle.add(rb_truck);

        //JTextFields
        tf_car_cargo = new JTextField();
        tf_car_consumption = new JTextField();

        tf_truck_consumption = new JTextField();
        tf_truck_axles = new JTextField();
        tf_truck_cargo = new JTextField();


        //JLabels
        lbs_car = new JLabel[texts_car.length];
        lbs_truck = new JLabel[texts_truck.length];

        for (int i = 0; i < texts_car.length; i++)
        {
            JLabel lb = new JLabel(texts_car[i]);
            lbs_car[i] = lb;
        }

        for (int i = 0; i < texts_truck.length; i++)
        {
            JLabel lb = new JLabel(texts_truck[i]);
            lbs_truck[i] = lb;
        }

        lb_co2_result = new JLabel("CO2 Consumption: ");
        lb_co2_result.setHorizontalAlignment(JLabel.CENTER);
        lb_routeCost_result = new JLabel("Route Cost: ");
        lb_routeCost_result.setHorizontalAlignment(JLabel.CENTER);

        //JButton
        bt_calc = new JButton("Calculate");
        bt_calc.addActionListener(new MyActionListener());

        //JCheckBox
        checkBox_adBlue = new JCheckBox();
        checkBox_adBlue.setSelected(false);

        //JComboBox
        cb_car_fuelTypes = new JComboBox<FuelType>();
        cb_truck_fuelTypes = new JComboBox<FuelType>();

        for (int i = 0; i < FuelType.values().length; i++)
        {
          cb_car_fuelTypes.addItem(FuelType.values()[i]);
          cb_truck_fuelTypes.addItem(FuelType.values()[i]);
        }


        //Adding Components
        cont.add(panel_main, BorderLayout.CENTER);

        //Adding to Panel Main
        panel_main.add(panel_north, BorderLayout.NORTH);
        panel_main.add(panel_center, BorderLayout.CENTER);
        panel_main.add(panel_south, BorderLayout.SOUTH);

        //Adding to Panel North
        panel_north.add(rb_car);
        panel_north.add(rb_truck);

        //Adding to Panel South
        panel_south.add(bt_calc);
        panel_south.add(panel_south_results);

        //Adding to Panel Center
        panel_center.add(panel_center_car);
        panel_center.add(panel_center_truck);

        //Adding to Panel Center Car
        panel_center_car.add(lbs_car[0]);
        panel_center_car.add(tf_car_consumption);
        panel_center_car.add(lbs_car[1]);
        panel_center_car.add(cb_car_fuelTypes);
        panel_center_car.add(lbs_car[2]);
        panel_center_car.add(tf_car_cargo);

        //Adding to Panel South Results
        panel_south_results.add(lb_co2_result);
        panel_south_results.add(lb_routeCost_result);

        //Adding to Panel Center Truck
        //"Fuel Consumption", "Fuel Type", "Cargo", "Axles", "adBlue"
        panel_center_truck.add(lbs_truck[0]);
        panel_center_truck.add(tf_truck_consumption);
        panel_center_truck.add(lbs_truck[1]);
        panel_center_truck.add(cb_truck_fuelTypes);
        panel_center_truck.add(lbs_truck[2]);
        panel_center_truck.add(tf_truck_cargo);
        panel_center_truck.add(lbs_truck[3]);
        panel_center_truck.add(tf_truck_axles);
        panel_center_truck.add(lbs_truck[4]);
        panel_center_truck.add(checkBox_adBlue);

    }


    private void enableTruckControls(boolean enable)
    {
        panel_center_truck.setEnabled(false);
    }

    private void setDesign(String design)
    {
        try
        {
            UIManager.setLookAndFeel(design);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex)
        {
            System.out.println("Error in GUI: GUI(): " + ex.getMessage());
        }
    }

    class MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            onCalculateCO2();
        }
    }

    private void calculatePrice() {
        Price price = this.routeBL.getPriceOfAktualDay();
    }

    private void onCalculateCO2() {

        try {
        if (rb_car.isSelected()) {

                int cargo = Integer.parseInt(this.tf_car_cargo.getText());
                double consumption = Double.parseDouble(this.tf_car_consumption.getText());
                FuelType fueltype = (FuelType) this.cb_car_fuelTypes.getSelectedItem();

                Car car = new Car(consumption, cargo, fueltype);

                double sum = 0;
                for (Route r : this.routes) {
                    sum += this.m_Calculator.calculateCo2Consumption(r, car);
                }

                this.lb_co2_result.setText("Result: " + sum);

        }
        else {
            int cargo = Integer.parseInt(this.tf_truck_cargo.getText());
            double consumption = Double.parseDouble(this.tf_truck_consumption.getText());
            FuelType fueltype = (FuelType) this.cb_truck_fuelTypes.getSelectedItem();
            int axels = Integer.parseInt(this.tf_truck_axles.getText());
            boolean b = this.checkBox_adBlue.isSelected();
            Truck tr = new Truck(consumption,cargo,fueltype,axels,b);
            double sum = 0;
            for (Route r : this.routes) {
                sum += this.m_Calculator.calculateCo2Consumption(r, tr);
            }

            this.lb_co2_result.setText("Result: " + sum);
        }
        } catch(Exception e) {
            this.lb_co2_result.setText("There was an error!");
        }

    }

    public static void main(String[] args)
    {
        new GUI().setVisible(true);
    }

    //JPanels
    private JPanel panel_main;
    private JPanel panel_north;
    private JPanel panel_center;
    private JPanel panel_south;
    private JPanel panel_south_results;
    private JPanel panel_center_car;
    private JPanel panel_center_truck;

    //JRadioButtons
    private ButtonGroup bg_vehicle;
    private JRadioButton rb_car;
    private JRadioButton rb_truck;

    //JCheckBoxes
    private JCheckBox checkBox_adBlue;

    //TitledBorder
    private TitledBorder titledBorder;

    //JLabels
    private JLabel[] lbs_car;
    private JLabel[] lbs_truck;
    private JLabel lb_co2_result;
    private JLabel lb_routeCost_result;


    //JTextFields
    private JTextField tf_car_cargo;
    private JTextField tf_car_consumption;

    private JTextField tf_truck_cargo;
    private JTextField tf_truck_consumption;
    private JTextField tf_truck_axles;

    //JComboBox
    private JComboBox<FuelType> cb_car_fuelTypes;
    private JComboBox<FuelType> cb_truck_fuelTypes;

    //JButtons
    private JButton bt_calc;



}
