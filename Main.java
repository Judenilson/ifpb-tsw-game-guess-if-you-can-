import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

  public static void main(String args[]) throws IOException {
    Path path = Paths.get("words.txt"); //caminho do arquivo contendo as palavras
    List<String> linhasArquivo = Files.readAllLines(path); //colocando os arquvios numa lista

    HashMap<Integer,List<String>> dicPalavras = new HashMap<Integer,List<String>>(); 
    // separando as palavras num dicionário, colocando o tamanho das palavras como índice.
    for (String item : linhasArquivo){
      if (dicPalavras.get(item.length()) != null){
        dicPalavras.get(item.length()).add(item);
      }
      else{
        List<String> vazia = new ArrayList<String>();
        dicPalavras.put(item.length(),vazia);
        dicPalavras.get(item.length()).add(item);
      }
    }
          
    //Lendo dados de entrada do jogador
    Scanner lerEntrada = new Scanner(System.in);
    //RF1: O jogador pode escolher o tamanho da palavra (o mínimo é 2 letras)    
    System.out.println("::::: JOGO ADIVINHE SE PUDER :::::");
    System.out.println("Informe o tamanho da palavra, entre 2 e 23:");
    Integer tamanho = lerEntrada.nextInt();
    Integer tentativas = 1;
    boolean flag = false;
    while (flag != true){
      if (tamanho<2 || tamanho>24){
        System.out.println("Tamanho de palavra não permmitido.");
        System.out.println("Sério!? A palavra deve ter tamanho entre 2 e 23:");
        tamanho = lerEntrada.nextInt();
      } else {
        flag = true;
      }
    }
    flag = false;
    while (flag != true){
      System.out.println("Quantas tentativas você quer para adivinhar?");
      tentativas = lerEntrada.nextInt();
      if (tentativas < 1){          
        System.out.println("Você entendeu? A resposta tem que ser maior que zero!");
      } else {        
        flag = true;
      }
    }    
        
    System.out.println("!!!!!!!!!!!!!!!!!");
    System.out.printf("Sorteando palavra de tamanho %d para iniciar o Jogo.\n", tamanho);
    System.out.println("!!!!!!!!!!!!!!!!!");
    
    //RF2: A palavra deve ser sorteada da lista que se encontra nesse link
    Random sorteio = new Random();
    Integer countNumeroPalavras = dicPalavras.get(tamanho).size();
    Integer numSorteado = sorteio.nextInt(countNumeroPalavras);
    String palavraSorteada = dicPalavras.get(tamanho).get(numSorteado);
    Integer contarVitoria = 0;

    String resposta = "";
    char palavraSorteadaAnalisada[] = palavraSorteada.toCharArray();
    System.out.printf("PARA TESTE! A palavra sorteada foi: ");
    System.out.println(palavraSorteadaAnalisada); //print para saber a palavra e poder testar!!! Quando OK deve ser comentado ou deletado.
    
    System.out.println("::::: Jogo Iniciado, Divirta-se! :::::");
    System.out.println("Para sair escreva 'x'");
    while (true){
      palavraSorteadaAnalisada = palavraSorteada.toCharArray(); //a cada rodada a palavra tem q ser resetada, para analisar sem o "cincos" inseridos.
      System.out.println("Informe uma palavra:");
      String palavra = lerEntrada.next();      
      if (palavra.equals(palavraSorteada)){ //condição de vitória do jogo
        System.out.println("Você acertou, Parabéns!!!");   
        break;
      }
      if (palavra.equals("x")){ break;} //condição de parada do jogo apenas durante TESTES, não está nos requisitos, deve ser REMOVIDO!
       
      //erro caso o usuário digite palavras de tamanhos diferentes do determinado no início do jogo.
      if (palavra.length() != tamanho){
        System.out.println("Tamanho errado da palavra digitada!");        
      } 
      //verifica se o usuário escreveu, de fato, uma palavra ou letras aleatórias.
      else if(!dicPalavras.get(tamanho).contains(palavra)){ 
        System.out.println("A palavra digitada não existe!");       
      } 
      //RF4: Letra descoberta que está no local correto deve ser exibida em caixa alta.
      else{
        resposta = ""; //apaga a resposta a cada loop de tentativas
        contarVitoria = 0;
        for (int i=0; i<palavra.length(); i++) { //itera cada letra da palavra digitada
          flag = true;
          char letra = palavra.charAt(i);   
          for (int j=0; j<tamanho; j++){ //itera a palavra sorteada
            if (letra == palavraSorteadaAnalisada[j]){   
              flag = false;
              if(i==j){ //match, letra e posição, resposta com letra maiúscula.
                resposta += Character.toUpperCase(letra);
                palavraSorteadaAnalisada[i] = '5'; //coloca o 5 na posição que está correta.
                contarVitoria ++;
                } 
              else {
                String Letra = ""+letra;
                if (!resposta.contains(Letra)){                  
                  resposta += letra;
                }
              }
              break;
            }
          }         
          if (flag){resposta += "-";}
        }        
        System.out.println(resposta);
        if (contarVitoria == tamanho){
          System.out.println("Você acertou, Parabéns!!!");
          break;          
        } else{
          tentativas --;
          if (tentativas < 1){            
            System.out.println("Que pena, você perdeu!!!");
            break;
          }
        }
      }
      // System.out.println(palavraSorteadaAnalisada);
    }   
    
    System.out.printf("Fim do Jogo.");
  }
}
