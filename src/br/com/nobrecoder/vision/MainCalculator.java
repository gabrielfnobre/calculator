package br.com.nobrecoder.vision;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**@OBJETIVO_DA_CLASSE
 * Essa é classe principal que carrega a nossa aplicação da calculadora, bem como é uma classe do pacote "vision" responsável por renderizar a nossa calculadora na tela, por isso essa classe extends de JFrame.
 * <p><p>
 * @extends
 * JFrame é um pacote do swing responsável por renderizar uma tela visual para nossa aplicação.
 * */

@SuppressWarnings("serial")
public class MainCalculator extends JFrame {
	
	/**@constructor
	 * Constructor dessa classe é o responsavél por renderizar nossa tela, tamanho dela e botão de cancelamento da aplicação;
	 * */
	public MainCalculator() {
		
		organizeLayout(); //Método para definir como o layout ficará organizado na tela;
		
		setSize(233, 322); //Define tamanho da tela
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Define que a aplicação será encerrada quando o botão de fechar for clicado
		setLocationRelativeTo(null); //Define que e tela será inicializada no centro da tela do monitor
		setVisible(true); //Declara a tela e seus elementos como visiveis.
	}
	
	/**@Objetivo
	 * Esse método define como o layout da calculadora deve ser renderizado na tela.
	 * */
	private void organizeLayout() {
		setLayout(new BorderLayout()); //Define que a direção do layout será organizada pelas bordas;
		
		Display display = new Display();
		Keyboard keyboard = new Keyboard();
		
		display.setPreferredSize(new Dimension(233, 60));
		add(display, BorderLayout.NORTH); //Define que a preferência é que o display se posicione ao norte a partir da borda.
		add(keyboard, BorderLayout.CENTER); //Define que a preferência é que o telhado da calculadora se posicione no centro a partir da borda.
	}

	/**@main
	 * Essa é a classe main da aplicação, responsável por chamar o método construtor;
	 * */
	public static void main(String[] args) {
		new MainCalculator();
	}

}
