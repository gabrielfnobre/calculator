package br.com.nobrecoder.vision;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.nobrecoder.model.Memory;
import br.com.nobrecoder.model.ObserverMemory;

/**@Objetivo
 * Essa classe define o display visual para a calculadora.
 * <p><p>
 * @extends
 * JPanel define um painel, como um container no css, para receber elementos dentro dele.
 * <p><p>
 * @implements
 * ObserverMemory é uma functional interface responsável por implementar o método que será executado toda vez que o usuário apertar um botão na calculadora. Esse método irá notificar os observers que um click aconteceu em algum botão, e a calculadora deverá se comportar de uma determinada forma dependendo do tipo de botão que for clicado.
 * */

@SuppressWarnings("serial")
public class Display extends JPanel implements ObserverMemory{
	
	private JLabel label = new JLabel(); //Uma label textual que irá dentro do painel, nessa label que irão os números operados pelos calculos da calculadora.
	
	public Display() {
		Memory.getInstanceOfMemory().addObservers(this); //Aqui estamos registrando o display como um observer interessado em saber que um click do botão ocorreu.
		
		setBackground(new Color(46, 49, 50)); //Cor de background do display
		label.setForeground(Color.WHITE); //Cor do texto do display
		label.setFont(new Font("courier", Font.PLAIN, 35)); //Familia, estilo e tamanho da fonte do display
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); //Modo como os objetos irão se comportar dentro do container do display, bem como, espaçamento entre o padding de cima e debaixo do display da aplicação.
		add(label); //a label foi adicionada ao display...
		
		label.setText(Memory.getInstanceOfMemory().getAtualText()); //Atualiza o texto do display...
	}
	
	/**@Objetivo
	 * Esse método é implementado em resposta a implementação da functional interface ObserverMemory. Esse método será chamado toda vez que o observer identificar uma mudança no estado do evento. Esse método tem como objetivo mudar o valor do display de acordo com o valor do botão que foi clicado.
	 * */
	
	@Override
	public void alteredValue(String newValue) {
		label.setText(newValue);
	}
}
