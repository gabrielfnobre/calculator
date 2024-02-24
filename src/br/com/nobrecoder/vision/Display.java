package br.com.nobrecoder.vision;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**@Objetivo
 * Essa classe define o display visual para a calculadora.
 * <p><p>
 * @extends
 * JPanel define um painel, como um container no css, para receber elementos dentro dele.
 * */

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	private JLabel label = new JLabel(); //Uma label textual que irá dentro do painel, nessa label que irão os números operados pelos calculos da calculadora.
	
	public Display() {
		setBackground(new Color(46, 49, 50)); //Cor de background do display
		label.setForeground(Color.WHITE); //Cor do texto do display
		label.setFont(new Font("courier", Font.PLAIN, 80)); //Familia, estilo e tamanho da fonte do display
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25)); //Modo como os objetos irão se comportar dentro do container do display, bem como, espaçamento entre o padding de cima e debaixo do display da aplicação.
		add(label); //a label foi adicionada ao display...
		
		label.setText("1234,56");
	}
	
}
