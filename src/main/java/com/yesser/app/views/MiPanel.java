package com.yesser.app.views;

import com.yesser.app.util.Reportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class MiPanel extends JPanel {
    public MiPanel(){
        // Menus y barra de menus
        JMenuBar miBarra = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuEditor = new JMenu("Ediciones");
        JMenu menuHerramientas = new JMenu("Herramientas");
        JMenu opciones = new JMenu("Opciones");

        ActionListener abrirListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrir");
                try {
                    Reportes.generarReporte("reportes/hola.jrxml");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        // Creacion de los JMenuItem
        JMenuItem abrir = new JMenuItem("Abrir");
        abrir.setMnemonic('A');
        abrir.setAccelerator(KeyStroke.getKeyStroke('A',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),
                false));
        abrir.addActionListener(abrirListener);
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem guardarComo = new JMenuItem("Guardar Como");
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem pegar = new JMenuItem("Pegar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem generales = new JMenuItem("Generales");
        JMenuItem opcion1 = new JMenuItem("Opcion 1");
        JMenuItem opcion2 = new JMenuItem("Opcion 2");

        // agregar los items a los menus
        menuArchivo.add(abrir);
        menuArchivo.add(guardar);
        menuArchivo.add(guardarComo);

        menuEditor.add(copiar);
        menuEditor.add(pegar);
        menuEditor.add(cortar);
        menuEditor.addSeparator();
        menuEditor.add(opciones);

        menuHerramientas.add(generales);

        opciones.add(opcion1);
        opciones.add(opcion2);

        // agregar todos los menus a la barra
        miBarra.add(menuArchivo);
        miBarra.add(menuEditor);
        miBarra.add(menuHerramientas);

        // agregar la barra menus al panel
        add(miBarra);
    }

    private static void mostrarVentana(JInternalFrame frm) {
        try {
            //Agrenga  la Ventana al escritorio si la ventana no esta visible
            if (!frm.isVisible()) {
                MiVentana.AgregarAVentana(frm);
            } else {
                // si la ventana esta visible pero debajo de otras ventanas se mueve al frente y se selecciona
                frm.moveToFront();
                if (!frm.isSelected()) {
                    frm.setSelected(true);
                }
            }

        } catch (PropertyVetoException e) {
            System.out.println("Error al activar la ventana Tipo de cuenta");
        }

    }
}