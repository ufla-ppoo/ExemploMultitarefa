package corpoHumano;

import java.util.ArrayList;
import java.util.List;

public abstract class CorpoHumano {
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

    public abstract void realizarAcao(AcaoCorpoHumano acao);

    public void morrer() {
        vivo = false;
    }

    public boolean estahVivo() {
        return vivo;
    }
}
