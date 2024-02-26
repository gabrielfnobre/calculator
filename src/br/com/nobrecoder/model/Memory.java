package br.com.nobrecoder.model;

import java.util.ArrayList;
import java.util.List;

/**@Objetivo
 * Essa classe implementa a lógica de como iremos usar a memória da calculadora para armazenar valores operados e mostrar os valores atuais que serão digitados no teclado.
 * <p><p>
 * @Design_Pattern_usado
 * <b>Singleton:</b> esse padrão de projeto faz com que uma classe só possa ter uma única instância. Muitas vezes criada dentro da própria classe de forma estática. Podendo ser somente chamada, mas nunca instanciada.
 * */
public class Memory {
	
	private enum TypeComand { //Essa enum foi criada de forma embutida dentro da classe Memory. A missão dela é identificar que tipo de botão foi clicado na calculadora, através da identificação do tipo de botão, é possível fazer operações de forma correta.
		TO_ZERO, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA
	}
	private static final Memory INSTANCE_OF_MEMORY = new Memory(); //Aqui temos o objeto estático único da classe, por ser privado, só poderá ser chamado de forma estática.
	private final List<ObserverMemory> observers = new ArrayList<>(); //Nesta lista serão armazenados todos os observers interessados em saber quando um botão foi clicado e que tipo de botão foi clicado.
	private String atualText = ""; //Note que o texto atual por ser dinamico poderá ser chamado por através de um getter a partir da instancia de Memory.
	
	private Memory() {} //Constructor é privado, isso que faz o padrão Singleton acontecer, pois o constructor nunca poderá ser chamado fora da classe.
	
	public static Memory getInstanceOfMemory() { //Getter para podermos chamar a instancia de Memory de forma estática.
		return INSTANCE_OF_MEMORY;
	}
	
	public String getAtualText() { //Um método para podermos visualizar o texto atual armazenado no atributo privado "atualText"
		return atualText.isEmpty() ? "0" : atualText; //O retorno desse método tem uma validação onde, se a String estiver vazia, retornará o valor "0", como as calculadoras geralmente fazem.
	}
	
	/**@Objetivo
	 * Registrar os observers dentro da lista de observers.
	 * */
	public void addObservers(ObserverMemory o) {
		observers.add(o);
	}
	
	/**@Objetivo
	 * Receber o texto do botão que foi clicado e passar o valor desse botão para a lógica da calculadora, que irá primeiro identificar o tipo de botão que foi clicado e qual o seu valor, para que possa calcular e guardar na memória os valores e operações que forem passadas.
	 * */
	public void processCalc(String value) { //Recebe uma string como valor, essa string contém a string que fica no botão.
		
		TypeComand typeComand = detectComand(value); //Recebe o tipo de botão, que é identificado pela enum acima, por através do método "detectComand", que faz um filtro dos valores passados para identificar qual é o tipo de botão correto.
		System.out.println(typeComand);
		
		atualText += value; //Adiciona o valor passado ao valor pré-existente em memória.
		observers.forEach(o -> o.alteredValue(getAtualText())); //Avisa todos os observadores que o botão foi clicado e o valor do display deverá ser alterado.
	}

	/**@Objetivo
	 * Identificar o tipo de botão que foi clicado por através das enums...
	 * */
	private TypeComand detectComand(String value) { //Recebe como parâmetro o texto que está escrito encima do botão.

		if(atualText.isEmpty() && value == "0") { //Esse laço "if" estabelece a lógica para não fazer nada quando uma calculadora já estiver com o valor zero e um zero for clicado. Tornando o display inalterado.
			return null;
		}
		
		try { //O try catch identifica se o valor passado pode ser convertido em um número ou não, se puder, ele retorna a informação de que o botão clicado é do tipo NUMBER... 
			Integer.parseInt(value);
			return TypeComand.NUMBER;
		} catch (NumberFormatException e) { //Se o valor não for um NUMBER, entra-se no catch...
			if("AC".equals(value)){ //Esse laço "if" identifica cada operação da calculadora retornando a sua enum correspondente...
				return TypeComand.TO_ZERO;
			} else if("/".equals(value)) {
				return TypeComand.DIV;
			} else if("*".equals(value)) {
				return TypeComand.MULT;
			} else if("+".equals(value)) {
				return TypeComand.SUM;
			} else if("-".equals(value)) {
				return TypeComand.SUB;
			} else if("=".equals(value)) {
				return TypeComand.EQUAL;
			} else if(",".equals(value)) {
				return TypeComand.COMMA;
			}
		}
		
		return null;
	}
}
