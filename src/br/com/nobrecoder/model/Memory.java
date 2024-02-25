package br.com.nobrecoder.model;

/**@Objetivo
 * Essa classe implementa a lógica de como iremos usar a memória da calculadora para armazenar valores operados e mostrar os valores atuais que serão digitados no teclado.
 * <p><p>
 * @Design_Pattern_usado
 * <b>Singleton:</b> esse padrão de projeto faz com que uma classe só possa ter uma única instância. Muitas vezes criada dentro da própria classe de forma estática. Podendo ser somente chamada, mas nunca instanciada.
 * */
public class Memory {
	
	private static final Memory INSTANCE_OF_MEMORY = new Memory(); //Aqui temos o objeto estático único da classe, por ser privado, só poderá ser chamado de forma estática.
	
	private String atualText = ""; //Note que o texto atual por ser dinamico poderá ser chamado por através de um getter a partir da instancia de Memory.
	
	private Memory() {} //Constructor é privado, isso que faz o padrão Singleton acontecer, pois o constructor nunca poderá ser chamado fora da classe.
	
	public static Memory getInstanceOfMemory() { //Getter para podermos chamar a instancia de Memory de forma estática.
		return INSTANCE_OF_MEMORY;
	}
	
	public String getAtualText() { //Um método para podermos visualizar o texto atual armazenado no atributo privado "atualText"
		return atualText.isEmpty() ? "0" : atualText; //O retorno desse método tem uma validação onde, se a String estiver vazia, retornará o valor "0", como as calculadoras geralmente fazem.
	}
}
