package Tally_Erp;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class TallyCreateVoucher {
    
    

    public boolean Create_Voucher_in_Tally(String amount,String Narration) {
        boolean sts = false;
       
        System.out.println("_-----------------"+amount);
        try {
            // Format date to Tally format: ddMMyyyy
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            String tallyDate = sdf.format(new Date());
            System.out.println("----->date"+tallyDate);

            // Tally settings
            String tallyURL = "http://localhost:9000";
            String companyName = "ABC";

            // Prepare voucher XML
            String voucherXML =
                    "<ENVELOPE>" +
                        "<HEADER>" +
                            "<TALLYREQUEST>Import Data</TALLYREQUEST>" +
                        "</HEADER>" +
                        "<BODY>" +
                            "<IMPORTDATA>" +
                                "<REQUESTDESC>" +
                                    "<REPORTNAME>Vouchers</REPORTNAME>" +
                                    "<STATICVARIABLES>" +
                                        "<SVCURRENTCOMPANY>" + companyName + "</SVCURRENTCOMPANY>" +
                                    "</STATICVARIABLES>" +
                                "</REQUESTDESC>" +
                                "<REQUESTDATA>" +
                                    "<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">" +
                                        "<VOUCHER VCHTYPE=\"Payment\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">" +
                                            "<DATE>20250420</DATE>" +
                                            "<GUID>" + UUID.randomUUID() + "</GUID>" +
                                            "<NARRATION>"+Narration+"</NARRATION>" +
                                            "<VOUCHERTYPENAME>Payment</VOUCHERTYPENAME>" +
                                            "<VOUCHERNUMBER>1001</VOUCHERNUMBER>" +
                                            "<PARTYLEDGERNAME>Cash</PARTYLEDGERNAME>" +
                                            "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>" +
                                            "<EFFECTIVEDATE>20250420</EFFECTIVEDATE>" +

                                            // Credit Entry
                                            "<ALLLEDGERENTRIES.LIST>" +
                                                "<LEDGERNAME>Cash</LEDGERNAME>" +
                                                "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>" +
                                                "<AMOUNT>-"+amount+"</AMOUNT>" +
                                            "</ALLLEDGERENTRIES.LIST>" +

                                            // Debit Entry
                                            "<ALLLEDGERENTRIES.LIST>" +
                                                "<LEDGERNAME>SANDEEP KUMAR IPD 123</LEDGERNAME>" +
                                                "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>" +
                                                "<AMOUNT>"+amount+"</AMOUNT>" +
                                            "</ALLLEDGERENTRIES.LIST>" +

                                        "</VOUCHER>" +
                                    "</TALLYMESSAGE>" +
                                "</REQUESTDATA>" +
                            "</IMPORTDATA>" +
                        "</BODY>" +
                    "</ENVELOPE>";

            // Setup connection
            URL url = new URL(tallyURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml");

            // Send XML
            OutputStream os = conn.getOutputStream();
            os.write(voucherXML.getBytes());
            os.flush();

            // Read Tally's response
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                response.append(line).append("\n");

                // Error handling
                if (line.contains("<LINEERROR>")) {
                    System.err.println("⚠ Tally Error: " + line);
                }
                if (line.contains("<CREATED>1</CREATED>")) {
                    sts = true;
                }
            }

            scanner.close();
            conn.disconnect();

            System.out.println("📨 Tally Response:\n" + response.toString());

        } catch (Exception e) {
            System.err.println("❌ Exception occurred while sending voucher to Tally:");
            e.printStackTrace();
        }

        return sts;
    }


   
   


    public static void main(String[] args) {
        try {
            String tallyURL = "http://localhost:9000";

            String companyName = "ABC"; // Replace with your actual company name

            String voucherXML = "<ENVELOPE>" +
                    "<HEADER>" +
                    "<TALLYREQUEST>Import Data</TALLYREQUEST>" +
                    "</HEADER>" +
                    "<BODY>" +
                    "<IMPORTDATA>" +
                    "<REQUESTDESC>" +
                    "<REPORTNAME>Vouchers</REPORTNAME>" +
                    "<STATICVARIABLES>" +
                    "<SVCURRENTCOMPANY>" + companyName + "</SVCURRENTCOMPANY>" +
                    "</STATICVARIABLES>" +
                    "</REQUESTDESC>" +
                    "<REQUESTDATA>" +
                    "<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">" +
                    "<VOUCHER VCHTYPE=\"Payment\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">" +
                    "<DATE>20250420</DATE>" +
                    "<GUID>12345678-ABCD-1234-ABCD-1234567890AB</GUID>" +
                    "<NARRATION>Payment to Test Account</NARRATION>" +
                    "<VOUCHERTYPENAME>Payment</VOUCHERTYPENAME>" +
                    "<VOUCHERNUMBER>1001</VOUCHERNUMBER>" +
                    "<PARTYLEDGERNAME>Cash</PARTYLEDGERNAME>" +
                    "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>" +
                    "<EFFECTIVEDATE>20250420</EFFECTIVEDATE>" +

                    // Credit Entry
                    "<ALLLEDGERENTRIES.LIST>" +
                    "<LEDGERNAME>Cash</LEDGERNAME>" +
                    "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>" +
                    "<AMOUNT>-500</AMOUNT>" +
                    "</ALLLEDGERENTRIES.LIST>" +

                    // Debit Entry
                    "<ALLLEDGERENTRIES.LIST>" +
                    "<LEDGERNAME>SANDEEP KUMAR IPD 123</LEDGERNAME>" +
                    "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>" +
                    "<AMOUNT>500</AMOUNT>" +
                    "</ALLLEDGERENTRIES.LIST>" +

                    "</VOUCHER>" +
                    "</TALLYMESSAGE>" +
                    "</REQUESTDATA>" +
                    "</IMPORTDATA>" +
                    "</BODY>" +
                    "</ENVELOPE>";

            URL url = new URL(tallyURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml");

            OutputStream os = conn.getOutputStream();
            os.write(voucherXML.getBytes());
            os.flush();

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
