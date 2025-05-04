/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ALL_Methods;

import PatitentPayment.PatitentPaymentDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Lenovo
 */
public class ALL_Methods {
    //public conn connection 
    
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    //all methods of billin that can be reused automaticlly here 
    
    public String generateUUID(String prefix) {
    return prefix + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
}

public String formatSqlDateTime(String inputDate) {
    LocalDateTime dt = LocalDateTime.parse(inputDate);
    return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}

public void insertFinalBill(Connection con, String billNo, String patientId, String gross, String discount, String net, String user) throws SQLException{
    String sql = "INSERT INTO ipd_final_bill (Bill_no, patient_id, Bill_gross_amount, bill_discount, Bill_final_paid, Bill_status, created_by, created_date) " +
                 "VALUES (?, ?, ?, ?, ?, 'Provisional_Bill', ?, GETDATE())";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, billNo);
        ps.setString(2, patientId);
        ps.setString(3, gross);
        ps.setString(4, discount);
        ps.setString(5, net);
        ps.setString(6, user);
        ps.executeUpdate();
    }
}

public void updatePatientLocation(Connection con, String patientId, String endDate) throws SQLException {
    String sql = "UPDATE patient_location SET end_date = ?, bill_clearence_sts = 'Yes' WHERE patient_id = ? AND end_date IS NULL";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, endDate);
        ps.setString(2, patientId);
        ps.executeUpdate();
    }
}

public void clearOtherCharges(Connection con, String patientId) throws SQLException {
    String sql = "UPDATE Add_other_charge_details SET oc_amt_paid = oc_final_amt, oc_amt_left = 0 WHERE patient_id = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, patientId);
        ps.executeUpdate();
    }
}

public void clearLabTests(Connection con, String patientId) throws SQLException {
    String sql = "UPDATE lab_test_assigned SET test_paid_amt = test_rate, test_pending_amt = 0, test_sts = 'Done' WHERE patient_id = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, patientId);
        ps.executeUpdate();
    }
}

public String insertVoucher(Connection con, String patientId, String netAmount, String user, String vocCode, String payType,
                          String chequeNo, String cardNo, String upi, String transactionId, String receivedFrom,
                          String debitAc, String tpaName) throws SQLException {
String Voc_id="";
    String amount = (tpaName != null && tpaName.trim().equalsIgnoreCase("ayushman bharat")) ? "0" : netAmount;
    String remark = amount.equals("0") ? "Ayushman_patient bill" : "Final Bill Clearance";

    String sql = "INSERT INTO ipd_patient_payment (patient_id, amount, payment_date, taken_by, voucher_id, remark, " +
                 "paytype, cheque_number, card_number, upi_type, transcation_id, recieved_from, Debit_AC) " +
                 "VALUES (?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, patientId);
        ps.setString(2, amount);
        ps.setString(3, user);
        ps.setString(4, vocCode);
        ps.setString(5, remark);
        ps.setString(6, payType);
        ps.setString(7, chequeNo);
        ps.setString(8, cardNo);
        ps.setString(9, upi);
        ps.setString(10, transactionId);
        ps.setString(11, receivedFrom);
        ps.setString(12, debitAc);
        ps.executeUpdate();
    }
    return Voc_id;
}

public void handleRoomPayments(Connection con, String patientId, String[] roomAmounts,
                               String[] roomBedIds, String[] roomChargeRwids, String user) throws SQLException {
    for (int i = 0; i < roomBedIds.length; i++) {
        String bedId = roomBedIds[i];
        String roomAmt = roomAmounts[i];
        String rwid = roomChargeRwids[i];
        String paymentId = generateUUID("RP_");
        String detailId = generateUUID("RD_");

        try (PreparedStatement ps = con.prepareStatement("SELECT 1 FROM room_payment WHERE patient_id = ? AND room_bed_id = ?")) {
            ps.setString(1, patientId);
            ps.setString(2, bedId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String updateRoom = "UPDATE room_payment SET paid_amt = ?, panding_amt = 0 WHERE patient_id = ? AND room_bed_id = ?";
                try (PreparedStatement ups = con.prepareStatement(updateRoom)) {
                    ups.setString(1, roomAmt);
                    ups.setString(2, patientId);
                    ups.setString(3, bedId);
                    ups.executeUpdate();
                }
            } else {
                String insertRoom = "INSERT INTO room_payment (room_payment_id, patient_id, room_amt, paid_amt, panding_amt, createDT, createBy, room_bed_id) " +
                                    "VALUES (?, ?, ?, ?, 0, GETDATE(), ?, ?)";
                try (PreparedStatement ins = con.prepareStatement(insertRoom)) {
                    ins.setString(1, paymentId);
                    ins.setString(2, patientId);
                    ins.setString(3, roomAmt);
                    ins.setString(4, roomAmt);
                    ins.setString(5, user);
                    ins.setString(6, bedId);
                    ins.executeUpdate();
                }
            }
        }

        // Insert into room_payment_details
        String detailInsert = "INSERT INTO room_payment_details (room_payment_details_id, room_payment_id, patient_id, " +
                              "patient_location_rwid, room_bed_id, room_amt, paid_amt, panding_amt, createBY) " +
                              "VALUES (?, ?, ?, ?, ?, ?, ?, 0, ?)";
        try (PreparedStatement pds = con.prepareStatement(detailInsert)) {
            pds.setString(1, detailId);
            pds.setString(2, paymentId);
            pds.setString(3, patientId);
            pds.setString(4, rwid);
            pds.setString(5, bedId);
            pds.setString(6, roomAmt);
            pds.setString(7, roomAmt);
            pds.setString(8, user);
            pds.executeUpdate();
        }
    }
}

public void savePatientPaymentRecord(String patientId, String paymentId, String user, String netAmount) throws Exception {
    Map<String, Object> paymentData = new HashMap<>();
    paymentData.put("patitent_id", patientId);
    paymentData.put("payment_id", paymentId);
    paymentData.put("payment_type", "Final Bill Clearance");
    paymentData.put("mode", "Payment_Voucher");
    paymentData.put("create_by", user);
    paymentData.put("pay_amt", "0");
    paymentData.put("paid_amt", netAmount);
    paymentData.put("pending_amt", 0);

    boolean status = new PatitentPaymentDao().savePayment((HashMap<String, Object>) paymentData);
    if (!status) {
        throw new Exception("Failed to save patient payment data.");
    }
}

    
}
