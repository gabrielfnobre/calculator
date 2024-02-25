package br.com.nobrecoder.vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**@Objetivo
 * Essa classe define o estilo do teclado da calculadora, como os botões ficarão dispostos no teclado.
 * <p><p>
 * @extends
 * Essa classe extende de JPanel, o que faz com que ela possa receber outros componentes visuais dentro dela e colocá-los dispostos segundo o layout que definirmos.
 * <p><p>
 * @implements
 * Essa classe implementa a interface ActionListener. Essa interface implementa o método actionPerformed, que irá ser chamado quando uma determinada ação for performada. A missão dessa interface é fazer com que a classe atual escute os observers que fizerem determinada ação retornando algum comportamento característico.
 * */

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener {

	//Cores pré escolhidas...
	private final Color COLOR_DARK_GRAY = new Color(68, 68, 68);
	private final Color COLOR_LIGTH_GRAY = new Color(99, 99, 99);
	private final Color COLOR_ORANGE = new Color(242, 163, 60);
	
	public Keyboard() { //Construtor do teclado
		GridBagLayout layout = new GridBagLayout(); //Escolhemos o estilo de layout GridBagLayout, uma classe herdeira de Layout, essa classe possuí algumas formas de gerir layout peculiares, que nos permitem dispor um grid layout de uma forma diferente do convencional da classe Layout.
		GridBagConstraints constraint = new GridBagConstraints(); //O GridBagConstraints nos permite definir como linhas e colunas serão dispostas em um GrigBagLayout por através de eixos x e y, atribuíndo numerações aos nossos componentes.
		
		setLayout(layout); //Definimos que o layout será um GridBagLayout
		
		constraint.weightx = 1; //Esse atributo nos ajuda a definir como será o comportamentos do preenchimento dos objetos dentro do grid entre si, quando o valor é "1" significa que independente do tamanho dos objetos entre si, em relação ao eixo "x" todos terão a mesma largura.
		constraint.weighty = 1; //Igual o exemplo acima, só que em relação ao eixo "y".
		constraint.fill = GridBagConstraints.BOTH; //O atributo "fill" de um objeto GridBagConstraints faz com que os elementos dentro de um grid ocupem todo o espaço disponível para um grid. Para isso esse atributo deve receber como valor um inteiro que define se o preenchimento deve ser feito em relação ao eixo x, ao y ou para ambos os lados. Para nos ajudar a encontrar o valor, existem constantes em GridBagConstraints que nos ajudam a encontrar esse valor, estamos usando no caso "BOTH" que nos ajuda a encontrar o valor para preencher o GridBagConstraints para ambos os lados.
		
		//LINE 01
		constraint.gridwidth = 3; //Como queremos que o botão AC ocupe a largura de 3 colunas do grid, mudamos o seu gridwidth para "3" em vez do 1, que seria o padrão.
		addButton("AC", COLOR_DARK_GRAY, constraint, 0, 0); //Estamos usando um método criado por nós mesmo para adicionar os botões, veja mais detalhes abaixo...
		constraint.gridwidth = 1; //Após a mudança do botão "AC", temos que voltar o gridwidth para "1", se não todos os demais botões terão também o grid de 3 entre si...
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
		constraint.gridwidth = 2; //Queremos que o botão "0" ocupe 2 colunas...
		addButton("0", COLOR_LIGTH_GRAY, constraint, 0, 4);
		constraint.gridwidth = 1; //Retornando a largura dos elementos para 1 de novo...
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
		Button button = new Button(text, color); //Criamos um botão.
		button.addActionListener(this); //Adicionamos os botões como observer de um ActionListener, ou seja, quando um botão for clicado ele será reconhecido como um observer, e uma ação que foi implementada no método actionPerformed será executada.
		add(button, constraint); //O método add que JPanel pode receber como parâmetros um componente e em segundo lugar um constraint, que define a posição onde ele ficará no grid. Posição essa que já foi atribuída aos atributos gridx e gridy.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //Implementa uma ação para quando um observer de ActionListener for reconhecido.
		if(e.getSource() instanceof JButton) { //Valida que somente botões sejam reconhecidos. O método getSource de um ActionEvent retornar o observer que foi origem do evento ActionListener
			JButton button = (JButton) e.getSource(); //Como o objeto de retorno de um e.getSource só poderá ser um JButton, nesse caso atribuímos ele devolta a uma nova instancia de JButton para poder capturar o texto que está nele...
			System.out.println(button.getText());
		}
	}
	
}
