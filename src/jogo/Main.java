package jogo;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SistemaJogo sj = new SistemaJogo();
        Scanner sc = new Scanner(System.in);
        List<String> listaPalavras = List.of("java", "python",  "taylor", "swift", "showgirl", "sabrina", "manchild");
        Set<Character> listaTentativas = new HashSet<>();
        Set<Character> letrasCertas = new HashSet<>();
        String palavra = sj.sortearPalavra(listaPalavras);
        char letra;
        int vida = 6;
        boolean rodando = true;


        System.out.println("FORCA!!");
        System.out.println("Uma palavra com "+palavra.length()+" letras!");
        while (rodando == true) {

            sj.exibirPalavra(palavra, letrasCertas);
            System.out.println(" ");
            System.out.println("Digite uma letra");
            letra = sc.next().charAt(0);
            letra = Character.toLowerCase(letra);
            boolean tentativa = sj.tentativa(palavra, letra, listaTentativas, letrasCertas);
            if (tentativa == false) {
                vida -= 1;
            }else {
                System.out.println("A palavra contém a letra: "+letra);
            }
            sj.exibirTentativas(listaTentativas, vida, letrasCertas);
            if(vida == 0){
                System.out.println("Você perdeu!!!");
                rodando = false;
            }

            if (palavra.chars().allMatch(c -> letrasCertas.contains((char) c))) {
                System.out.println("Parabéns, você ganhou! A palavra era: " + palavra);
                rodando = false;
            }

        }
    }
}
