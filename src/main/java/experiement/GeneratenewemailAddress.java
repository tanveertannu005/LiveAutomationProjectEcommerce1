package experiement;

import java.util.Date;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import java.util.Date;

public class GeneratenewemailAddress {
    public static void main(String[] args) {
        System.out.println(generatebrandnewEmailadress());
    }
    
    public static String generatebrandnewEmailadress() {
        Date date = new Date();
        String datesString = date.toString();
        String dateStringwithoutSpaces = datesString.replaceAll("\\s", "");
String datestringwithoutspacesAndcolons=        dateStringwithoutSpaces.replaceAll("\\:","");
 String brandNewEmail=    datestringwithoutspacesAndcolons+"@gmail.com";
 return brandNewEmail;
    }
    
}
