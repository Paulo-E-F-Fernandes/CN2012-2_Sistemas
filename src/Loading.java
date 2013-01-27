import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Loading extends JFrame implements Runnable {
	
	public void run() {
		
		JLabel loadingLabel;
		Container loadingPane;
		ImageIcon loadingGif = new ImageIcon(this.getClass().getResource("aguarde_processando.gif"));
		
		loadingLabel = new JLabel(loadingGif, JLabel.CENTER);
		loadingPane = this.getContentPane();
		loadingPane.setLayout(new GridLayout(1, 1));
		loadingPane.add(loadingLabel);
		loadingPane.setBackground(Color.white);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(220, 100);
		this.setVisible(true);
	}
	
	public void exit() {
		this.setVisible(false);
	}

}
