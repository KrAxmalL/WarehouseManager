package org.ukma.warehouse.Start;

import org.ukma.warehouse.Controllers.Main.MainController;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainClient {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				MainController controller = new MainController();
			}
		});
	}
}
