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
		setBackground(new Color(46, 49, 50));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 80));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		add(label);
		
		label.setText("0000");
	}
	
}
