package jogo;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ForcaGUI extends JFrame{
    private SistemaJogo sistema;
    private List<String> listaPalavras;
    private Set<Character> listaTentativas;
    private Set<Character> letrasCertas;
    private String palavra;
    private int vida = 6;

    // Componentes da interface
    private JLabel lblPalavra;
    private JLabel lblVidas;
    private JLabel lblCertas;
    private JLabel lblErradas;
    private JTextField txtLetra;
    private JButton btnTentar;

    public ForcaGUI() {
        // Backend
        sistema = new SistemaJogo();
        listaPalavras = List.of("java", "python", "taylor", "swift", "showgirl", "sabrina", "manchild");
        listaTentativas = new HashSet<>();
        letrasCertas = new HashSet<>();
        palavra = sistema.sortearPalavra(listaPalavras);

        // Configuração da Janela
        setTitle("Jogo da Forca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel painelCentro = new JPanel(new GridLayout(5, 1));

        lblPalavra = new JLabel(getPalavraFormatada(), SwingConstants.CENTER);
        lblPalavra.setFont(new Font("Arial", Font.BOLD, 24));

        lblVidas = new JLabel("Tentativas restantes: " + vida, SwingConstants.CENTER);
        lblCertas = new JLabel("Letras certas: ", SwingConstants.CENTER);
        lblErradas = new JLabel("Letras erradas: ", SwingConstants.CENTER);

        painelCentro.add(lblPalavra);
        painelCentro.add(lblVidas);
        painelCentro.add(lblCertas);
        painelCentro.add(lblErradas);

        // Painel de input
        JPanel painelBaixo = new JPanel();
        txtLetra = new JTextField(5);
        btnTentar = new JButton("Tentar");
        painelBaixo.add(new JLabel("Digite uma letra: "));
        painelBaixo.add(txtLetra);
        painelBaixo.add(btnTentar);

        // Adiciona na janela
        add(painelCentro, BorderLayout.CENTER);
        add(painelBaixo, BorderLayout.SOUTH);

        // Ação do botão
        btnTentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogar();
            }
        });

        setLocationRelativeTo(null); // centralizar
        setVisible(true);
    }

    private void jogar() {
        if (vida == 0 || palavra.chars().allMatch(c -> letrasCertas.contains((char) c))) {
            return; // jogo já acabou
        }

        String entrada = txtLetra.getText().trim().toLowerCase();
        txtLetra.setText("");

        if (entrada.isEmpty()) return;
        char letra = entrada.charAt(0);

        boolean acertou = sistema.tentativa(palavra, letra, listaTentativas, letrasCertas);

        if (!acertou) {
            vida--;
        }

        // Atualiza interface
        lblPalavra.setText(getPalavraFormatada());
        lblVidas.setText("Tentativas restantes: " + vida);
        lblCertas.setText("Letras certas: " + letrasCertas);
        lblErradas.setText("Letras erradas: " + listaTentativas);

        // Checa vitória ou derrota
        if (vida == 0) {
            JOptionPane.showMessageDialog(this, "Você perdeu! A palavra era: " + palavra);
        } else if (palavra.chars().allMatch(c -> letrasCertas.contains((char) c))) {
            JOptionPane.showMessageDialog(this, "Parabéns, você ganhou! A palavra era: " + palavra);
        }
    }

    private String getPalavraFormatada() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            if (letrasCertas.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new ForcaGUI();
    }
}

