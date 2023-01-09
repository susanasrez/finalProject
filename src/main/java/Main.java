
public class Main {

    public static void main(String[] args) {
        //Se le pasa el apiToken y la ruta del Datalake
        /*f (args.length > 2 || args.length == 0){
            System.out.println("Número de parámetros introducidos incorrectos.");
        }
        else{
            String apiToken = args[0];
            File rootDatalake = new File(args[1]);
            System.out.println(apiToken );
        }*/

        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXNhbmFzdWFyZXptZW5kb3phMjZAZ21haWwuY29tIiwianRpIjoiMmY1NDQ1MGYtMTc5Mi00MzM3LTlhODQtNDQ0NGZjZTg5MjYyIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2NzMwOTM3MTQsInVzZXJJZCI6IjJmNTQ0NTBmLTE3OTItNDMzNy05YTg0LTQ0NDRmY2U4OTI2MiIsInJvbGUiOiIifQ.pXIbNRFJKXQwHbizkuiXdxq_7Jtdk8inUdfs6eWqMfM";
        //File rootDatalake = new File("C:\\Users\\Susana\\Desktop\\Universidad\\Segundo de Ingenieria de datos\\aemetDataLake");

        new Controller(apiKey);
    }
}
