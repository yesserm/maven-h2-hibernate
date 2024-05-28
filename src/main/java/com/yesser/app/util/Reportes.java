package com.yesser.app.util;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.jdbc.Work;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class Reportes {
    static JasperPrint jp;

    /**
     * @param url
     * @throws IOException
     * To generate a PDF report from a jrxml file
     */
    private static InputStream loadReport(URL url) throws Exception {
        FileInputStream is = readFile(url);
        if (is == null) {
            throw new Exception("Plantilla de reporte no encontrada. " + url);
        }
        return is;
    }

    /**
     * Reads a file from a URL
     * @param url
     * @return
     * @throws Exception
     */
    private static FileInputStream readFile(URL url) throws Exception {
        FileInputStream is = null;
        try {
            is = new FileInputStream(url.getFile());
        } catch (Exception e) {
            throw e;
        }
        return is;
    }

    static public void generarReporte(String ruta) throws Exception {
        try {

            // obtener la conexion de la sesion hibernate
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    try {
                        HashMap<String,Object> parametros = new HashMap<String,Object>();
                        parametros.put("Dir", Reportes.class.getResource(".")  );
                        // cargar el archivo jrxml
                        InputStream is = getClass().getClassLoader().getResourceAsStream(ruta);
                        JasperDesign jasperDesign = JRXmlLoader.load(is);
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        // llenar el reporte con los datos
                        jp = JasperFillManager.fillReport(jasperReport, parametros, connection);
                        // mostrar el reporte
                        JasperViewer jv = new JasperViewer(jp, false);
                        jv.setSize(1300, 1000);
                        jv.setZoomRatio((float)1.25);
                        jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                        jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                        jv.requestFocus();
                        jv.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Load the compiled report from the .jasper file
            /*InputStream is = Reportes.class.getClassLoader().getResourceAsStream(ruta);
            JasperDesign jd = JRXmlLoader.load(is);
            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            log.info("Reporte cargado: " + ruta);
            log.info("Parametros: " + parametros);
            log.info("Datos: " + datos);
            // Create a JasperPrint object
            jp = JasperFillManager.fillReport(jasperReport, parametros, datos);
            // Show the report
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle("Reporte");
            jv.requestFocus();
            jv.setVisible(true);*/
        } catch (Exception e) {
            throw e;
        }
    }

}
