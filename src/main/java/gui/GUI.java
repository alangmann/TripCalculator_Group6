package gui;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.BufferedReader;

/**
 * Created by Jule on 13.11.2014.
 */
public class GUI extends JFrame
{
    public GUI()
    {
        super("Trip Calculator");
        initComponents();
        setSize(400, 300);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDesign("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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


        //JRadioButtons & ButtonGroup
        bg_vehicle = new ButtonGroup();
        rb_car = new JRadioButton("Car");
        rb_car.setHorizontalAlignment(JRadioButton.CENTER);
        rb_truck = new JRadioButton("Truck");
        rb_truck.setHorizontalAlignment(JRadioButton.CENTER);

        bg_vehicle.add(rb_car);
        bg_vehicle.add(rb_truck);

        //Adding Components
        cont.add(panel_main, BorderLayout.CENTER);

        //Adding to Panel Main
        panel_main.add(panel_north, BorderLayout.NORTH);

        //Adding to Panel North
        panel_north.add(rb_car);
        panel_north.add(rb_truck);
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

    public static void main(String[] args)
    {
        new GUI().setVisible(true);
    }

    //JPanels
    private JPanel panel_main;
    private JPanel panel_north;

    //JRadioButtons
    private ButtonGroup bg_vehicle;
    private JRadioButton rb_car;
    private JRadioButton rb_truck;

    //TitledBorder
    private TitledBorder titledBorder;


}
