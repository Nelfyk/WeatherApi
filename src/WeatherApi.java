import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherApi {
    public static void main(String[] args) {
        // 🍺
        // API Key: 1d30c485d96c4273a8d92635220709
        // http://api.weatherapi.com/v1
        // Docs: https://www.weatherapi.com/docs
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.weatherapi.com/v1/current.json?key=1d30c485d96c4273a8d92635220709&q=Surgut"))
//                    .header("key", "1d30c485d96c4273a8d92635220709")
//                    .header("q", "Surgut")
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(getResponse);
            System.out.println(getResponse.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
