package Tally_Erp;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TallyConnector{ 

    public static void main(String[] args) {
        try {
            // Tally's HTTP server URL
            String tallyURL = "http://localhost:9000";

            // Sample request to get list of companies
            String xmlRequest = "<ENVELOPE>" +
                    "<HEADER>" +
                    "<TALLYREQUEST>Export Data</TALLYREQUEST>" +
                    "</HEADER>" +
                    "<BODY>" +
                    "<EXPORTDATA>" +
                    "<REQUESTDESC>" +
                    "<REPORTNAME>List of Companies</REPORTNAME>" +
                    "</REQUESTDESC>" +
                    "</EXPORTDATA>" +
                    "</BODY>" +
                    "</ENVELOPE>";

            // Send the request
            URL url = new URL(tallyURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml");

            OutputStream os = conn.getOutputStream();
            os.write(xmlRequest.getBytes());
            os.flush();

            // Read the response
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
