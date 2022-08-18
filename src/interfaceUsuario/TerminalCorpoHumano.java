package interfaceUsuario;

import corpoHumano.AcaoCorpoHumano;
import corpoHumano.CorpoHumano;

public class TerminalCorpoHumano extends CorpoHumano {

    @Override
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
