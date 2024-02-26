package br.com.nobrecoder.model;

/**@Objetivo
 * Essa Functional Interface define que todos os observers que a utilizem implementem o método de alteração de valor quando forem chamadas.
 * */

@FunctionalInterface
public interface ObserverMemory {
	public void alteredValue(String newValue);
}
