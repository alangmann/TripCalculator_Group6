package gui;

import bl.Route;
import bl.RouteBL;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Jule on 13.11.2014.
 */
public class GUI extends JFrame
{
    private RouteBL routeBL;
    private LinkedList<Route> routes = new LinkedList<Route>();

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
        initComponents();
        setSize(545, 290);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDesign("javax.swing.plaf.nimbus.NimbusLookAndFeel");


        routeBL = new RouteBL();
        try {
            routeBL.loadRoute();
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

        panel_center = new JPanel(new GridLayout(1, 2));

        panel_center_car = new JPanel(new GridLayout(texts_car.length, 2, 0, 20));
        panel_center_car.setBorder(new TitledBorder("Car"));

        panel_center_truck = new JPanel();
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

        //JLabels
        lbs_car = new JLabel[texts_car.length];
        tfs_car = new JTextField[texts_car.length];

        for (int i = 0; i < texts_car.length; i++)
        {
            JLabel lb = new JLabel(texts_car[i]);
            JTextField tf = new JTextField();

            lbs_car[i] = lb;
            tfs_car[i] = tf;

            panel_center_car.add(lbs_car[i]);
            panel_center_car.add(tfs_car[i]);
        }

        //Adding Components
        cont.add(panel_main, BorderLayout.CENTER);

        //Adding to Panel Main
        panel_main.add(panel_north, BorderLayout.NORTH);
        panel_main.add(panel_center, BorderLayout.CENTER);

        //Adding to Panel North
        panel_north.add(rb_car);
        panel_north.add(rb_truck);

        //Adding to Panel Center
        panel_center.add(panel_center_car);
        panel_center.add(panel_center_truck);
    }

    private void enableCarControls(boolean enable)
    {
        for (int i = 0; i < tfs_car.length; i++)
        {
            tfs_car[i].setText("");
            tfs_car[i].setEnabled(enable);
            panel_center_car.setEnabled(enable);
        }
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
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("car"))
            {
                enableCarControls(true);
                enableTruckControls(false);

            } else if (actionCommand.equals("truck"))
            {
                enableCarControls(false);
                enableTruckControls(true);
            }
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
    private JPanel panel_center_car;
    private JPanel panel_center_truck;

    //JRadioButtons
    private ButtonGroup bg_vehicle;
    private JRadioButton rb_car;
    private JRadioButton rb_truck;

    //TitledBorder
    private TitledBorder titledBorder;

    //JLabels
    private JLabel[] lbs_car;
    private JLabel[] lbs_truck;

    //JTextFields
    private JTextField[] tfs_car;
    private JTextField[] tfs_truck;




}
