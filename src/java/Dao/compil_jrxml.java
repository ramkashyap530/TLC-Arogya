package Dao;
import java.io.File;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class compil_jrxml {
 
public static void main(String[] args) {
  try {
      String path =   new File(".").getCanonicalPath();
      
        String reportName = path +"/web/Main/Reports/OPd_refund.jrxml";

    String str[]=reportName.split("\\.");
    String reportName_jasper = str[0] + ".jasper";
    System.out.println("@@@@@"+reportName_jasper);
    JasperCompileManager.compileReportToFile(reportName, reportName_jasper);
 
  } catch (IOException | JRException e) {
   throw new RuntimeException("It's not possible to generate the jasper report.", e);
  }
  
}
 

}