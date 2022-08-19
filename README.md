# Projeto Corpo Humano

Este projeto é usado como exemplo nas aulas de Multitarefa em disciplinas de Programação Orientada a Objetos.
O conteúdo é bem simples e apresenta apenas o básico do básico do uso de `Threads` em Java, sem entrar em problemas de concorrência, interrupção, etc.

O código do projeto é baseado em uma [versão inicial](https://github.com/ufla-ppoo/GCC178-Projetos-das-Aulas/blob/master/Multitarefa) criada pelo Prof. Paulo Afonso (DCC/ICET/UFLA).

## Multitarefa

É muito comum que, em um sistema, queiramos _rodar duas coisas ao mesmo tempo_.
Por exemplo, quando estamos assistindo a um vídeo no Netflix ou no YouTube, nós conseguimos começar a assistir o video mesmo antes que ele esteja todo carregado. Isso significa que o carregamento do vídeo e a exibição do mesmo são duas tarefas que estão sendo feitas em paralelo (_ao mesmo tempo_).

Nós também podemos fazer tarefas rodarem em paralelo nos nossos programas em Java.
Um dos exemplos mais comuns é esse caso de deixar o usuário usar funcionalidades do sistema, enquanto outras partes estão sendo carregadas.
Mas podemos pensar também que enquanto um software exporta os dados para um arquivo ele pode também estar fazendo algum cálculo ou processamento, por exemplo.

Java implementa o conceito de _multithreading_, o que significa que podemos criar, e rodar, várias _threads_ dentro do mesmo programa.
As _threads_ fazem parte do mesmo processo e, portanto, compartilham o espaço de memória.
Essa característica torna o uso de múltiplas _threads_ muito útil, ao mesmo tempo que pode criar problemas de concorrência.

Em computadores antigos com um único núcleo (_singlecore_), o Sistema Operacional executa uma _thread_ de cada vez, mas como cada uma delas executa por muito pouco tempo, nós temos a "ilusão" de que elas estão rodando em paralelo.
Já nos computadores mais modernos, com arquitetura _multicore_, o Sistema Operacional pode escalonar as _threads_ em vários núcleos, o que permite que elas sejam realmente executadas em paralelo.

## Como criar Threads em Java?

Quando iniciamos o nosso programa, uma _thread_ principal é criada e executada a partir do método `main`.
Mas nós podemos criar novas _threads_ em qualquer ponto do nosso pograma, usando um objeto de uma classe com o nome bem sugestivo de `Thread` :)
Para criar e executar uma nova _thread_ nós precisamos, basicamente, de: (1) criar um objeto dessa classe passando uma tarefa a ser executada e (2) executar a _thread_ chamando o método `start()`.

Para que o objeto da classe `Thread` saiba como executar a tarefa passada no construtor, é necessário que esta tarefa siga um contrato definido pela inteface `Runnable`.
Esta inteface possui um único método chamado `run` (como mostrado no código abaixo).

```java
public interface Runnable {
    void run();
}
```

Portanto, precisamos passar para o construtor da classe `Thread` um objeto de uma classe que implemente a interaface `Runnable`, sobrescrevendo o método `run`.
Repare que a classe `Thread` não precisa conhecer o tipo específico da classe que representa a tarefa.
Basta que ela saiba que a classe segue o contrato estabelecido (ou seja, tenha o método `run` com a assinatura esperada).

Para exemplificar, vamos criar uma classe chamada `Tarefa` que, no método `run`, exibe uma mensagem na tela.

```java
public class Tarefa implements Runnable {
    @Override
    public void run() {
        System.out.println("Tarefa executada pela thread criada!");
    }
}
```

Para executarmos essa tarefa em uma _thread_ separada da _thread_ principal nós podemos, por exemplo, fazer então o seguinte:

```java
public class App {
    public static void main(String[] args) {
        System.out.println("Criando a tarefa a ser executada")    
        Tarefa tarefa = new Tarefa();

        System.out.println("Criando o objeto de uma nova thread com a tarefa passada!")
        Thread thread = new Thread(tarefa);

        System.out.println("Iniciando a execução da thread!")
        tarefa.start()

        System.out.println("Terminano o método main!")
    }
}
```

Uma possível saída do programa é exibida abaixo.
Veja que a mensagem da _thread_ criada foi exibida somente depois da mensagem `Terminando o método main!`, o que mostra que o método `main` terminou antes da _thread_ ser executada.
Mas a ordem poderia ter sido diferente, pois depende de como o Sistema Operacional aloca as _threads_ e da disponibilidade dos núcleos do computador durante a execução.

```
Criando a tarefa a ser executada
Criando o objeto de uma nova thread com a tarefa passada!
Iniciando a execução da thread!
Terminando o método main!
Tarefa executada pela thread criada!
```

