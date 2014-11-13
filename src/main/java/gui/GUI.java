package gui;

import javax.swing.*;
import java.awt.*;

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
