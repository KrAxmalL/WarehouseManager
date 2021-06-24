package org.example.Start;

import org.example.Controllers.Main.MainController;
import org.example.Network.Context.GlobalContext;

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
