package casc;

import java.util.ArrayList;
import java.util.Collections;

public class TileGraph {
	private ArrayList<Tile> tiles;
	private int row;
	private int tokens;
	private int slot;
	private int x[] = { 247, 278 };
	private int y[] = { 104, 155, 205, 256, 306, 358, 408, 459, 510, 561, 611 };
	private int[] LargestBiome;

	public TileGraph() {
		tiles = new ArrayList<Tile>();
	}

	public boolean addTile(int r, int s, Tile t) {// make return boolean similar to animal, make sure is not occupied
													// etc
		if (r % 2 == 0) {
			if (findTile(r, s - 1) != null || findTile(r, s + 1) != null || findTile(r - 1, s) != null
					|| findTile(r - 1, s - 1) != null || findTile(r + 1, s) != null || findTile(r + 1, s - 1) != null) {
			} else
				return false;
		} else {
			if (findTile(r, s - 1) != null || findTile(r, s + 1) != null || findTile(r - 1, s) != null
					|| findTile(r - 1, s + 1) != null || findTile(r + 1, s) != null || findTile(r + 1, s + 1) != null) {
			} else
				return false;
		}
		boolean canAdd = false;
		if (findTile(r, s) == null) {
			row = r;
			slot = s;
			if (r % 2 == 0)
				t.setXY(247 + ((s - 1) * 59) + (s / 5), y[r]);
			else
				t.setXY(277 + ((s - 1) * 59) + (s / 5), y[r]);
			t.setRC(r, s);
			tiles.add(t);
			setEdges();
			return true;

		}
		return false;

	}

