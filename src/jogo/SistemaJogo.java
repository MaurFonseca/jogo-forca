package jogo;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SistemaJogo {
    public String sortearPalavra(List<String> listaPalavras){
        Random rd = new Random();
        int sorteio = rd.nextInt(0, listaPalavras.size());

        return listaPalavras.get(sorteio);

    }


    public boolean tentativa(String palavra, char letra, Set<Character> tentativaLetras, Set<Character> lestrasCertas){
        boolean teste = false;
        for (int i = 0; i < palavra.length(); i++) {
            char caracterAtual = palavra.charAt(i);
            if(letra == caracterAtual){
                lestrasCertas.add(letra);
                return teste = true;
            }
        }
        tentativaLetras.add(letra);
        System.out.println("A plavra não contém a letra: "+letra);
        return teste;

    }

    public void exibirTentativas(Set<Character> tentativaLetras, int vida, Set<Character> letrasCertas){
        System.out.println("LETRAS ERRADAS TESTADAS: ");
        tentativaLetras.forEach(letra -> System.out.printf("%s | ",letra));
        System.out.println("\n");
        System.out.println("LETRAS CERTAS TESTADAS");
        letrasCertas.forEach(letra-> System.out.printf("%s | ", letra));
        System.out.println("\n");
        System.out.println("Tentativas restantes: "+vida);

    }

    public void exibirPalavra(String palavra, Set<Character> letrasCertas){
        String[] resposta = new String[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            char caracterAtual = palavra.charAt(i);
            if (letrasCertas.contains(caracterAtual)){
                resposta[i] = String.valueOf(caracterAtual);
            }else {
                resposta[i] = "_ ";
            }

        }
        for (int i = 0; i < resposta.length; i++) {
            System.out.printf("%s ", resposta[i]);
        }

    }
}
