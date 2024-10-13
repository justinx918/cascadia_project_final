package casc;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile extends Display {
	private int rotation; // for each 1 rotation tile should be 60degrees clockwise
	// private BufferedImage image;
	private BufferedImage image; // string will be used for image as a testing placeholder; replace w/ buffimage-
	// -when possible
	private ArrayList<String> possibleAnimals;
	private Tile edge1, edge2, edge3, edge4, edge5, edge6;
	private String[] habitats;
	private Animal animal = null;
	private int row;
	private int coll;
	private boolean isStarter = false;
	private boolean isChecked = false;
	private boolean isPopulated = false;

	public Tile() {
		// image = ImageIO.read(new File("\\images\\"+img));
		super();
		possibleAnimals = new ArrayList<String>();
		rotation = 0;
		habitats = new String[6];
	}

	public Tile(String l, String r) {
		super();
		habitats = new String[6];
		possibleAnimals = new ArrayList<String>();
		habitats[0] = l;
		habitats[1] = l;
		habitats[2] = l;
		habitats[3] = r;
		habitats[4] = r;
		habitats[5] = r;
		isStarter = true;
	}

	public boolean getIsChecked() {
		return isChecked;
	}

	public void checked(boolean t) {
		isChecked = t;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage img) {
		image = img;
	}

	public void addLeft(String l) {
		habitats[0] = l;
		habitats[1] = l;
		habitats[2] = l;
	}

	public void setRC(int r, int c) {
		row = r;
		coll = c;
	}

	public int getRow() {
		return row;
	}

	public int getColl() {
		return coll;
	}

	public void addRight(String r) {
		habitats[3] = r;
		habitats[4] = r;
		habitats[5] = r;
	}

	public void addPotentialAnimal(String a) {
		possibleAnimals.add(a);
	}

	public void rotateRight() {
		if (rotation == 5)
			rotation = 0;
		else
			rotation++;

		String[] placeholder = habitats.clone();
		habitats[0] = placeholder[5];
		habitats[1] = placeholder[0];
		habitats[2] = placeholder[1];
		habitats[3] = placeholder[2];
		habitats[4] = placeholder[3];
		habitats[5] = placeholder[4];
	}

	public void rotateLeft() {
		if (rotation == 0)
			rotation = 5;
		else
			rotation--;

		String[] placeholder = habitats.clone();
		habitats[0] = placeholder[1];
		habitats[1] = placeholder[2];
		habitats[2] = placeholder[3];
		habitats[3] = placeholder[4];
		habitats[4] = placeholder[5];
		habitats[5] = placeholder[0];
	}

	public int getRotation() {
		return rotation;
	}

	public void rotate() {
		rotateLeft();
		if (!isStarter)
			image = rotate(image);
	}

	public ArrayList<String> getPotentialAnimals() {
		return possibleAnimals;
	}

	public String getSide(int d) {
		String side = "";
		switch (d) {
		case 0:
			side = habitats[0];
			break;
		case 1:
			side = habitats[1];
			break;
		case 2:
			side = habitats[2];
			break;
		case 3:
			side = habitats[3];
			break;
		case 4:
			side = habitats[4];
			break;
		case 5:
			side = habitats[5];
			break;
		}
		return side;
	}

	public boolean isKeyStone() {
		if (getPotentialAnimals().size() == 1)
			return true;
		return false;
	}

	public String toString() {
		String str = "" + habitats[0] + " " + habitats[4] + possibleAnimals;
		if (isPopulated)
			str += getAnimal().getType();
		return str;
	}

	private BufferedImage rotate(BufferedImage img) {

		// Getting Dimensions of image
		int width = img.getWidth();
		int height = img.getHeight();

		// Creating a new buffered image
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		// creating Graphics in buffered image
		Graphics2D g2 = newImage.createGraphics();

		// Rotating image by degrees using toradians()
		// method
		// and setting new dimension t it
		g2.rotate(Math.toRadians(60), width / 2, height / 2);
		g2.drawImage(img, null, 0, 0);

		// Return rotated buffer image
		return newImage;
	}

	public void addAnimal(Animal a) {
		animal = a;
		isPopulated = true;
	}

	public Animal getAnimal() {
		return animal;
	}

	public boolean isPopulated() {
		return isPopulated;
	}

	public void setEdge1(Tile t) {
		edge1 = t;
	}

	public void setEdge2(Tile t) {
		edge2 = t;
	}

	public void setEdge3(Tile t) {
		edge3 = t;
	}

	public void setEdge4(Tile t) {
		edge4 = t;
	}

	public void setEdge5(Tile t) {
		edge5 = t;
	}

	public void setEdge6(Tile t) {
		edge6 = t;
	}

	public Tile getEdge1() {
		return edge1;
	}

	public Tile getEdge2() {
		return edge2;
	}

	public Tile getEdge3() {
		return edge3;
	}

	public Tile getEdge4() {
		return edge4;
	}

	public Tile getEdge5() {
		return edge5;
	}

	public Tile getEdge6() {
		return edge6;
	}
	public String[] getSides(){
		return habitats;
	}
	public Tile getEdge(int s){
		Tile t = null;
		switch(s){
			case 0:
				t = getEdge6();
				break;
			case 1:
				t = getEdge5();
				break;
			case 2: 
				t = getEdge4();
				break;
			case 3: 
			 	t = getEdge3();
				break;
			case 4: 
				t = getEdge2();
				break;
			case 5: 
				t = getEdge1();
				break;
		}
		return t;
	}
}