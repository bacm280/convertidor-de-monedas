import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcionElegida = 0;


        ConsultaDeConversion.ConsultaConversion consulta = new ConsultaDeConversion.ConsultaConversion();
        calculos.Calculos calculos = new calculos.Calculos(consulta);
        GeneradorDeArchivos generador = new GeneradorDeArchivos();


        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                $$$bienvenido al Conversor de Monedas$$$                               \s
                1) Peso Chileno ==>> Dólar Estadounidense
                2) Peso Chileno ==>> Euro
                3) Peso Chileno ==>> Libra Esterlina
                4) Dólar Estadounidense ==>> Peso Chileno
                5) Euro ==>> Peso Chileno
                6) Libra Esterlina ==>> Peso Chileno
                7) Otra opción de conversión
                8) Mostrar historial
                9) Salir
                $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
               \s""";

        while (opcionElegida != 9) {
            try {
                System.out.println(menu);
                opcionElegida = Integer.parseInt(lectura.nextLine());

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                switch (opcionElegida) {
                    case 1:
                        calculos.almacenarValores("CLP", "USD");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 2:
                        calculos.almacenarValores("CLP", "EUR");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 3:
                        calculos.almacenarValores("CLP", "GBP");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 4:
                        calculos.almacenarValores("USD", "CLP");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 5:
                        calculos.almacenarValores("EUR", "CLP");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 6:
                        calculos.almacenarValores("GBP", "CLP");
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 7:
                        calculos.almacenarValoresPersonalizados();
                        respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                        break;
                    case 8:
                        System.out.println("Historial de conversiones:");
                        if (respuestas.isEmpty()) {
                            System.out.println("No hay conversiones realizadas aún.");
                        } else {
                            for (String respuesta : respuestas) {
                                System.out.println(respuesta);
                            }
                        }
                        break;
                    case 9:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Ingrese una opción válida");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error. Ingrese un valor numérico válido.");
                lectura.nextLine();
            }
        }

        generador.guardarJson(respuestas);
        lectura.close();
        System.out.println("Finalizando programa");
    }
}
