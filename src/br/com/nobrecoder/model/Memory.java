package br.com.nobrecoder.model;

public class Memory {
	
	private static final Memory INSTANCE_OF_MEMORY = new Memory();
	
	private String atualText = "";
	
	private Memory() {}
	
	public static Memory getInstanceOfMemory() {
		return INSTANCE_OF_MEMORY;
	}
	
	public String getAtualText() {
		return atualText.isEmpty() ? "0" : atualText;
	}
}
