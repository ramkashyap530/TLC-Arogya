import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TallyVoucherFetcher {

    public static void main(String[] args) {
        try {
            // ✅ SET THIS to match exactly what’s shown in Tally
            String companyName = "ABC";

            // ✅ SET THIS to a known date where vouchers exist (format: YYYYMMDD)
            String fromDate = "20250420";
            String toDate = "20250420";

            // ✅ SAFE XML request to fetch Day Book vouchers
            String xmlRequest =
                    "<ENVELOPE>" +
                        "<HEADER>" +
                            "<TALLYREQUEST>Export Data</TALLYREQUEST>" +
                        "</HEADER>" +
                        "<BODY>" +
                            "<EXPORTDATA>" +
                                "<REQUESTDESC>" +
                                    "<REPORTNAME>Day Book</REPORTNAME>" +
                                    "<STATICVARIABLES>" +
                                        "<SVFROMDATE>" + fromDate + "</SVFROMDATE>" +
                                        "<SVTODATE>" + toDate + "</SVTODATE>" +
                                        "<SVEXPORTFORMAT>XML</SVEXPORTFORMAT>" +
                                        "<SVCURRENTCOMPANY>" + companyName + "</SVCURRENTCOMPANY>" +
//                                         Optional: Uncomment this line to fetch only specific voucher type
                                         "<SVVOUCHERTYPENAME></SVVOUCHERTYPENAME>" +
                                    "</STATICVARIABLES>" +
                                "</REQUESTDESC>" +
                            "</EXPORTDATA>" +
                        "</BODY>" +
                    "</ENVELOPE>";

            // ✅ Connect to Tally on default port (9000)
            URL url = new URL("http://localhost:9000");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml");

            // ✅ Send the XML request
            OutputStream os = conn.getOutputStream();
            os.write(xmlRequest.getBytes("UTF-8"));
            os.flush();
            os.close();

            // ✅ Read and print the response
            Scanner scanner = new Scanner(conn.getInputStream(), "UTF-8");
            System.out.println("----- Tally Response -----");
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
