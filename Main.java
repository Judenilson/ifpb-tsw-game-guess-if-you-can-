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
    System.out.printf("Informe o tamanho da palavra, entre 2 e 23:\n");
    Integer tamanho = lerEntrada.nextInt();
    boolean flag = false;
    while (flag != true){
      if (tamanho<2 || tamanho>24){
        System.out.printf("Tamanho de palavra não permmitido.\n");
        tamanho = lerEntrada.nextInt();
      }
      else{
        System.out.printf("Sorteando palavra de tamanho %d para iniciar o Jogo.\n", tamanho);
        flag = true;
      }
    }
      
    System.out.printf("Informe uma palavra:\n");
    String palavra = lerEntrada.next();
    //tem q tratar essa entrada de palavra, pois pode haver um tamanho q não existe no dic.
    for (String item : dicPalavras.get(tamanho)){
      if( item.equalsIgnoreCase(palavra) ){
        System.out.println("Achei");
        break;
      }
    }
    System.out.printf("Encerrado.");        
    //System.out.println(dicPalavras.keySet().toString());
    //System.out.println(dicPalavras.get(18));

  }
}
