package br.com.nobrecoder.vision;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainCalculator extends JFrame {
	
	public MainCalculator() {
		setSize(432, 522);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainCalculator();
	}

}
