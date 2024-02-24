package br.com.nobrecoder.vision;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**@Objetivo
 * Essa classe define o visual dos botões da calculadora.
 * <p><p>
 * @extends
 * Ela herda os elementos de JButton por herança. JButton é um dos pacotes de swing, que contém os elementos necessários para criarmos e renderizarmos botões na tela. Bem como, controlar seus estilos.
 * */

@SuppressWarnings("serial")
public class Button extends JButton{
	public Button(String text, Color color) { //Recebe como parâmetro o texto do botão e sua cor
		setBackground(color); //Define a cor
		setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Define o tipo de borda e cor
		setFont(new Font("courier", Font.PLAIN, 25)); //Define a família da fonte, estilo e tamanho
		setForeground(Color.WHITE); //Define a cor do texto do botão
		setOpaque(true); //Define que o botão terá o fundo opaco (se estiver false o botão ficará transparente) 
		setText(text); //Define o texto para o botão
	}
}