	public void addStarterTile(int r, int s, Tile t) {
		row = r;
		slot = s;
		if (r % 2 == 0)
			t.setXY(247 + ((s - 1) * 59) + (s / 5), y[r]);
		else
			t.setXY(277 + ((s - 1) * 59) + (s / 5), y[r]);
		t.setRC(r, s);
		tiles.add(t);
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public boolean addAnimal(int r, int s, Animal a) {
		Tile tilec = null;
		boolean canAdd = false;
		for (Tile tile : tiles) {
			if (tile.getRow() == r && tile.getColl() == s)
				tilec = tile;
		}
		for (int i = 0; i < tilec.getPotentialAnimals().size(); i++) {
			if (tilec.getPotentialAnimals().get(i).equals(a.getType())) {
				canAdd = true;
				if (tilec.isKeyStone())
					tokens++;
				tilec.addAnimal(a);
			}
		}
		return canAdd;
	}

	public Tile findTile(int r, int s) {
		for (Tile t : tiles) {
			if (t.getRow() == r && t.getColl() == s)
				return t;
		}
		return null;
	}

	public int getTokens() {
		return tokens;
	}

	public void loseToken() {
		tokens--;
	}

	public void setEdges() {
		for (Tile t : tiles) {
			int r = t.getRow();
			int c = t.getColl();
			if (r % 2 == 0) {
				if (findTile(r, c - 1) != null) {
					t.setEdge5(findTile(r, c - 1));
				}
				if (findTile(r, c + 1) != null) {
					t.setEdge2(findTile(r, c + 1));
				}
				if (findTile(r - 1, c) != null) {
					t.setEdge1(findTile(r - 1, c));
				}
				if (findTile(r - 1, c - 1) != null) {
					t.setEdge6(findTile(r - 1, c - 1));
				}
				if (findTile(r + 1, c) != null) {
					t.setEdge3(findTile(r + 1, c));
				}
				if (findTile(r + 1, c - 1) != null) {
					t.setEdge4(findTile(r + 1, c - 1));
				}
			} else {
				if (findTile(r - 1, c + 1) != null) {
					t.setEdge1(findTile(r - 1, c + 1));
				}
				if (findTile(r, c + 1) != null) {
					t.setEdge2(findTile(r, c + 1));
				}
				if (findTile(r + 1, c + 1) != null) {
					t.setEdge3(findTile(r + 1, c + 1));
				}
				if (findTile(r + 1, c) != null) {
					t.setEdge4(findTile(r + 1, c));
				}
				if (findTile(r, c - 1) != null) {
					t.setEdge5(findTile(r, c - 1));
				}
				if (findTile(r - 1, c) != null) {
					t.setEdge6(findTile(r - 1, c));
				}
			}
		}
	}

	public int getHawk() {
		int score = 0;
		int cnt = 0;
		for (Tile t : tiles) {
			if (t.isPopulated()) {
				if (t.getAnimal().getType().equals("hawk")) {
					if (!t.getIsChecked()) {
						int r = t.getRow();
						int c = t.getColl();
						boolean isAlone = true;
						if (t.getEdge1() != null) {
							if (t.getEdge1().isPopulated()) {
								if (t.getEdge1().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (t.getEdge2() != null) {
							if (t.getEdge2().isPopulated()) {
								if (t.getEdge2().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (t.getEdge3() != null) {
							if (t.getEdge3().isPopulated()) {
								if (t.getEdge3().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (t.getEdge4() != null) {
							if (t.getEdge4().isPopulated()) {
								if (t.getEdge4().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (t.getEdge5() != null) {
							if (t.getEdge5().isPopulated()) {
								if (t.getEdge5().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (t.getEdge6() != null) {
							if (t.getEdge6().isPopulated()) {
								if (t.getEdge6().getAnimal().getType().equals("hawk"))
									isAlone = false;
							}
						}
						if (isAlone)
							cnt++;
						t.checked(true);
					} else {
					}

				}
			}
		}
		if (cnt == 0)
			return 0;
		for (Tile t : tiles)
			t.checked(false);
		if (cnt <= 5)
			score = cnt * 3 - 1;
		else if (cnt > 5 && cnt < 8)
			score = 14 + (cnt - 5) * 4;
		else
			score = 28;
		return score;
	}

	public int getFox() {
		int score = 0;
		int cnt = 0;
		for (Tile t : tiles) {
			if (t.isPopulated()) {
				if (t.getAnimal().getType().equals("fox")) {
					if (!t.getIsChecked()) {
						int r = t.getRow();
						int c = t.getColl();
						int fox = 0;
						int bear = 0;
						int salmon = 0;
						int elk = 0;
						int hawk = 0;
						if (t.getEdge1() != null) {
							if (t.getEdge1().isPopulated()) {
								if (t.getEdge1().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge1().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge1().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge1().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge1().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (t.getEdge2() != null) {
							if (t.getEdge2().isPopulated()) {
								if (t.getEdge2().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge2().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge2().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge2().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge2().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (t.getEdge3() != null) {
							if (t.getEdge3().isPopulated()) {
								if (t.getEdge3().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge3().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge3().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge3().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge3().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (t.getEdge4() != null) {
							if (t.getEdge4().isPopulated()) {
								if (t.getEdge4().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge4().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge4().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge4().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge4().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (t.getEdge5() != null) {
							if (t.getEdge5().isPopulated()) {
								if (t.getEdge5().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge5().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge5().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge5().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge5().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (t.getEdge6() != null) {
							if (t.getEdge6().isPopulated()) {
								if (t.getEdge6().getAnimal().getType().equals("hawk"))
									hawk++;
								if (t.getEdge6().getAnimal().getType().equals("fox"))
									fox++;
								if (t.getEdge6().getAnimal().getType().equals("bear"))
									bear++;
								if (t.getEdge6().getAnimal().getType().equals("elk"))
									elk++;
								if (t.getEdge6().getAnimal().getType().equals("salmon"))
									salmon++;
							}
						}
						if (elk == 1) {
							score++;
							System.out.println("elk");
						}
						if (fox == 1) {
							score++;
							System.out.println("fox");

						}
						if (hawk == 1) {
							score++;
							System.out.println("hawk");

						}
						if (salmon == 1) {
							score++;
							System.out.println("salmon");

						}
						if (bear == 1) {
							score++;
							System.out.println("bear");

						}
						t.checked(true);
					} else {
					}

				}
			}
		}
		for (Tile t : tiles)
			t.checked(false);
		return score;

	}

	public int getBear() {

		int score = 0;
		int cnt = 0;
		for (Tile t : tiles) {
			if (t.isPopulated()) {
				if (t.getAnimal().getType().equals("bear")) {
					if (!t.getIsChecked()) {
						int r = t.getRow();
						int c = t.getColl();
						int bear = 0;
						Tile btile = null;
						if (t.getEdge1() != null) {
							if (t.getEdge1().isPopulated()) {
								if (t.getEdge1().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge1());
								}
							}
						}
						if (t.getEdge2() != null) {
							if (t.getEdge2().isPopulated()) {

								if (t.getEdge2().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge2());
								}

							}
						}
						if (t.getEdge3() != null) {
							if (t.getEdge3().isPopulated()) {

								if (t.getEdge3().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge3());
								}

							}
						}
						if (t.getEdge4() != null) {
							if (t.getEdge4().isPopulated()) {

								if (t.getEdge4().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge4());
								}

							}
						}
						if (t.getEdge5() != null) {
							if (t.getEdge5().isPopulated()) {

								if (t.getEdge5().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge5());

								}

							}
						}
						if (t.getEdge6() != null) {
							if (t.getEdge6().isPopulated()) {

								if (t.getEdge6().getAnimal().getType().equals("bear")) {
									bear++;
									cnt = checkEdges("bear", t.getEdge6());

								}

							}
						}

						if (bear == 1 && cnt == 1) {
							score++;
							System.out.println("bear");
						}
						t.checked(true);
					} else {
					}

				}
			}
		}
		int sc = 0;
		if (score == 1)
			sc = 4;
		if (score == 2)
			sc = 11;
		if (score == 3)
			sc = 19;
		if (score > 3)
			sc = 27;
		for (Tile t : tiles)
			t.checked(false);
		return sc;

	}

	private int checkEdges(String a, Tile t) {
		int cnt = 0;
		if (t.getEdge1() != null) {
			if (t.getEdge1().isPopulated()) {
				if (t.getEdge1().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		if (t.getEdge2() != null) {
			if (t.getEdge2().isPopulated()) {
				if (t.getEdge2().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		if (t.getEdge3() != null) {
			if (t.getEdge3().isPopulated()) {
				if (t.getEdge3().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		if (t.getEdge4() != null) {
			if (t.getEdge4().isPopulated()) {
				if (t.getEdge4().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		if (t.getEdge5() != null) {
			if (t.getEdge5().isPopulated()) {
				if (t.getEdge5().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		if (t.getEdge6() != null) {
			if (t.getEdge6().isPopulated()) {
				if (t.getEdge6().getAnimal().getType().equals(a)) {
					t.checked(true);
					cnt++;
				}
			}
		}
		return cnt;
	}

	/*
	 * public int getElk(){ int score = 0; int cnt=0; for(Tile t: tiles) {
	 * if(t.isPopulated()) { if(t.getAnimal().getType().equals("elk")) {
	 * if(!t.getIsChecked()){ if(t.getEdge1().getAnimal().getType().equals("elk")){
	 * 
	 * } } } } } return -1; }
	 */
	private int getElks(Tile t) {
		int cnt = 0;
		if (t.getEdge1() != null) {
			if (t.getEdge1().isPopulated()) {
				if (t.getEdge1().getAnimal().getType().equals("elk") && t.getEdge1().getIsChecked() == false) {
					t.getEdge1().checked(true);
					cnt += 1 + getElks(t.getEdge1());
				}
			}
		}
		if (t.getEdge2() != null) {
			if (t.getEdge2().isPopulated()) {
				if (t.getEdge2().getAnimal().getType().equals("elk") && t.getEdge2().getIsChecked() == false) {
					t.getEdge2().checked(true);
					cnt += 1 + getElks(t.getEdge2());
				}
			}
		}
		if (t.getEdge3() != null) {
			if (t.getEdge3().isPopulated()) {
				if (t.getEdge3().getAnimal().getType().equals("elk") && t.getEdge3().getIsChecked() == false) {
					t.getEdge3().checked(true);
					cnt += 1 + getElks(t.getEdge3());
				}
			}
		}
		if (t.getEdge4() != null) {
			if (t.getEdge4().isPopulated()) {
				if (t.getEdge4().getAnimal().getType().equals("elk") && t.getEdge4().getIsChecked() == false) {
					t.getEdge4().checked(true);
					cnt += 1 + getElks(t.getEdge4());
				}
			}
		}
		if (t.getEdge5() != null) {
			if (t.getEdge5().isPopulated()) {
				if (t.getEdge5().getAnimal().getType().equals("elk") && t.getEdge5().getIsChecked() == false) {
					t.getEdge5().checked(true);
					cnt += 1 + getElks(t.getEdge5());
				}
			}
		}
		if (t.getEdge6() != null) {
			if (t.getEdge6().isPopulated()) {
				if (t.getEdge6().getAnimal().getType().equals("elk") && t.getEdge6().getIsChecked() == false) {
					t.getEdge6().checked(true);
					cnt += 1 + getElks(t.getEdge6());
				}
			}
		}
		return cnt;
	}

	public int getElk() {
		int cnt = 0;
		int score = 0;
		for (Tile t : tiles) {
			if (t.isPopulated()) {
				if (t.getAnimal().getType().equals("elk")) {
					if (t.getIsChecked() == false) {
						cnt = 1;
						t.checked(true);
						cnt += getElks(t);
						if (cnt == 1)
							score += 2;
						if (cnt == 2)
							score += 4;
						if (cnt == 3)
							score += 7;
						if (cnt == 4)
							score += 10;
						if (cnt == 5)
							score += 14;
						if (cnt == 6)
							score += 18;
						if (cnt == 7)
							score += 23;
						if (cnt >= 8)
							score += 28;
					}
				}

			}
		}
		return score;
	}

	public int getSalmon() {
		int score = 0;
		for (Tile t : tiles) {
			if (t.isPopulated()) {
				if (t.getAnimal().getType().equals("salmon")) {
					int cnt = 0;
					if (t.getIsChecked() == false) {
						t.checked(true);
						cnt += getSalmons(t);
						if(cnt ==0){
							if(checkAlone(t))score +=2;
						}
						if(cnt>0)cnt++;
						if (cnt == 1)
							score += 2;
						if (cnt == 2)
							score += 4;
						if (cnt == 3)
							score += 9;
						if (cnt == 4)
							score += 11;
						if (cnt >= 5)
							score += 17;
					}
				}
			}
		}
		for (Tile t : tiles) {
			t.checked(false);
		}
		return score;
	}
	private boolean checkAlone(Tile t){
		boolean isAlone = true;
		if (t.getEdge1() != null) {
			if (t.getEdge1().isPopulated()) {
				if (t.getEdge1().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		if (t.getEdge2() != null) {
			if (t.getEdge2().isPopulated()) {
				if (t.getEdge2().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		if (t.getEdge3() != null) {
			if (t.getEdge3().isPopulated()) {
				if (t.getEdge3().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		if (t.getEdge4() != null) {
			if (t.getEdge4().isPopulated()) {
				if (t.getEdge4().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		if (t.getEdge5() != null) {
			if (t.getEdge5().isPopulated()) {
				if (t.getEdge5().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		if (t.getEdge6() != null) {
			if (t.getEdge6().isPopulated()) {
				if (t.getEdge6().getAnimal().getType().equals("salmon")) {
					return false;
				}
			}
		}	
		return true;

	}

	private int getSalmons(Tile t) {
		int cnt = 0;
		int border = 0;
		boolean isRun = true;
		if (t.getEdge1() != null) {
			if (t.getEdge1().isPopulated()) {
				if (t.getEdge1().getAnimal().getType().equals("salmon")) {
					if (t.getEdge1().getIsChecked()) {
						border++;
					} else {
						t.getEdge1().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge1());
					}
				}
			}
		}
		if (t.getEdge2() != null) {
			if (t.getEdge2().isPopulated()) {
				if (t.getEdge2().getAnimal().getType().equals("salmon")) {
					if (t.getEdge2().getIsChecked()) {
						border++;
					} else {
						t.getEdge2().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge2());
					}
				}
			}
		}
		if (t.getEdge3() != null) {
			if (t.getEdge3().isPopulated()) {
				if (t.getEdge3().getAnimal().getType().equals("salmon")) {
					if (t.getEdge3().getIsChecked()) {
						border++;
					} else {

						t.getEdge3().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge3());
					}
				}
			}
		}
		if (t.getEdge4() != null) {
			if (t.getEdge4().isPopulated()) {
				if (t.getEdge4().getAnimal().getType().equals("salmon")) {
					if (t.getEdge4().getIsChecked()) {
						border++;
					} else {
						t.getEdge4().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge4());
					}
				}
			}
		}
		if (t.getEdge5() != null) {
			if (t.getEdge5().isPopulated()) {
				if (t.getEdge5().getAnimal().getType().equals("salmon")) {
					if (t.getEdge5().getIsChecked()) {
						border++;
					} else {
						t.getEdge5().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge5());
					}
				}
			}
		}
		if (t.getEdge6() != null) {
			if (t.getEdge6().isPopulated()) {
				if (t.getEdge6().getAnimal().getType().equals("salmon")) {
					if (t.getEdge6().getIsChecked()) {
						border++;
					} else {
						t.getEdge6().checked(true);
						border++;
						cnt += 1 + getSalmons(t.getEdge6());
					}
				}
			}
		}
		if (border > 2)
			return -1000;
		else
			return cnt;

	}

	public int getLargestBiome(String b) {
		int cnt = 0;
		int score = 0;
		ArrayList<Integer> sizes = new ArrayList<>();
		ArrayList<Integer> sides = new ArrayList<>();
		for (Tile t : tiles) {
			String[] habs = t.getSides();
			if (habs[0].equals(b) || habs[2].equals(b) || habs[4].equals(b) || habs[5].equals(b)) {
				if (!t.getIsChecked()) {
					t.checked(true);
					if (habs[0].equals(b))
						sides.add(0);
					if (habs[1].equals(b))
						sides.add(1);
					if (habs[2].equals(b))
						sides.add(2);
					if (habs[3].equals(b))
						sides.add(3);
					if (habs[4].equals(b))
						sides.add(4);
					if (habs[5].equals(b))
						sides.add(5);
					cnt = 1 + getLargestBiomes(b, t, sides);
					sizes.add(cnt);
				}

			}
		}
		for (Tile t : tiles) {
			t.checked(false);
		}

		Collections.sort(sizes, Collections.reverseOrder());
		 for(int i: sizes)System.out.println("size:" + i);
		 return sizes.get(0);
	}

	private int getLargestBiomes(String b, Tile t, ArrayList<Integer> s) {
		int cnt = 0;
		ArrayList<Integer> sides = new ArrayList<>();
		for (int i : s) {
			if (t.getEdge(i) != null) {
				if (t.getEdge(i).getSide((i + 3) % 6).equals(b)) {
					if (!t.getEdge(i).getIsChecked()) {
						t.getEdge(i).checked(true);
						String[] habs = t.getEdge(i).getSides();
						if (habs[0].equals(b))
							sides.add(0);
						if (habs[1].equals(b))
							sides.add(1);
						if (habs[2].equals(b))
							sides.add(2);
						if (habs[3].equals(b))
							sides.add(3);
						if (habs[4].equals(b))
							sides.add(4);
						if (habs[5].equals(b))
							sides.add(5);
						cnt += 1 + getLargestBiomes(b, t.getEdge(i), sides);
					}
				}

			}
		}
		return cnt;
	}

}