## Exemplos mostrando ordem de execução diferente

Se nós simplesmente adicionarmos algum processamento no método `main` depois da tread secundária ser executada, nós podemos ver que ela será executada antes de terminar o método `main`.

Com o código abaixo, por exemplo:

```java
public class App {
    public static void main(String[] args) {
        System.out.println("Criando a tarefa a ser executada");
        Tarefa tarefa = new Tarefa();

        System.out.println("Criando o objeto de uma nova thread com a tarefa passada!");
        Thread thread = new Thread(tarefa);

        System.out.println("Iniciando a execução da thread!");
        thread.start();
        
        for (int i = 1; i < 100000; i++) {
            double valor = 10*i;
        }
        
        System.out.println("Terminando o método main!");
    }
}
```

Uma saída possível é:

```
Criando a tarefa a ser executada
Criando o objeto de uma nova thread com a tarefa passada!
Iniciando a execução da thread!
Tarefa executada pela thread criada!
Terminando o método main!
```

O exemplo pode ficar mais interessante se a _thread_ secundária também tem algum processamento mais demorado.
Veja abaixo as novas versões das nossas classes `Tarefa` e `App`.
Como acha que as mensagens serão exibidas na tela?

```java
public class Tarefa implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100000; i++) {
            double valor = 10*i;
            // exibe uma mensagem a cada 10 mil iterações
            if (i % 10000 == 0) {
                System.out.println("Tarefa executada pela thread, valor: " + valor);
            }
        }
        
    }
}
```

```java
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
```

O código acima está neste projeto, no pacote `exemploInicial`.
Experimente executar o programa várias vezes (rodando a partir da classe `App` que está dentro do pacote), e repare as diferentes ordens de execução.

Uma das possíveis saídas é mostrada abaixo.

```
Criando a tarefa a ser executada
Criando o objeto de uma nova thread com a tarefa passada!
Iniciando a execução da thread!
Valor 100000.0
Tarefa executada pela thread, valor: 100000.0
Tarefa executada pela thread, valor: 200000.0
Valor 200000.0
Tarefa executada pela thread, valor: 300000.0
Valor 300000.0
Valor 400000.0
Tarefa executada pela thread, valor: 400000.0
Valor 500000.0
Valor 600000.0
Valor 700000.0
Tarefa executada pela thread, valor: 500000.0
Tarefa executada pela thread, valor: 600000.0
Valor 800000.0
Tarefa executada pela thread, valor: 700000.0
Valor 900000.0
Tarefa executada pela thread, valor: 800000.0
Valor 1000000.0
Terminando o método main!
Tarefa executada pela thread, valor: 900000.0
Tarefa executada pela thread, valor: 1000000.0
```
## E se minha classe herdar da classe `Thread`?

É comum encontrarmos exemplos de classes de tarefas que herdam da classe `Thread` ao invés de implementar a interface `Runnable`.
Mas essa abordagem não é muito adequada, por dois motivos principais.
Você consegue imaginar quais?

1. Primeiro que a modelagem OO fica estranha. 
   Repare que a modelagem fica mais clara, quando nossa classe representa uma tarefa a ser executada pela _thread_.
   Já quando a classe herda de `Thread` ela é um tipo específico de _thread_, o que não representa bem o que ela faz.
   Sem conta que a classe acaba herdando diversos outros métodos que, muitas das vezes, não estamos interessados.

2. Outro ponto é que herdar de `Thread` limita a herança múltipla.
   Como em Java uma classe pode herdar de apenas uma superclasse, a nossa classe que realiza a tarefa não poderá herdar de nenhuma outra classe.
   Já se ela herdar da interface `Runnable`, ela poderá herdar de outra classe, uma vez que Java possui herança múltipla de interfaces.

## Sabia que uma `Thread` pode dormir?

Um método muito útil da classe `Thread` é o `sleep` (em português, _dormir_), que pode ser usado para suspender a execução de uma _thread_ pelo tempo informado.

O método estático `sleep` espera, como parâmetro, o tempo de suspensão em milissegundos e, quando é chamado, a _thread_ é suspensa é só volta a executar depois desse tempo informado.
Obs.: é importante notar que o tempo pode não ser _exatamente_ o valor passado, pode ser um pouquinho mais dependendo de quão ocupado está o seu sistema.

O pacote `exemploSleep` traz um código de exemplo de uma tarefa que usa o método `sleep`.
Neste exemplo, a classe principal cria dois objetos da tarefa `TarefaComSleep`, cada uma com um tempo de suspensão diferente.
Experimente executar o programa, rodando a partir da classe `App` que está dentro do pacote.



