package com.Upload.Phu.Service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ChatbotService {

    public String getChatbotResponse(String userMessage) {
        String chatbotUrl = "http://localhost:5001/chat";  // URL của Flask API
        String response = "";

        try {
            // Tạo URL và kết nối tới Flask API
            URL url = new URL(chatbotUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Thiết lập phương thức POST và các header cần thiết
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);  // Cho phép gửi dữ liệu trong body yêu cầu

            // Tạo body yêu cầu JSON (gửi tin nhắn người dùng)
            String jsonInputString = "{\"message\": \"" + userMessage + "\"}";

            // Gửi dữ liệu đến API
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Nhận mã phản hồi từ server
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {  // Nếu phản hồi thành công (200 OK)
                // Đọc dữ liệu phản hồi từ Flask API
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    String inputLine;
                    StringBuilder responseBuilder = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        responseBuilder.append(inputLine);
                    }
                    response = responseBuilder.toString();  // Lưu trữ phản hồi
                }
            } else {
                response = "Error: " + statusCode;  // Nếu có lỗi, trả về mã lỗi
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = "Đã xảy ra lỗi khi kết nối với chatbot.";
        }

        return response;
    }
}
