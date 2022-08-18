package corpoHumano;

/**
 * Um tipo enumerador de ações que podem ser realizadas pelo Corpo Humano.
 * 
 * Um tipo enumerador é usado para definir um conjunto de valores válidos para uma
 * determinada situação.
 * 
 * Sem um enumerador, provavelmete utilizaríamos valores inteiros para indicar cada
 * valor válido. Mas, há uma grande desvantagem nessa abordagem, pois nada impede
 * que um programador use um valor inteiro no código que não seja um valor válido
 * esperado.
 */
public enum AcaoCorpoHumano {
    // Nós definimos um tipo enumerador em Java usando a palavra-chave `enum.
    // Na prática estamos definindo uma classe com o nome `AcaoCorpoHumano` e
    // na lista abaixo estamos definindo instâncisa dessa classe.
    BATER_CORACAO,
    RESPIRAR,
    PISCAR_OLHOS;

    // Ao fazer isso, podemos usar as instâncias em nosso código, como no exemplo abaixo:
    //
    // public void tratarAcao(AcaoCorpoHumano acao) {
    //     if (acao == AcaoCorpoHumano.BATER_CORACAO) {
    //         System.out.println("Bateu coração!");
    //     }
    //     else if (acao == AcaoCorpoHumano.RESPIRAR) {
    //         System.out.println("Respirou!");
    //     }
    //     else if (acao == AcaoCorpoHumano.PISCAR_OLHOS) {
    //         System.out.println("Piscou!");
    //     }
    //     else {
    //         System.out.println("Ação não tratada");
    //     }
    // }
}
