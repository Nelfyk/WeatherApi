import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApi {
    final private String API_KEY = "key=1d30c485d96c4273a8d92635220709";
    final private String URI = "http://api.weatherapi.com/v1/current.json?";
    final private String Q = "&q=";
    private String CITY;

    private String name;
    private String temp_c;
    private int error;

    public void getWeather() {
        // 🍺
        // API Key: 1d30c485d96c4273a8d92635220709
        // http://api.weatherapi.com/v1
        // Docs: https://www.weatherapi.com/docs

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URI + API_KEY + Q + CITY))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            /*------------------------------------------------------------------------
                                        P A R S I N G
            ------------------------------------------------------------------------*/
            final String jsonString = getResponse.body(); // Здесь Json в виде строки

            final JsonParser parser = new JsonParser();
            final JsonObject root = parser.parse(jsonString).getAsJsonObject();
            error = 200;
            try {
                name = root.getAsJsonObject("location")
                        .get("name").toString().replaceAll("\"", "");

                temp_c = root.getAsJsonObject("current")
                        .get("temp_c").toString() + " °c";
            } catch (NullPointerException e1) {
                error = root.getAsJsonObject("error")
                        .get("code").getAsInt();
            }
            System.out.println(getResponse.body());
            System.out.println(jsonString);
            System.out.println("\n" + name);
            System.out.println(temp_c);
        } catch (Exception e) {
            error = 1;
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public int getError() {
        return error;
    }
}
