package casc;

import java.awt.image.BufferedImage;

public class Animal {
	private BufferedImage img;
	private String type;

	public Animal(String s, BufferedImage i) {
		type = s;
		img = i;
	}

	public BufferedImage getImage() {
		return img;
	}

	public String getType() {
		return type;
	}
}
