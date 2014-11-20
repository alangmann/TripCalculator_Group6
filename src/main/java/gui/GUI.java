package gui;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
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

    }

    private void initComponents()
    {
        Container cont = getContentPane();
        setLayout(new BorderLayout());
    }

    public static void main(String[] args)
    {
        new GUI().setVisible(true);
    }
}
