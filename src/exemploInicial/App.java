package exemploInicial;

public class App {
    public static void main(String[] args) {
        System.out.println("Criando a tarefa a ser executada");
        Tarefa tarefa = new Tarefa();

        System.out.println("Criando o objeto de uma nova thread com a tarefa passada!");
        Thread thread = new Thread(tarefa);

        System.out.println("Iniciando a execução da thread!");
        thread.start();

        for (int i = 1; i <= 100000; i++) {            
            double valor = 10*i;
            // exibe uma mensagem a cada 10 mil iterações
            if (i % 10000 == 0) {
                System.out.println("Valor " + valor);
            }
        }
        
        System.out.println("Terminando o método main!");
    }
}
