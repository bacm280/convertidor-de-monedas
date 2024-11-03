import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDeConversion {

    public static class ConsultaConversion {
        private List<String> historial = new ArrayList<>();

        public String buscaConversion(String monedaBase, String monedaObjetivo, double cantidad) {
            try {
                String apiKey = "fb67abe7c51c08cc7704420e";
                String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" +
                        monedaBase + "/" + monedaObjetivo;

                URL url = new URL(urlStr);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("GET");
                request.connect();

                int status = request.getResponseCode();
                if (status != 200) {
                    throw new RuntimeException("Error en la solicitud HTTP, código de respuesta: " + status);
                }

                JsonElement root = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
                JsonObject jsonobj = root.getAsJsonObject();

                double conversionRate = jsonobj.get("conversion_rate").getAsDouble();
                double resultadoConversion = conversionRate * cantidad;

                String registro = String.format("Conversión de %.2f %s a %s: %.2f", cantidad, monedaBase, monedaObjetivo, resultadoConversion);

                historial.add(registro);

                return registro;

            } catch (IOException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }

        public void mostrarHistorial() {
            if (historial.isEmpty()) {
                System.out.println("No hay historial de conversiones.");
            } else {
                System.out.println("Historial de conversiones:");
                for (String registro : historial) {
                    System.out.println(registro);
                }
            }
        }
    }

    public static void main(String[] args) {
        ConsultaConversion consulta = new ConsultaConversion();

        String resultado1 = consulta.buscaConversion("CLP", "EUR", 100);
        System.out.println("Resultado de la conversión: " + resultado1);

        String resultado2 = consulta.buscaConversion("CLP", "USD", 200);
        System.out.println("Resultado de la conversión: " + resultado2);

        consulta.mostrarHistorial();
    }
}

