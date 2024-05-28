package com.yesser.app.forms;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Home extends JInternalFrame {
    static Home myClient;

    private Home(){
        super("Client", true, true, true);
        inicializarCliente();
        myClient = this;
    }

    private void inicializarCliente(){
        setToolTipText("Datos del cliente");
        setOpaque(true);
        setBorder(new LineBorder(new Color(0,0,0)));
        setIconifiable(false);

        JLabel text = new JLabel("Home");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(null, "Home",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                null, Color.black
        ));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(text);

        setName("Cliente");
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().setPreferredSize(new Dimension(800, 400));
        pack();
    }

    public static Home getInstancia(){
        return null == myClient ? (new Home()) : myClient;
    }
}
