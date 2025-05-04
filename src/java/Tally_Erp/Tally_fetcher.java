

package Tally_Erp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class Tally_fetcher {
    
  public ArrayList<HashMap<String, String>> fetchLedgersFromTally() {
        ArrayList<HashMap<String, String>> ledgerList = new ArrayList<>();

        try {
            // Tally XML request for ledgers
            String xmlRequest = "<ENVELOPE>" +
                    "<HEADER><TALLYREQUEST>Export Data</TALLYREQUEST></HEADER>" +
                    "<BODY><EXPORTDATA>" +
                    "<REQUESTDESC>" +
                    "<REPORTNAME>List of Ledgers</REPORTNAME>" +
                    "</REQUESTDESC>" +
                    "</EXPORTDATA></BODY></ENVELOPE>";

            // Connect to Tally
            URL url = new URL("http://localhost:9000");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml");

            OutputStream os = conn.getOutputStream();
            os.write(xmlRequest.getBytes());
            os.flush();

            // Read Tally response
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            conn.disconnect();

            // Parse response to extract ledger names
            String responseXML = response.toString();

            // Basic XML parsing (manual, can be replaced with DOM/SAX)
            String[] parts = responseXML.split("<LEDGERNAME>");
            int i = 1;
 
         while (i < parts.length) {
    String name = parts[i].split("</LEDGERNAME>")[0].trim();

    HashMap<String, String> ledgerMap = new HashMap<>();
    ledgerMap.put("name", name);
    ledgerList.add(ledgerMap);

    i++;
}

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return ledgerList;
    }


}
