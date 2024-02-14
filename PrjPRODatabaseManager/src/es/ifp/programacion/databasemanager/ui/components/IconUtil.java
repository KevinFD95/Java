package es.ifp.programacion.databasemanager.ui.components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class IconUtil {

	public static void setDefaultIcon(JFrame frame) {
		ImageIcon defaultIcon = new ImageIcon("icons/usuario.png");
		Image scaledImage = defaultIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		frame.setIconImage(scaledIcon.getImage());
	}
}