package corpoHumano;

public class Respirar implements Runnable {

    private CorpoHumano corpo;
    
    public Respirar(CorpoHumano corpo) {
        this.corpo = corpo;
    }
    
    @Override
    public void run() {
        while (corpo.estahVivo()) {
            try {
                // Adultos respiram de 12-20 vezes por minuto
                // i.e. uma vez a cada 3-5 segundos.
                corpo.realizarAcao(AcaoCorpoHumano.RESPIRAR);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("Execução interrompida!");
            }
        }
    }

}
