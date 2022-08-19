package exemploSleep;

public class App {
    public static void main(String[] args) {
        System.out.println("Criando duas tarefas com suspensão");
        
        TarefaComSleep tarefa1 = new TarefaComSleep("Tarefa 1", 1000);
        TarefaComSleep tarefa2 = new TarefaComSleep("Tarefa 2", 2000);

        System.out.println("Criando os objetos de duas novas thread para as tarefas");
        Thread thread1 = new Thread(tarefa1);
        Thread thread2 = new Thread(tarefa2);

        System.out.println("Iniciando a execução das threads");
        thread1.start();
        thread2.start();
        
        System.out.println("Terminando o método main!");
    }
}
