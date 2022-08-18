package corpoHumano;

public class BaterCoracao implements Runnable {
    
    private CorpoHumano corpo;
    
    public BaterCoracao(CorpoHumano corpo) {
        this.corpo = corpo;
    }
    
    @Override
    public void run() {
        while (corpo.estahVivo()) {
            try {
                // O coração de um homem de 25 anos, 
                // em situação normal, bate aprox. 115/min.
                // i.e. uma batida a cada 0.52 segundos.
                corpo.realizarAcao(AcaoCorpoHumano.BATER_CORACAO);
                Thread.sleep(520);
            } catch (InterruptedException ex) {
                System.out.println("Execução interrompida!");
            }
        }
    }
}
