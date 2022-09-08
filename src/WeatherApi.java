import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

            //System.out.println(getResponse);


            /*------------------------------------------------------------------------
                                        P A R S I N G
            ------------------------------------------------------------------------*/
            final String jsonString = getResponse.body(); // Здесь Json в виде строки

            final JsonParser parser = new JsonParser();
            final JsonObject root = parser.parse(jsonString).getAsJsonObject();

            final String name = root.getAsJsonObject("location")
                    .get("name").toString().replaceAll("\"","");

            final String temp_c = root.getAsJsonObject("current")
                            .get("temp_c").toString();

            System.out.println(jsonString);
            System.out.println("\n"+name);
            System.out.println(temp_c+"°C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
