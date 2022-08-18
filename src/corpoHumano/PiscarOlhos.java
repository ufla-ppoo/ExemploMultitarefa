package corpoHumano;

public class PiscarOlhos implements Runnable {

    private CorpoHumano corpo;
    
    public PiscarOlhos(CorpoHumano corpo) {
        this.corpo = corpo;
    }
    
    @Override
    public void run() {
        while (corpo.estahVivo()) {
            try {
                // Um adulto normal fecha os olhos 24 vezes por minuto, 
                // para umidificá-los e limpá-los.
                corpo.realizarAcao(AcaoCorpoHumano.PISCAR_OLHOS);
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                System.out.println("Execução interrompida!");
            }
        }
    }

}
