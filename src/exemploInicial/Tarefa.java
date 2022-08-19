package exemploInicial;

public class Tarefa implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100000; i++) {
            double valor = 10*i;
            // exibe uma mensagem a cada 10 mil iterações
            if (i % 10000 == 0) {
                System.out.println("Tarefa executada pela thread, valor: " + valor);
            }
        }
        
    }
}
