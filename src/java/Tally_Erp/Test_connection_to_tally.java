/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tally_Erp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Lenovo
 */
public class Test_connection_to_tally {
    public static void main(String[] args) {
        String tallyURL = "http://localhost:9000";

        // Minimal XML to request Tally company list (just to test connection)
        String xmlRequest = "<ENVELOPE>\n" +
"    <HEADER>\n" +
"        <TALLYREQUEST>Export Data</TALLYREQUEST>\n" +
"    </HEADER>\n" +
"    <BODY>\n" +
"        <EXPORTDATA>\n" +
"            <REQUESTDESC>\n" +
"                <REPORTNAME>Balance Sheet</REPORTNAME>\n" +
"            </REQUESTDESC>\n" +
"        </EXPORTDATA>\n" +
"    </BODY>\n" +
"</ENVELOPE>";

        try {
            URL url = new URL(tallyURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            // Send XML to Tally
            OutputStream os = connection.getOutputStream();
            os.write(xmlRequest.getBytes());
            os.flush();

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String responseLine;
            StringBuilder response = new StringBuilder();

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine).append("\n");
            }

            connection.disconnect();
            System.out.println("Connected to Tally!");
            System.out.println("Response from Tally:");
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println("Could not connect to Tally.");
            e.printStackTrace();
        }
    }
}
    

