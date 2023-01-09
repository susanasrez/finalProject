
public class Main {

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Por favor introduzca su apiKey.");
        }
        else{
            String apiToken = args[0];
            new Controller(apiToken);
        }

    }
}
