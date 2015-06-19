package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardButton extends JButton {

	public void createNameCard(String[] names) {
		BufferedImage img = new BufferedImage(180, 124, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setColor(Color.BLUE);
		int textX = 16;
		int textY = 16;
		for (int i = 0; i < names.length; i++) {
			g.drawString(names[i], textX, textY);
			textX += 32;
			if((i + 1) % 5 == 0) {
				textY += 24;
				textX = 16;
			}
		}
		this.setIcon(new ImageIcon(img));
		this.setEnabled(true);
	}
	
	public void clearNames() {
		this.setIcon(null);
		this.setEnabled(false);
	}
}
