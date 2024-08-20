package jconnect1;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomerServlet {

    public static void main(String[] args) {
        try {
            String jsonInputString = "{\"name\":\"John Doe\", \"age\":30}"; // Gönderilecek JSON verisi

            URL url = new URL("http://localhost:3306/mydatabase"); // API URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            System.out.println("Response Code: " + code);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
