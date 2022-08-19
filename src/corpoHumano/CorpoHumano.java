package corpoHumano;

import java.util.ArrayList;
import java.util.List;

public class CorpoHumano {
    private boolean vivo;
    private List<Runnable> tarefasDoCorpo;

    public CorpoHumano() {
        vivo = false;
        construirTarefas();
    }
    
    private void construirTarefas() {
        tarefasDoCorpo = new ArrayList<Runnable>();
        tarefasDoCorpo.add(new BaterCoracao(this));
        tarefasDoCorpo.add(new PiscarOlhos(this));
        tarefasDoCorpo.add(new Respirar(this));
    }

    public void nascer() {
        vivo = true;
        for (Runnable tarefa : tarefasDoCorpo) {
            Thread th = new Thread(tarefa);
            th.start();
        }
    }    

    public void morrer() {
        vivo = false;
    }

    public boolean estahVivo() {
        return vivo;
    }

    public void realizarAcao(AcaoCorpoHumano acao) {
        if (acao == AcaoCorpoHumano.BATER_CORACAO) {
            System.out.println("Bateu coração...");
        }
        else if (acao == AcaoCorpoHumano.RESPIRAR) {
            System.out.println("Respirou...");
        }
        else if (acao == AcaoCorpoHumano.PISCAR_OLHOS) {
            System.out.println("Piscou os olhos...");
        }
        else {
            throw new IllegalArgumentException("Enum " + acao + " nao foi tratado!");
        }
    }
}
