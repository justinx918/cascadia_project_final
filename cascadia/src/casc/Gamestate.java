package casc;

import java.lang.Math;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import javax.swing.*;
import java.io.*;

public class Gamestate extends JPanel implements MouseListener {
	BufferedImage sp, instruct, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, st1, st2, st3;
	BufferedImage mp, bear, elk, fox, hawk, salmon;
	BufferedImage bearsc, hawksc, foxsc, salmonsc, elksc, psc;
	BufferedImage scPanel;
	boolean wipeActivated = false;
	boolean canRotate;
	ArrayList<Animal> animals = new ArrayList<>();
	boolean tileClicked;
	Animal[] availAnimals = new Animal[4];
	int page;
	ArrayList<Tile> allTiles = new ArrayList<>();
	Tile[] availTile = new Tile[4];
	boolean instClicked;
	Tile tileSelected;
	boolean tilePlaced, animalPlaced;
	int state = 1;
	int tileNum;
	TileGraph tilegraph;
	Player[] players = new Player[3];
	int currentPlayer = 0;
	Animal animalSelected;
	boolean animalClicked;
	int animalNum;
	int round = 1;
	int playerDisplay;
	boolean canWipe = true;
	boolean sw1, sw2, sw3, sw4 = false;

	public Gamestate() {
		tilePlaced = false;
		animalPlaced = false;
		animalSelected = null;
		String fileName = "/animal/tileinfo.txt";
		// "C:\Users\justi\OneDrive\Desktop\tileinfo"
		Scanner sc = null;
		tilegraph = new TileGraph();
		players[0] = new Player();
		players[1] = new Player();
		players[2] = new Player();

		try {
			InputStream file = getClass().getResourceAsStream(fileName);
			sc = new Scanner(file);
			scPanel = ImageIO.read(Gamestate.class.getResource("/animal/scorepanel.png"));
			psc = ImageIO.read(Gamestate.class.getResource("/StartScreen/solopanel.png"));
			st1 = ImageIO.read(Gamestate.class.getResource("/tiles/start1.png"));
			st2 = ImageIO.read(Gamestate.class.getResource("/tiles/start2.png"));
			st3 = ImageIO.read(Gamestate.class.getResource("/tiles/start3.png"));
			salmonsc = ImageIO.read(Gamestate.class.getResource("/animal/salmonsc.png"));
			bearsc = ImageIO.read(Gamestate.class.getResource("/animal/bearsc.png"));
			foxsc = ImageIO.read(Gamestate.class.getResource("/animal/foxsc.png"));
			elksc = ImageIO.read(Gamestate.class.getResource("/animal/elksc.png"));
			hawksc = ImageIO.read(Gamestate.class.getResource("/animal/hawksc.png"));
			instruct = ImageIO.read(Gamestate.class.getResource("/StartScreen/intscreen.png"));
			sp = ImageIO.read(Gamestate.class.getResource("/StartScreen/startpanel.png"));
			i1 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction1.png"));
			i2 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction2.png"));
			i3 = ImageIO.read(Gamestate.class.getResource("/StartScreen/intruction3.png"));
			i4 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction4.png"));
			i5 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction5.png"));
			i6 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction6.png"));
			i7 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction7.png"));
			i8 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction8.png"));
			i9 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction9.png"));
			i10 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction10.png"));
			i11 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction11.png"));
			i12 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instructions12.png"));
			i13 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction13.png"));
			i14 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction14.png"));
			i15 = ImageIO.read(Gamestate.class.getResource("/StartScreen/instruction15.png"));
			mp = ImageIO.read(Gamestate.class.getResource("/StartScreen/CascadiaGame.png"));
			bear = ImageIO.read(Gamestate.class.getResource("/animal/bear.png"));
			elk = ImageIO.read(Gamestate.class.getResource("/animal/elk.png"));
			fox = ImageIO.read(Gamestate.class.getResource("/animal/fox.png"));
			hawk = ImageIO.read(Gamestate.class.getResource("/animal/hawk.png"));
			salmon = ImageIO.read(Gamestate.class.getResource("/animal/salmon.png"));
		} catch (Exception e) {
			System.out.println("Error sp");
		}
		addMouseListener(this);
		Animal bearObj = new Animal("bear", bear);
		Animal elkObj = new Animal("elk", elk);
		Animal foxObj = new Animal("fox", fox);
		Animal salmonObj = new Animal("salmon", salmon);
		Animal hawkObj = new Animal("hawk", hawk);

		for (int i = 0; i < 20; i++) {

			animals.add(bearObj);
		animals.add(elkObj);
			animals.add(foxObj);

			animals.add(salmonObj);

			animals.add(hawkObj);

		}
		Collections.shuffle(animals);
		availAnimals[0] = animals.remove(0);
		availAnimals[1] = animals.remove(0);
		availAnimals[2] = animals.remove(0);
		availAnimals[3] = animals.remove(0);
		if (availAnimals[0].getType().equals(availAnimals[1].getType())
				&& availAnimals[1].getType().equals(availAnimals[2].getType())
				&& availAnimals[2].getType().equals(availAnimals[3].getType()))
			wipe();

		for (Animal ani : animals) {
			// System.out.print(ani.getType() + " ");
		}
		System.out.println();
		BufferedImage img = null;
		for (int i = 0; i < 84; i++) {
			Tile newtile = new Tile();
			try {
				img = ImageIO.read(Gamestate.class.getResource("/tiles/" + sc.nextLine()));
			} catch (Exception e) {
				System.out.println("errror tiles");
			}
			newtile.setImage(img);
			newtile.addLeft(sc.nextLine());
			newtile.addRight(sc.nextLine());
			newtile.addPotentialAnimal(sc.nextLine());
			String d = sc.nextLine();
			if (!d.equals("next")) {
				newtile.addPotentialAnimal(d);
				d = sc.nextLine();
				if (!d.equals("next")) {
					newtile.addPotentialAnimal(d);
					sc.nextLine();
				}
			}
			// System.out.println(newtile.toString()); //else {sc.nextLine();}
			allTiles.add(newtile);
		}
		Collections.shuffle(allTiles);
		// System.out.println(allTiles.size());
		availTile[0] = allTiles.remove(0);
		availTile[1] = allTiles.remove(0);
		availTile[2] = allTiles.remove(0);
		availTile[3] = allTiles.remove(0);
		startTiles();
	}

