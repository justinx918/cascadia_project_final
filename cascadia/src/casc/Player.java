package casc;

import java.util.*;

public class Player {
	private TileGraph tilegraph;
	private int token;
	private int score;
	private int largestBiome[];
	private int bear, elk, salmon, fox, hawk;
	private int forest, prairie, mountain, river, wetland;
	private int bonus;
	public Player() {
		tilegraph = new TileGraph();
	}

	public boolean addTile(int r, int s, Tile t) {
		return tilegraph.addTile(r, s, t);
	}

	public ArrayList<Tile> getTiles() {
		return tilegraph.getTiles();
	}

	public boolean addAnimal(int r, int c, Animal a) {
		return tilegraph.addAnimal(r, c, a);
	}

	public void addStarterTile(int r, int s, Tile t) {
		tilegraph.addStarterTile(r, s, t);
	}

	public int getTokens() {
		return tilegraph.getTokens();
	}

	public void loseToken() {
		tilegraph.loseToken();
	}

	public void getHawk() {
		hawk = tilegraph.getHawk();
	}

	public Tile findTile(int r, int s) {
		return tilegraph.findTile(r, s);
	}

	public void getFox() {
		fox = tilegraph.getFox();
	}

	public void getBear() {
		bear = tilegraph.getBear();
	}

	public void getElk() {
		elk = tilegraph.getElk();
	}

	public void getSalmon() {
		salmon = tilegraph.getSalmon();
	}

	public int returnHawk() {
		return hawk;
	}

	public int returnFox() {
		return fox;
	}

	public int returnBear() {
		return bear;
	}

	public int returnElk() {
		return elk;
	}

	public int returnSalmon() {
		return salmon;
	}

	public int getAnimalTotal() {
		return hawk + fox + bear + elk + salmon;
	}
	public int getLargestBiome(String b){
		return tilegraph.getLargestBiome(b);
	}
	public void calcBiome(){
		forest = getLargestBiome("forest");
		wetland = getLargestBiome("wetland");
		river = getLargestBiome("river");
		prairie = getLargestBiome("prairie");
		mountain = getLargestBiome("mountain");
	}
	public int getMountain(){
		return mountain;
	}
	public int getForest(){
		return forest;

	}
	public void addScore(int i)
{
	score+=i;
	bonus =i;
}	
	public int getScore(){
		return score;
	}
	public int calcTotal(){
		return  hawk + fox + bear + elk + salmon + wetland + river + prairie + mountain + forest + tilegraph.getTokens() + bonus;
	}
	public int calcTotalB(){
		return wetland + river + prairie + mountain + forest + bonus;
	}
	public int getWetland(){return wetland;}
	public int getRiver(){return river;}
	public int getPrairie(){
		return prairie;
	}

}
