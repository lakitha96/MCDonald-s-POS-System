/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.report.print;

import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author lakitha
 */
public class PrintReport extends JFrame {
    //connection

    public PrintReport() throws HeadlessException {
    }

    public void showReport() {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("/lk/mc/cashier/report/jasper/OrderInvoice.jrxml");
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, null);
            JRViewer jRViewer=new JRViewer(jasperPrint);
            
            this.add(jRViewer);
            this.setSize(900, 500);
            this.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(PrintReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