	public void turn(Graphics g) {

		if (availAnimals[0] != null)
			g.drawImage(availAnimals[0].getImage(), 154, 141, 58, 58, null);
		if (availAnimals[1] != null)
			g.drawImage(availAnimals[1].getImage(), 154, 289, 58, 58, null);
		if (availAnimals[2] != null)
			g.drawImage(availAnimals[2].getImage(), 154, 441, 58, 58, null);
		if (availAnimals[3] != null)
			g.drawImage(availAnimals[3].getImage(), 154, 590, 58, 58, null);
		if (availTile[0] != null)
			g.drawImage(availTile[0].getImage(), 28, 127, 85, 85, null);
		if (availTile[1] != null)
			g.drawImage(availTile[1].getImage(), 28, 276, 85, 85, null);
		if (availTile[2] != null)
			g.drawImage(availTile[2].getImage(), 28, 428, 85, 85, null);
		if (availTile[3] != null)
			g.drawImage(availTile[3].getImage(), 28, 576, 85, 85, null);

	}

	public void paint(Graphics g) {
		super.paint(g);
		if (state == 1) {
			if (instClicked == false) {
				g.drawImage(sp, 0, 0, 1440, 800, null);
			}
			if (instClicked == true) {
				g.drawImage(instruct, 0, 0, 1440, 800, null);
				switch (page) {
					case 1:
						g.drawImage(i1, 315, 0, 800, 800, null);
						break;
					case 2:
						g.drawImage(i2, 315, 0, 800, 800, null);
						break;
					case 3:
						g.drawImage(i3, 315, 0, 800, 800, null);
						break;
					case 4:
						g.drawImage(i4, 315, 0, 800, 800, null);
						break;

					case 5:
						g.drawImage(i5, 315, 0, 800, 800, null);
						break;
					case 6:
						g.drawImage(i6, 315, 0, 800, 800, null);
						break;
					case 7:
						g.drawImage(i7, 315, 0, 800, 800, null);
						break;
					case 8:
						g.drawImage(i8, 315, 0, 800, 800, null);
						break;
					case 9:
						g.drawImage(i9, 315, 0, 800, 800, null);
						break;
					case 10:
						g.drawImage(i10, 315, 0, 800, 800, null);
						break;
					case 11:
						g.drawImage(i11, 315, 0, 800, 800, null);
						break;
					case 12:
						g.drawImage(i12, 315, 0, 800, 800, null);
						break;
					case 13:
						g.drawImage(i13, 315, 0, 800, 800, null);
						break;
					case 14:
						g.drawImage(i14, 315, 0, 800, 800, null);
						break;
					case 15:
						g.drawImage(i15, 315, 0, 800, 800, null);
						break;

				}
			}
		}
		if (state == 2) {
			g.drawImage(mp, 0, 0, 1440, 800, null);
			// g.drawImage(st2, 0, 0, 100, 100, null);

			Font font = new Font("SansSerif", 1, 30);
			g.setFont(font);
			g.drawString(currentPlayer + 1 + "", 158, 35);
			g.drawString(players[currentPlayer].getTokens() + "", 158, 85);
			g.drawString("round:" + round + "", 100, 100);

			switch (currentPlayer) {
				case 0:
					g.drawImage(st2, 664, 355, 123, 123, null);
					break;
				case 1:
					g.drawImage(st1, 664, 355, 123, 123, null);
					break;
				case 2:
					g.drawImage(st3, 664, 355, 123, 123, null);
					break;
			}
			g.drawImage(bearsc, 1265, 78, 100, 115, null);
			g.drawImage(salmonsc, 1265, 190, 100, 115, null);
			g.drawImage(elksc, 1265, 305, 100, 115, null);
			g.drawImage(hawksc, 1265, 423, 100, 115, null);
			g.drawImage(foxsc, 1265, 538, 100, 115, null);

			if (tileClicked) {
			}
			// g.drawImage(tileSelected.getImage(), tileSelected.getX(),
			// tileSelected.getY(), 80, 80, null);

			int p = 278;
			int l = 247;
			for (Tile tt : players[currentPlayer].getTiles()) {
				g.drawImage(tt.getImage(), tt.getX(), tt.getY(), 68, 68, null);
				if (tt.getAnimal() != null)
					g.drawImage(tt.getAnimal().getImage(), tt.getX() + 17, tt.getY() + 17, 35, 35, null);
				// System.out.println(tt.toString());
			}

			turn(g);

		}
		if (state == 3) {
			Font font = new Font("SansSerif", 1, 30);
			g.setFont(font);
			g.drawImage(scPanel, 0, 0, 1440, 800, null);
			players[0].getBear();
			players[0].getElk();
			players[0].getSalmon();
			players[0].getHawk();
			players[0].getFox();
			players[0].calcBiome();

			players[1].getBear();
			players[1].getElk();
			players[1].getSalmon();
			players[1].getHawk();
			players[1].getFox();
			players[1].calcBiome();

			players[2].getBear();
			players[2].getElk();
			players[2].getSalmon();
			players[2].getHawk();
			players[2].getFox();
			players[2].calcBiome();

			g.drawString("" + players[0].returnHawk(), 1105, 305);
			g.drawString("" + players[1].returnHawk(), 1155, 305);
			g.drawString("" + players[2].returnHawk(), 1205, 305);
			g.drawString("" + players[0].returnFox(), 1105, 347);
			g.drawString("" + players[1].returnFox(), 1155, 347);
			g.drawString("" + players[2].returnFox(), 1205, 347);

			g.drawString("" + players[0].returnBear(), 1105, 188);
			g.drawString("" + players[1].returnBear(), 1155, 188);
			g.drawString("" + players[2].returnBear(), 1205, 188);
			g.drawString("" + players[0].returnElk(), 1105, 220);
			g.drawString("" + players[1].returnElk(), 1155, 220);
			g.drawString("" + players[2].returnElk(), 1205, 220);
			g.drawString("" + players[0].returnSalmon(), 1105, 260);
			g.drawString("" + players[1].returnSalmon(), 1155, 260);
			g.drawString("" + players[2].returnSalmon(), 1205, 260);
			g.drawString("" + players[0].getAnimalTotal(), 1105, 387);
			g.drawString("" + players[1].getAnimalTotal(), 1155, 387);
			g.drawString("" + players[2].getAnimalTotal(), 1205, 387);

			Font fontB = new Font("SansSerif", 1, 24);
			g.setFont(fontB);
			g.drawString("" + players[0].getMountain(), 1102, 438);
			g.drawString("" + players[0].getForest(), 1102, 478);
			g.drawString("" + players[0].getPrairie(), 1102, 520);
			g.drawString("" + players[0].getWetland(), 1102, 562);
			g.drawString("" + players[0].getRiver(), 1102, 604);

			g.drawString("" + players[1].getMountain(), 1152, 438);
			g.drawString("" + players[1].getForest(), 1152, 478);
			g.drawString("" + players[1].getPrairie(), 1152, 520);
			g.drawString("" + players[1].getWetland(), 1152, 562);
			g.drawString("" + players[1].getRiver(), 1152, 604);

			g.drawString("" + players[2].getMountain(), 1202, 438);
			g.drawString("" + players[2].getForest(), 1202, 478);
			g.drawString("" + players[2].getPrairie(), 1202, 520);
			g.drawString("" + players[2].getWetland(), 1202, 562);
			g.drawString("" + players[2].getRiver(), 1202, 604);
			int p1b, p2b, p3b ;
			p1b = 0; p2b = 0; p3b = 0;
			int[] bonus = rankVariables(players[0].getMountain(), players[1].getMountain(), players[2].getMountain());
			System.out.println(bonus[0]);
			System.out.println(bonus[1]);
			System.out.println(bonus[2]);
			g.drawString("" +bonus[0], 1125, 452);
			g.drawString("" +bonus[1], 1175, 452);
			g.drawString("" +bonus[2], 1225, 452);

			p1b+= bonus[0];
			p2b+=bonus[1];
			p3b += bonus[2];

			bonus = rankVariables(players[0].getForest(), players[1].getForest(), players[2].getForest());
			System.out.println(bonus[0]);
			System.out.println(bonus[1]);
			System.out.println(bonus[2]);
			g.drawString("" +bonus[0], 1125, 492);
			g.drawString("" +bonus[1], 1175, 492);
			g.drawString("" +bonus[2], 1225, 492);
			p1b+= bonus[0];
			p2b+=bonus[1];
			p3b += bonus[2];


			bonus = rankVariables(players[0].getPrairie(), players[1].getPrairie(), players[2].getPrairie());
			System.out.println(bonus[0]);
			System.out.println(bonus[1]);
			System.out.println(bonus[2]);
			g.drawString("" +bonus[0], 1125, 534);
			g.drawString("" +bonus[1], 1175, 534);
			g.drawString("" +bonus[2], 1225, 534);
			p1b+= bonus[0];
			p2b+=bonus[1];
			p3b += bonus[2];

			bonus = rankVariables(players[0].getWetland(), players[1].getWetland(), players[2].getWetland());
			System.out.println(bonus[0]);
			System.out.println(bonus[1]);
			System.out.println(bonus[2]);
			g.drawString("" +bonus[0], 1125, 576);
			g.drawString("" +bonus[1], 1175, 576);
			g.drawString("" +bonus[2], 1225, 576);
				p1b+= bonus[0];
			p2b+=bonus[1];
			p3b += bonus[2];

			bonus = rankVariables(players[0].getRiver(), players[1].getRiver(), players[2].getRiver());
			System.out.println(bonus[0]);
			System.out.println(bonus[1]);
			System.out.println(bonus[2]);
			g.drawString("" +bonus[0], 1125, 618);
			g.drawString("" +bonus[1], 1175, 618);
			g.drawString("" +bonus[2], 1225, 618);
			p1b+= bonus[0];
			p2b+=bonus[1];
			p3b += bonus[2];
			players[0].addScore(p1b);
			players[1].addScore(p2b);
			players[2].addScore(p3b);

			g.drawString(players[0].calcTotalB() + "", 1105, 645);
			g.drawString(players[1].calcTotalB() + "", 1155, 645);
			g.drawString(players[2].calcTotalB() + "", 1205, 645);
			
			g.drawString(players[0].getTokens() + "", 1105, 700);
			g.drawString(players[1].getTokens() + "", 1155, 700);
			g.drawString(players[2].getTokens() + "", 1205, 700);

			g.drawString(players[0].calcTotal()+"", 1105, 755);
			g.drawString(players[1].calcTotal() + "", 1155, 755);
			g.drawString(players[2].calcTotal() + "", 1205, 755);
			Font fontGG = new Font("SansSerif", 1, 100);
			g.setFont(fontGG);
			g.drawString(calcWinner(players[0].calcTotal(), players[1].calcTotal(), players[2].calcTotal()), 250, 500);
		}
		if (state == 4) {

			// make go bak butotn
			Font font = new Font("SansSerif", 1, 30);
			g.setFont(font);
			g.drawImage(psc, 0, 0, 1440, 800, null);

			// display tilegraph according to playerDisplay
			switch (playerDisplay) {
				case 0:
					g.drawImage(st2, 664, 355, 123, 123, null);
					g.drawString(0 + 1 + "", 158, 35);
					g.drawString(players[0].getTokens() + "", 158, 85);
					break;
				case 1:
					g.drawImage(st1, 664, 355, 123, 123, null);
					g.drawString(1 + 1 + "", 158, 35);
					g.drawString(players[1].getTokens() + "", 158, 85);
					break;
				case 2:
					g.drawImage(st3, 664, 355, 123, 123, null);
					g.drawString(2 + 1 + "", 158, 35);
					g.drawString(players[2].getTokens() + "", 158, 85);
					break;
			}
			for (Tile tt : players[playerDisplay].getTiles()) {
				g.drawImage(tt.getImage(), tt.getX(), tt.getY(), 68, 68, null);
				if (tt.getAnimal() != null)
					g.drawImage(tt.getAnimal().getImage(), tt.getX() + 17, tt.getY() + 17, 35, 35, null);
				// System.out.println(tt.toString());
			}
		}
		// g.drawImage(sp, 0, 0, 1440, 800,null);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + " " + y);
		if (state == 1) {
			if (instClicked == true) {
				if (Math.sqrt(((x - 1367) * (x - 1367)) + ((y - 736) * (y - 736))) <= 35)
					instClicked = false;
				else if (Math.sqrt(((x - 265) * (x - 265)) + ((y - 400) * (y - 400))) <= 35 && page > 1)
					page--;
				else if (Math.sqrt(((x - 1175) * (x - 1175)) + ((y - 400) * (y - 400))) <= 35) {
					if (page < 15)
						page++;
				}

			} else {
				if (Math.sqrt(((x - 1367) * (x - 1367)) + ((y - 736) * (y - 736))) <= 43) {
					instClicked = true;
					page = 1;
				} else if (x < 804 && x > 634 && y > 527 && y < 696) {
					state = 2;
				}

			}
			// System.out.println(state);

			repaint();
		}
		if (state == 2) {
			if (tileClicked || animalClicked) {
				// drawTileTest(x, y);

				boolean rowOdd = false;
				int row = 0;
				boolean rowClicked = true;
				int collum = 0;
				// split into rows, then collums
				if (y > 120 && y < 662 && x > 250 && x < 1140) {

					if (y > 120 && y < 156) {// row 0
						row = 0;
						rowOdd = false;
						// System.out.println(row);
					} else if (y > 176 && y < 206) {// row 1
						row = 1;
						rowOdd = true;
						// System.out.println(row);
					} else if (y > 222 && y < 256) {// row 2
						row = 2;
						rowOdd = false;
						// System.out.println(row);
					} else if (y > 273 && y < 306) {// row 3
						row = 3;
						rowOdd = true;
						// System.out.println(row);
					} else if (y > 324 && y < 358) {// row 4
						row = 4;
						rowOdd = false;
						// System.out.println(row);
					} else if (y > 376 && y < 406) {// row 5
						row = 5;
						rowOdd = true;
						// System.out.println(row);
					} else if (y > 426 && y < 459) {// row 6
						row = 6;
						rowOdd = false;
						// System.out.println(row);
					} else if (y > 476 && y < 509) {// row 7
						row = 7;
						rowOdd = true;
						// System.out.println(row);
					} else if (y > 528 && y < 562) {
						row = 8;
						rowOdd = false;
						// System.out.println(row);
					} else if (y > 577 && y < 611) {
						row = 9;
						rowOdd = true;
						// System.out.println(row);
					} else if (y > 629 && y < 662) {
						row = 10;
						rowOdd = false;
						// System.out.println(row);
					} else {
						rowClicked = false;
					}
					int offset = 0;
					if (rowOdd)
						offset = 30;
					if (x > 250 + offset && x < 310 + offset)
						collum = 1;
					else if (x > 310 + offset && x < 360 + offset)
						collum = 2;
					else if (x > 360 + offset && x < 420 + offset)
						collum = 3;
					else if (x > 420 + offset && x < 480 + offset)
						collum = 4;
					else if (x > 480 + offset && x < 540 + offset)
						collum = 5;
					else if (x > 540 + offset && x < 600 + offset)
						collum = 6;
					else if (x > 600 + offset && x < 660 + offset)
						collum = 7;
					else if (x > 660 + offset && x < 720 + offset)
						collum = 8;
					else if (x > 720 + offset && x < 780 + offset)
						collum = 9;
					else if (x > 780 + offset && x < 840 + offset)
						collum = 10;
					else if (x > 840 + offset && x < 900 + offset)
						collum = 11;
					else if (x > 900 + offset && x < 960 + offset)
						collum = 12;
					else if (x > 960 + offset && x < 1020 + offset)
						collum = 13;
					else if (x > 1020 + offset && x < 1080 + offset)
						collum = 14;
					else if (x > 1080 + offset && x < 1140 + offset)
						collum = 15;
					// System.out.println(collum);
					if (rowClicked == true && tileSelected != null && !tilePlaced) {
						if (!checkPair()) {
							if (players[currentPlayer].getTokens() >= 1) {
								if (players[currentPlayer].addTile(row, collum, tileSelected)) {
									players[currentPlayer].loseToken();
									repaint();
									tileSelected = null;
									availTile[tileNum] = null;
									tilePlaced = true;
								} else {
								} // System.out.println("could not be added");
							} else {
							} // System.out.println("not enuf token");
						} else {
							if (players[currentPlayer].addTile(row, collum, tileSelected)) {
								repaint();
								tileSelected = null;
								availTile[tileNum] = null;
								tilePlaced = true;
							}
						}
						System.out.println(row + " " + collum);
						canRotate = true;
						canWipe = false;
					} else if (animalSelected != null && !animalPlaced) {
						if (players[currentPlayer].findTile(row, collum) != null) {
							if (players[currentPlayer].findTile(row, collum).isPopulated() == false) {
								if (!checkPair()) {
									if (players[currentPlayer].getTokens() >= 1) {
										if (players[currentPlayer].addAnimal(row, collum, animalSelected) == true) {
											players[currentPlayer].loseToken();
											animalSelected = null;
											// System.out.println("animal added g");
											availAnimals[animalNum] = null;
											animalPlaced = true;
											repaint();
										} else {
											// System.out.println("animal cant be added");
										}
									} else {
									} // System.out.println("not enuf token");
								} else {
									if (players[currentPlayer].addAnimal(row, collum, animalSelected) == true) {
										animalSelected = null;
										// System.out.println("animal added g");
										availAnimals[animalNum] = null;
										animalPlaced = true;
										repaint();
									}
								}
							}
						}
						canWipe = false;
					}
					// tileClicked = false;
				}
			}
			if (x > 1204 && x < 1275 && y > 12 && y < 52) {
				playerDisplay = 0;
				state = 4;
				repaint();
			}
			if (x > 1276 && x < 1352 && y > 12 && y < 52) {
				playerDisplay = 1;
				state = 4;
				repaint();
			}
			if (x > 1356 && x < 1427 && y > 12 & y < 52) {
				playerDisplay = 2;
				state = 4;
				repaint();
			}
			if (x > 1203 && x < 1273 && y > 697 && y < 738) {// rotate
				if (canRotate) {
					int l = players[currentPlayer].getTiles().size();
					players[currentPlayer].getTiles().get(l - 1).rotate();
					repaint();
				}

			}
			if (x > 1273 && x < 1354 && y > 696 && y < 737) {
				canRotate = false;
			}
			if (x > 1354 && x < 1428 && y > 696 && y < 737) {
				for (Tile t : players[currentPlayer].getTiles()) {
					// System.out.println(t.toString());
				}
				// somethign smomthing end turn
				if (currentPlayer == 2)
					round++;
				if (round == 21)
					state = 3;
				currentPlayer++;
				tilePlaced = false;
				animalPlaced = false;
				repopulate();
				repaint();
				if (currentPlayer > 2)
					currentPlayer = 0;
				canRotate = false;
				canWipe = true;
				wipeActivated = false;
			}
			if (Math.sqrt(((x - 183) * (x - 183)) + ((y - 169) * (y - 169))) <= 29 && !animalPlaced) {

				animalClicked = true;
				// System.out.println(availAnimals[0].getType());
				animalNum = 0;
				animalSelected = availAnimals[animalNum];
			} else if (Math.sqrt(((x - 183) * (x - 183)) + ((y - 318) * (y - 318))) <= 29 && !animalPlaced) {
				// System.out.println(checkPair() + "pair");
				animalClicked = true;
				// System.out.println(availAnimals[1].getType());
				animalNum = 1;
				animalSelected = availAnimals[animalNum];
			} else if (Math.sqrt(((x - 183) * (x - 183)) + ((y - 469) * (y - 469))) <= 29 && !animalPlaced) {
				// System.out.println(checkPair() + "pair");

				animalClicked = true;
				// System.out.println(availAnimals[2].getType());
				animalNum = 2;
				animalSelected = availAnimals[animalNum];

			} else if (Math.sqrt(((x - 183) * (x - 183)) + ((y - 619) * (y - 619))) <= 29 && !animalPlaced) {
				animalClicked = true;
				// System.out.println(availAnimals[3].getType());
				animalNum = 3;
				animalSelected = availAnimals[animalNum];

			} else if (x > 132 && x < 231 && y < 731 && y > 690) {// wipe
				if (canWipe) {
					if (animalSelected != null) {
						System.out.println(wipeActivated);
						if (players[currentPlayer].getTokens() >= 1 || wipeActivated) {
							System.out.println("canwipe " + canWipe);

							availAnimals[animalNum] = animals.remove(0);
							animalSelected = null;
							while (availAnimals[0].getType().equals(availAnimals[1].getType())
									&& availAnimals[1].getType().equals(availAnimals[2].getType())
									&& availAnimals[2].getType().equals(availAnimals[3].getType()))
								wipe();
							if (!wipeActivated)
								players[currentPlayer].loseToken();
							wipeActivated = true;
							repaint();
						}
					} else {

						wipe();
					}
				}

			} else if (Math.sqrt(((x - 71) * (x - 71)) + ((y - 169) * (y - 169))) <= 107 && !tilePlaced) {
				// System.out.println(availTile[0].toString());//tiles
				tileClicked = true;
				tileNum = 0;
				tileSelected = availTile[0];

			} else if (Math.sqrt(((x - 71) * (x - 71)) + ((y - 319) * (y - 319))) <= 107 && !tilePlaced) {
				// System.out.println(availTile[1].toString());
				tileClicked = true;
				tileNum = 1;
				tileSelected = availTile[1];

			} else if (Math.sqrt(((x - 71) * (x - 71)) + ((y - 469) * (y - 469))) <= 107 && !tilePlaced) {

				// System.out.println(availTile[2].toString());
				tileClicked = true;
				tileSelected = availTile[2];
				tileNum = 2;

			} else if (Math.sqrt(((x - 71) * (x - 71)) + ((y - 619) * (y - 619))) <= 107 && !tilePlaced) {
				// System.out.println(availTile[3].toString());
				tileClicked = true;
				tileSelected = availTile[3];
				tileNum = 3;

			}

		} else if (state == 3) {
			if (x > 1093 && x < 1141 && y > 105 && y < 148) {
				// System.out.println("player 1");
				playerDisplay = 0;
				state = 4;
				repaint();
			} else if (x > 1141 && x < 1189 && y > 105 && y < 148) {
				// System.out.println("player 2");
				playerDisplay = 1;
				state = 4;
				repaint();
			} else if (x > 1189 && x < 1239 && y > 105 && y < 148) {
				// System.out.println("player 3");
				playerDisplay = 2;
				state = 4;
				repaint();
			}
		} else if (state == 4) {
			if (x > 1203 && x < 1426 && y < 736 && y > 695) {
				if (round == 21)
					state = 3;
				else
					state = 2;
				repaint();
			}
		}
		// repaint();

		// System.out.println(tileClicked);
	}

