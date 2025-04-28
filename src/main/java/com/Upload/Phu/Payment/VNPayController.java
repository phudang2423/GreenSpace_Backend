package com.Upload.Phu.Payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class VNPayController {

    @Value("${vnpay.tmnCode}")
    private String vnp_TmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnp_HashSecret;

    @Value("${vnpay.paymentUrl}")
    private String vnp_PaymentUrl;

    @Value("${vnpay.returnUrl}")
    private String vnp_ReturnUrl;

    @PostMapping("/create")
    public Map<String, String> createPayment(@RequestBody Map<String, Object> requestData) {
        try {
            long amount = Long.parseLong(requestData.get("amount").toString());
            String orderInfo = requestData.get("orderInfo").toString();
            String orderType = requestData.get("orderType").toString();
            String orderId = requestData.get("orderId").toString();

            // Xử lý logic thanh toán...
            Map<String, String> response = new HashMap<>();
            response.put("code", "00");
            response.put("message", "success");
            response.put("data", "URL_THANH_TOAN_VNPAY");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("code", "99");
            response.put("message", "error");
            return response;
        }
    }

    @GetMapping("/callback")
    public String paymentCallback(HttpServletRequest request) {
        Map<String, String> vnp_Params = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String paramName = params.nextElement();
            vnp_Params.put(paramName, request.getParameter(paramName));
        }
        String vnp_SecureHash = vnp_Params.remove("vnp_SecureHash");

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = vnp_Params.get(fieldName);
            if (value != null && value.length() > 0) {
                hashData.append(fieldName).append('=').append(value).append('&');
            }
        }
        hashData.deleteCharAt(hashData.length() - 1);

        String secureHash = VNPayUtils.hmacSHA512(vnp_HashSecret, hashData.toString());
        if (secureHash.equals(vnp_SecureHash)) {
            // Xử lý logic khi thanh toán thành công
            return "Payment success!";
        } else {
            return "Invalid signature!";
        }
    }
}