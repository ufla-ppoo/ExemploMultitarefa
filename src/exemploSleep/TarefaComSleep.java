package exemploSleep;

public class TarefaComSleep implements Runnable {
    private String nome;
    private int tempoDeSuspensao;

    public TarefaComSleep(String nome, int tempoDeSuspensao) {
        this.nome = nome;
        this.tempoDeSuspensao = tempoDeSuspensao;

    }

    public String getNome() {
        return nome;
    }

    public int getTempoDeSuspensao() {
        return tempoDeSuspensao;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Execuntado " + nome + "...");

                System.out.println("Agora " + nome + " vai dormir por " + tempoDeSuspensao + " milissegundos...");
                Thread.sleep(tempoDeSuspensao);
            
            } catch (InterruptedException ex) {
                // O método `Thread.sleep` lança uma exceção verificada do tipo `InterruptedException`.
                // Logo, nós temos que tratar essa exceção
                System.out.println("Execução da tarefa " + nome + " interrompida!");
            }
        }
    }
}