	public void drawTileTest(int x, int y) {
		tileSelected.setXY(x - 42, y - 42);
		// add tile to player arraylist
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void wipe() {
		int dupes = 0;
		ArrayList<String> anim = new ArrayList<>();
		for (Animal each : availAnimals) {
			anim.add(each.getType());
		}
		String dupe = "";
		ArrayList<Integer> lol = new ArrayList<>();
		lol.add(Collections.frequency(anim, "bear"));
		if (Collections.frequency(anim, "bear") >= 3)
			dupe = "bear";
		lol.add(Collections.frequency(anim, "elk"));
		if (Collections.frequency(anim, "elk") >= 3)
			dupe = "elk";
		lol.add(Collections.frequency(anim, "hawk"));
		if (Collections.frequency(anim, "hawk") >= 3)
			dupe = "hawk";
		lol.add(Collections.frequency(anim, "salmon"));
		if (Collections.frequency(anim, "salmon") >= 3)
			dupe = "salmon";
		lol.add(Collections.frequency(anim, "fox"));
		if (Collections.frequency(anim, "fox") >= 3)
			dupe = "fox";

		Collections.sort(lol, Collections.reverseOrder());
		if (lol.get(0) >= 3) {
			System.out.println("3 dupe");
			canWipe = false;
			for (int e = 0; e < 4; e++) {
				if (availAnimals[e].getType().equals(dupe)) {
					animals.add(availAnimals[e]);
					availAnimals[e] = animals.remove(0);
				}
			}

		}
		Collections.shuffle(animals);
		repaint();

	}

	public void repopulate() {
		for (int i = 0; i < 4; i++) {
			if (availTile[i] == null) {
				availTile[i] = allTiles.remove(0);
			}
			if (availAnimals[i] == null) {
				availAnimals[i] = animals.remove(0);
			}

		}
		while (availAnimals[0].getType().equals(availAnimals[1].getType())
				&& availAnimals[1].getType().equals(availAnimals[2].getType())
				&& availAnimals[2].getType().equals(availAnimals[3].getType()))
			wipe();

	}

	private void startTiles() {

		Tile ttt = new Tile("river", "river");
		ttt.addPotentialAnimal("salmon");
		ttt.setRC(5, 8);
		players[0].addStarterTile(5, 8, ttt);
		Tile ttt2 = new Tile("prairie", "forest");
		String[] asd = ttt2.getSides();
		for (String s : asd)
			System.out.println(s);

		ttt2.rotate();
		asd = ttt2.getSides();
		for (String s : asd)
			System.out.println(s);
		ttt2.addPotentialAnimal("salmon");
		ttt2.addPotentialAnimal("elk");
		ttt2.addPotentialAnimal("bear");
		ttt2.setRC(6, 8);
		players[0].addStarterTile(6, 8, ttt2);// 8, 9
		Tile ttt3 = new Tile("mountain", "wetland");
		ttt3.rotate();
		ttt3.rotate();
		ttt3.addPotentialAnimal("fox");
		ttt3.addPotentialAnimal("hawk");
		ttt3.setRC(6, 9);
		players[0].addStarterTile(6, 9, ttt3);// 8, 9

		Tile ttt4 = new Tile("forest", "forest");
		ttt4.addPotentialAnimal("elk");
		ttt4.setRC(5, 8);
		players[1].addStarterTile(5, 8, ttt4);
		Tile ttt5 = new Tile("mountain", "river");
		ttt5.rotate();
		ttt5.addPotentialAnimal("hawk");
		ttt5.addPotentialAnimal("elk");
		ttt5.addPotentialAnimal("bear");
		ttt5.setRC(6, 8);
		players[1].addStarterTile(6, 8, ttt5);// 8, 9
		Tile ttt6 = new Tile("wetland", "prairie");
		ttt6.rotate();
		ttt6.rotate();

		ttt6.addPotentialAnimal("fox");
		ttt6.addPotentialAnimal("salmon");
		ttt6.setRC(6, 9);
		players[1].addStarterTile(6, 9, ttt6);// 8, 9

		Tile ttt7 = new Tile("wetland", "wetland");
		ttt7.addPotentialAnimal("hawk");
		ttt7.setRC(5, 8);
		players[2].addStarterTile(5, 8, ttt7);
		Tile ttt8 = new Tile("river", "forest");
		ttt8.rotate();
		ttt8.addPotentialAnimal("hawk");
		ttt8.addPotentialAnimal("elk");
		ttt8.addPotentialAnimal("salmon");
		ttt8.setRC(6, 8);
		players[2].addStarterTile(6, 8, ttt8);// 8, 9
		Tile ttt9 = new Tile("prairie", "mountain");
		ttt9.rotate();
		ttt9.rotate();
		ttt9.addPotentialAnimal("fox");
		ttt9.addPotentialAnimal("bear");
		ttt9.setRC(6, 9);
		players[2].addStarterTile(6, 9, ttt9);// 8, 9

	}

	public boolean checkPair() {
		if (tilePlaced) {
			if (tileNum == animalNum) {
				return true;
			} else
				return false;
		} else if (animalPlaced) {
			if (tileNum == animalNum) {
				return true;
			} else
				return false;
		}
		return true;
	}

	public int[] rankVariables(int p1, int p2, int p3) {
		// Create a list to store the variables
		List<Integer> variables = Arrays.asList(p1, p2, p3);

		// Create a list to store the variables
		// Sort the variables in descending order
		Collections.sort(variables, Collections.reverseOrder());

		// Assign ranks
		List<Integer> ranks = new ArrayList<>();
		int rank = 1;
		for (int i = 0; i < variables.size(); i++) {
			int currentVar = variables.get(i);
			if (i > 0 && currentVar != variables.get(i - 1)) {
				rank = i + 1;
			}
			ranks.add(rank);
		}

		// Order ranks by the original variables p1, p2, and p3
		List<Integer> orderedRanks = new ArrayList<>();
		orderedRanks.add(ranks.get(variables.indexOf(p1)));
		orderedRanks.add(ranks.get(variables.indexOf(p2)));
		orderedRanks.add(ranks.get(variables.indexOf(p3)));
		int[] rizz = { 0, 0, 0 };
		// Collections.frequency(anim, "salmon")
		String str = "" + orderedRanks.get(0) + orderedRanks.get(1) + orderedRanks.get(2);
		if (Collections.frequency(orderedRanks, 1) > 1) {
			if (Collections.frequency(orderedRanks, 1) > 2) {
				rizz[0] = 1;
				rizz[1] = 1;
				rizz[2] = 1;
			} 
			else {
				rizz[str.indexOf("1")] = 2;
				rizz[str.indexOf("1", str.indexOf("1")+1)] = 2;
			}

		} else if (Collections.frequency(orderedRanks, 2) > 1) {
			rizz[str.indexOf("1")] = 3;
		} else {
			rizz[str.indexOf("1")] = 3;
			rizz[str.indexOf("2")] = 1;

		}

		return rizz;

	}

	
	/*public String calculateWinner(int[] x){
		//im done wit this shit
		String nohelp= "";
		if(x[0] > x[1] && x[0] > x[2]) nohelp = "1";
		else if(x[1] > x[0] && x[1] > x[2]) nohelp = "2";
		else if(x[2] > x[1] && x[2] > x[0]) nohelp = "3";
		else if
		return "negro";

	} */
	public String calcWinner(int p1, int p2, int p3) {
		// Create a list to store the variables
		List<Integer> variables = Arrays.asList(p1, p2, p3);

		// Create a list to store the variables
		// Sort the variables in descending order
		Collections.sort(variables, Collections.reverseOrder());

		// Assign ranks
		List<Integer> ranks = new ArrayList<>();
		int rank = 1;
		for (int i = 0; i < variables.size(); i++) {
			int currentVar = variables.get(i);
			if (i > 0 && currentVar != variables.get(i - 1)) {
				rank = i + 1;
			}
			ranks.add(rank);
		}

		// Order ranks by the original variables p1, p2, and p3
		List<Integer> orderedRanks = new ArrayList<>();
		orderedRanks.add(ranks.get(variables.indexOf(p1)));
		orderedRanks.add(ranks.get(variables.indexOf(p2)));
		orderedRanks.add(ranks.get(variables.indexOf(p3)));
		int[] rizz = { 0, 0, 0 };
		// Collections.frequency(anim, "salmon")
		String str = "" + orderedRanks.get(0) + orderedRanks.get(1) + orderedRanks.get(2);
		String winner = "";
		if (Collections.frequency(orderedRanks, 1) > 1) {
			if (Collections.frequency(orderedRanks, 1) > 2) {
			winner = "1, 2, and 3!";
			} 
			else {
				winner += "" + (str.indexOf("1") +1) + " and " + (str.indexOf("1", str.indexOf("1")+1) + 1);
				
			}

		} 
		else {
			winner+="        " + (str.indexOf("1") +1) + "";

		}

		return winner;

	}
}
