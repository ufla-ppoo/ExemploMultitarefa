package corpoHumano;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Quanto tempo o corpo vai viver (em segundos)?");
        int tempo = Integer.parseInt(entrada.nextLine());
        CorpoHumano c = new CorpoHumano(tempo);
        c.nascer();
        entrada.close();
    }
}
