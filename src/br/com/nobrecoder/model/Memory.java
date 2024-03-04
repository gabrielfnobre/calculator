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
	
	private enum TypeCommand { //Essa enum foi criada de forma embutida dentro da classe Memory. A missão dela é identificar que tipo de botão foi clicado na calculadora, através da identificação do tipo de botão, é possível fazer operações de forma correta.
		TO_ZERO, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA
	}
	private static final Memory INSTANCE_OF_MEMORY = new Memory(); //Aqui temos o objeto estático único da classe, por ser privado, só poderá ser chamado de forma estática.
	private final List<ObserverMemory> observers = new ArrayList<>(); //Nesta lista serão armazenados todos os observers interessados em saber quando um botão foi clicado e que tipo de botão foi clicado.
	private String atualText = ""; //Note que o texto atual por ser dinamico poderá ser chamado por através de um getter a partir da instancia de Memory.
	private String bufferText = ""; //Essa variável será utilizada para armazenar valores operados que devem ser guardados para o calculo seja feito.
	private boolean replaceDisplay = false; //Essa variável booleana será usada para mostrar quando o display deverá ser limpo para que um próximo número entre no calculo em andamento.
	private TypeCommand lastOperator = null; //Essa variável irá armazenar o tipo de comando (enum) que está sendo operado. 
	
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
		
		TypeCommand typeCommand = detectComand(value); //Recebe o tipo de botão, que é identificado pela enum acima, por através do método "detectComand", que faz um filtro dos valores passados para identificar qual é o tipo de botão correto.
		
		//Abaixo temos um laço else if que avalia que tipo de tecla foi acionada pelo usuário, de acordo com o tipo de tecla, uma determinada operação deverá ser feita.
		if(typeCommand == null) { //Se estiver nulo, é por que nenhuma operação deverá ser feita...
			return;
		} else if(typeCommand == TypeCommand.TO_ZERO) { //Se for o "AC" que contém o tipo de comando para "zerar" a calculadora...
			atualText = "";
			bufferText = "";
			replaceDisplay = false;
			lastOperator = null;
		} else if(typeCommand == TypeCommand.NUMBER || typeCommand == TypeCommand.COMMA) { //se o tipo de comando for um número ou vírgula...
			atualText = replaceDisplay ? value : atualText + value;
			replaceDisplay = false;
		} else { //se o tipo de comando for uma operação + - * / ou =...
			replaceDisplay = true; //Para limpar o display na próxima operação
			atualText = getResultOfOperation(); //chama o método que irá operar os valores...
			bufferText = atualText; //Armazena a operação no buffer...
			lastOperator = typeCommand; //Passa a operação para a variável "lastOperator" identificar qual foi a última operação feita...
		}
		
		observers.forEach(o -> o.alteredValue(getAtualText())); //Avisa todos os observadores que o botão foi clicado e o valor do display deverá ser alterado.
	}

	/**@Objetivo
	 * 	Esse método é utilizado para identificar qual é o tipo de operação que deverá ser feita na calculadora e devolver o valor operado...
	 * */
	private String getResultOfOperation() {
		if(lastOperator == null || lastOperator == TypeCommand.EQUAL) { //Se nenhuma operação foi acionada ou se a operação continuar, devolve somente o último número armazenado...
			return atualText;
		}
		
		double bufferNumber = Double.parseDouble(bufferText.replace(",", ".")); //Troca a "," do valor do buffer por "." e transforma em double...
		double atualNumber = Double.parseDouble(atualText.replace(",", ".")); //Troca a "," do valor do número atual por "." e transforma em double...
		double result = 0; //Variável que armazenará o resultado final...
		
		//Confere qual é o tipo de operação e opera o valor do buffer contra o valor atual coletado...
		if(lastOperator == TypeCommand.SUM) {
			result = bufferNumber + atualNumber;
		} else if(lastOperator == TypeCommand.SUB) {
			result = bufferNumber - atualNumber;
		} else if(lastOperator == TypeCommand.MULT) {
			result = bufferNumber * atualNumber;
		} else if(lastOperator == TypeCommand.DIV) {
			result = bufferNumber / atualNumber;
		}
		
		String stringResult = Double.toString(result).replace(".", ","); //Transforma o valor coletado no resultado de double para string devolta devolvendo a vírgula no lugar do ponto...
		boolean integer = stringResult.endsWith(",0"); //Para conferência se o valor termina com ",0", para eliminar o ",0" da calculadora.
		return integer ? stringResult.replace(",0", "") : stringResult; //Confere se o número pode ser um interger, se sim, elimina o ",0", se não devolve o número com ponto decimal, normalmente.
	}

	/**@Objetivo
	 * Identificar o tipo de botão que foi clicado por através das enums...
	 * */
	private TypeCommand detectComand(String value) { //Recebe como parâmetro o texto que está escrito encima do botão.

		if(atualText.isEmpty() && value == "0") { //Esse laço "if" estabelece a lógica para não fazer nada quando uma calculadora já estiver com o valor zero e um zero for clicado. Tornando o display inalterado.
			return null;
		}
		
		try { //O try catch identifica se o valor passado pode ser convertido em um número ou não, se puder, ele retorna a informação de que o botão clicado é do tipo NUMBER... 
			Integer.parseInt(value);
			return TypeCommand.NUMBER;
		} catch (NumberFormatException e) { //Se o valor não for um NUMBER, entra-se no catch...
			if("AC".equals(value)){ //Esse laço "if" identifica cada operação da calculadora retornando a sua enum correspondente...
				return TypeCommand.TO_ZERO;
			} else if("/".equals(value)) {
				return TypeCommand.DIV;
			} else if("*".equals(value)) {
				return TypeCommand.MULT;
			} else if("+".equals(value)) {
				return TypeCommand.SUM;
			} else if("-".equals(value)) {
				return TypeCommand.SUB;
			} else if("=".equals(value)) {
				return TypeCommand.EQUAL;
			} else if(",".equals(value) && !atualText.contains(",")) {
				return TypeCommand.COMMA;
			}
		}
		
		return null;
	}
}
