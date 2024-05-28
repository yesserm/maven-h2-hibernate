package com.yesser.app.views;

import com.yesser.app.forms.Home;

import javax.swing.*;
import java.awt.*;

public class MiVentana extends JFrame {
    static JDesktopPane desktopPane;
    public MiVentana(){
        setBounds(500,500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MiPanel miPanel = new MiPanel();
        getContentPane().add(miPanel, BorderLayout.NORTH);
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        AgregarAVentana(Home.getInstancia());
        setVisible(true);
    }

    public static void AgregarAVentana(JInternalFrame ventanaInterna) {
        desktopPane.add(ventanaInterna);
        Dimension dskSize = desktopPane.getSize();
        Dimension frmSize = ventanaInterna.getSize();
        ventanaInterna.setLocation(0,0);
        ventanaInterna.setVisible(true);
    }


}
