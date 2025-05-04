//package Tally_Erp;
//
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import org.w3c.dom.*;
//
//public class test {
//
//    public  testng() {
//        try {
//            String tallyUrl = "http://localhost:9000";
//            URL url = new URL(tallyUrl);
////
//            // Safe XML request to fetch only ledger names
////            String xmlRequest =
////                    "<ENVELOPE>\n" +
////"  <HEADER>\n" +
////"    <TALLYREQUEST>Export Data</TALLYREQUEST>\n" +
////"  </HEADER>\n" +
////"  <BODY>\n" +
////"    <EXPORTDATA>\n" +
////"      <REQUESTDESC>\n" +
////"        <REPORTNAME>Ledger Vouchers</REPORTNAME>\n" +
////"        <STATICVARIABLES>\n" +
////"          <SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT>\n" +
////"        </STATICVARIABLES>\n" +
////"      </REQUESTDESC>\n" +
////"    </EXPORTDATA>\n" +
////"  </BODY>\n" +
////"</ENVELOPE>";
//
//
//String xmlRequest =
//    "<ENVELOPE>" +
//    "  <HEADER>" +
//    "    <TALLYREQUEST>Export Data</TALLYREQUEST>" +
//    "  </HEADER>" +
//    "  <BODY>" +
//    "    <EXPORTDATA>" +
//    "      <REQUESTDESC>" +
//    "        <REPORTNAME>Ledger Vouchers</REPORTNAME>" +
//    "        <STATICVARIABLES>" +
//    "          <SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT>" +
//    "          <LEDGERNAME>Cash</LEDGERNAME>" +
//    "          <FROMDATE>20250420</FROMDATE>" +
//    "          <TODATE>20250420</TODATE>" +
//    "        </STATICVARIABLES>" +
//    "      </REQUESTDESC>" +
//    "    </EXPORTDATA>" +
//    "  </BODY>" +
//    "</ENVELOPE>";
//
////
////       String xmlRequest = "<ENVELOPE>" +
////                    "<HEADER><TALLYREQUEST>Export Data</TALLYREQUEST></HEADER>" +
////                    "<BODY><EXPORTDATA>" +
////                    "<REQUESTDESC>" +
////                    "<REPORTNAME>List of Ledgers</REPORTNAME>" +
////                    "</REQUESTDESC>" +
////                    "</EXPORTDATA></BODY></ENVELOPE>";
//
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "text/xml");
//            connection.setDoOutput(true);
//
//            OutputStream os = connection.getOutputStream();
//            os.write(xmlRequest.getBytes("UTF-8"));
//            os.close();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            System.out.println("Received XML from Tally:\n");
//            System.out.println(response.toString());
//
//            // Parse and print the ledger names
//           // parseLedgerNames(response.toString());
//
//        } catch (Exception e) {
//            System.out.println("Error occurred while connecting to Tally:");
//            e.printStackTrace();
//        }
//        
//   
//    }
//
//
////    public  ArrayList<HashMap<String, String>> parseVoucherData(String xmlData) {
////        ArrayList<HashMap<String, String>> voucherList = new ArrayList<>();
////
////        try {
////            // Set up DocumentBuilderFactory to parse the XML
////            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
////            DocumentBuilder builder = factory.newDocumentBuilder();
////            InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
////            Document doc = builder.parse(is);
////            doc.getDocumentElement().normalize();
////
////            // Fetch all the VOUCHER elements
////            NodeList voucherNodes = doc.getElementsByTagName("VOUCHER");
////
////            // Loop through all the voucher elements and extract data
////            for (int i = 0; i < voucherNodes.getLength(); i++) {
////                Node voucherNode = voucherNodes.item(i);
////
////                if (voucherNode.getNodeType() == Node.ELEMENT_NODE) {
////                    Element voucherElement = (Element) voucherNode;
////
////                    // Create a new HashMap to store voucher data
////                    HashMap<String, String> voucherData = new HashMap<>();
////
////                    // Extract the values of different tags within the VOUCHER element
////                    voucherData.put("DATE","DATE");
////                    voucherData.put("VOUCHERTYPENAME","VOUCHERTYPENAME");
////                    voucherData.put("VOUCHERNUMBER","VOUCHERNUMBER");
////
////                    // Loop through all LEDGERENTRIES.LIST to extract ledger-related data
////                    NodeList ledgerEntries = voucherElement.getElementsByTagName("LEDGERENTRIES.LIST");
////                    for (int j = 0; j < ledgerEntries.getLength(); j++) {
////                        Element ledgerElement = (Element) ledgerEntries.item(j);
////
////                        // Extract individual ledger values and add them to the HashMap
//////                        String ledgerName = getTagValue("LEDGERNAME", ledgerElement);
//////                        String amount = getTagValue("AMOUNT", ledgerElement);
////
////                        // Use a key with an index to differentiate multiple ledger entries
//////                        voucherData.put("LEDGERNAME_" + (j + 1), ledgerName);
//////                        voucherData.put("AMOUNT_" + (j + 1), amount);
////                    }
////
////                    // Add the voucher data to the ArrayList
////                    voucherList.add(voucherData);
////                }
////            }
////
////        } catch (Exception e) {
////            System.out.println("Error while parsing XML:");
////            e.printStackTrace();
////        }
////
////        return voucherList;
////    }
//
////    // Helper method to extract tag value for a given tag name in an element
////    public static String getTagValue(String tag, Element element) {
////        NodeList list = element.getElementsByTagName(tag);
////        if (list.getLength() > 0 && list.item(0).hasChildNodes()) {
////            return list.item(0).getTextContent().trim();
////        }
////        return "";
////    }
//}
//
//
//
