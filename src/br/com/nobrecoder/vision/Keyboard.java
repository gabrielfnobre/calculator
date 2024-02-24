package br.com.nobrecoder.vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**@Objetivo
 * Essa classe define o estilo do teclado da calculadora, como os botões ficarão dispostos no teclado.
 * <p><p>
 * @extends
 * Essa classe extende de JPanel, o que faz com que ela possa receber outros componentes visuais dentro dela e colocá-los dispostos segundo o layout que definirmos.
 * */

@SuppressWarnings("serial")
public class Keyboard extends JPanel {

	//Cores pré escolhidas...
	private final Color COLOR_DARK_GRAY = new Color(68, 68, 68);
	private final Color COLOR_LIGTH_GRAY = new Color(99, 99, 99);
	private final Color COLOR_ORANGE = new Color(242, 163, 60);
	
	public Keyboard() { //Construtor do teclado
		GridBagLayout layout = new GridBagLayout(); //Escolhemos o estilo de layout GridBagLayout, uma classe herdeira de Layout, essa classe possuí algumas formas de gerir layout peculiares, que nos permitem dispor um grid layout de uma forma diferente do convencional da classe Layout.
		GridBagConstraints constraint = new GridBagConstraints(); //O GridBagConstraints nos permite definir como linhas e colunas serão dispostas em um GrigBagLayout por através de eixos x e y, atribuíndo numerações aos nossos componentes.
		
		setLayout(layout); //Definimos que o layout será um GridBagLayout
		
		//LINE 01
		addButton("AC", COLOR_DARK_GRAY, constraint, 0, 0); //Estamos usando um método criado por nós mesmo para adicionar os botões, veja mais detalhes abaixo...
		addButton("+/-", COLOR_DARK_GRAY, constraint, 1, 0); /**@Sobre_posicao_de_constraint: O valor de x define em que linha do GridBagLayout um elemento ficará, o valor y define em que coluna componente ficará.*/
		addButton("%", COLOR_DARK_GRAY, constraint, 2, 0);
		addButton("/", COLOR_ORANGE, constraint, 3, 0);
		
		//LINE 02
		addButton("7", COLOR_LIGTH_GRAY, constraint, 0, 1);
		addButton("8", COLOR_LIGTH_GRAY, constraint, 1, 1);
		addButton("9", COLOR_LIGTH_GRAY, constraint, 2, 1);
		addButton("*", COLOR_ORANGE, constraint, 3, 1);
		
		//LINE 03
		addButton("4", COLOR_LIGTH_GRAY, constraint, 0, 2);
		addButton("5", COLOR_LIGTH_GRAY, constraint, 1, 2);
		addButton("6", COLOR_LIGTH_GRAY, constraint, 2, 2);
		addButton("-", COLOR_ORANGE, constraint, 3, 2);
		
		//LINE 04
		addButton("1", COLOR_LIGTH_GRAY, constraint, 0, 3);
		addButton("2", COLOR_LIGTH_GRAY, constraint, 1, 3);
		addButton("3", COLOR_LIGTH_GRAY, constraint, 2, 3);
		addButton("+", COLOR_ORANGE, constraint, 3, 3);
		
		//LINE 05
		addButton("0", COLOR_LIGTH_GRAY, constraint, 0, 4);
		addButton("0", COLOR_LIGTH_GRAY, constraint, 1, 4);
		addButton(",", COLOR_LIGTH_GRAY, constraint, 2, 4);
		addButton("=", COLOR_ORANGE, constraint, 3, 4);
		
	}

	/**@Objetivo
	 * Esse método foi criado para facilitar a criação dos botões dentro do GridBagLayout.
	 * <p><p>
	 * @Parameters
	 * Text: texto do botão | color: cor do botão | constraint: objeto GridBagConstraint | x: índice do eixo "x" | y: índice do eixo: "y"
	 * */
	private void addButton(String text, Color color, GridBagConstraints constraint, int x, int y) {
		constraint.gridx = x; //O constraint define uma numeração para a posição de um componente no eixo "x"
		constraint.gridy = y; //Neste caso define uma numeração para a posição do componente no eixo "y"
		add(new Button(text, color), constraint); //O método add que JPanel pode receber como parâmetros um componente e em segundo lugar um constraint, que define a posição onde ele está.
	}
	
}
