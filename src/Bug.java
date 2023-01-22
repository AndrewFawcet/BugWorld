import java.util.Scanner;

public class Bug {

	private String species;
	private String name;
	private char symbol;
	private int x;
	private int y;
	private int energy;
	private int ID;

	public Bug(String species, String name, char symbol, int x, int y, int energy, int ID) {
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.ID = ID;

	}

	public Bug() {
		species = "Bug";
		name = "Buggy Bug";
		symbol = '#';
		x = (int) (Math.random() * 20);
		y = (int) (Math.random() * 20);
		energy = 10;
		ID = (int) (Math.random() * 10);
	}

	public void eatPlant(World w) {
		Plant remove = null;
		for (Plant p : w.plants) {
			if ((x == p.getX()) && (y == p.getY())) {
				energy += 4;
				p.setSize(p.getSize() - 4);
				if (p.getSize() <= 0) {
					remove = p;
				}
			}
		}
		w.plants.remove(remove); // removes plant if too small, why can't I get size here?

	}

	public void eatMeat(World w) {
		boolean eatOnce = false;
		Bug remove = null;
		for (Bug b : w.bugs) {
			if (b.getName().equals("Flug")) {
				break; // does not eat itself
			}
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if ((x + i == b.getX()) && (y + j == b.getY()) && !eatOnce) {
						eatOnce = true;
						energy += 10;
//						remove = w.deadBug(); How to access??
						// w.bugs.remove(b);
					}
				}
			}
		}
		if (eatOnce) {
			w.bugs.remove(remove);
		}
	}

	public void smellMeat(World w) {
		boolean moveOnce = false;
		for (int xGrid = -3; xGrid < 4; xGrid++) { // smell range 3
			for (int yGrid = -3; yGrid < 4; yGrid++) {
				for (Bug b : w.bugs) {
					if (b.getName().equals("Flug")) {
						break; // does not smell move for itself
					}
					if ((x + xGrid == b.getX()) && (y + yGrid == b.getY()) && moveOnce) {
						moveOnce = true;
						w.setFoodNear(true);
						gridMove(xGrid, yGrid, w);
						
//						if (xGrid == 0) { // moving on the y (j) plane always second
//							if (yGrid == 3 || yGrid == -3) {
//								y = y + yGrid / 3;
//							} else if (yGrid == 2 || yGrid == -2) {
//								y = y + yGrid / 2;
//							} else {
//								y = y + yGrid;
//							}
//
//						} else { // move on x (i) plane first
//							if (xGrid == 3 || xGrid == -3) {
//								x = x / 3;
//							} else if (xGrid == 2 || xGrid == -2) {
//								x = x + xGrid / 2;
//							} else {
//								x = x + xGrid;
//							}
//						}

					}
				}
			}
		}
	}

	public void smellFood(World w) {
		boolean moveOnce = false;
		for (int xGrid = -2; xGrid < 3; xGrid++) { // smell range 2
			for (int yGrid = -2; yGrid < 3; yGrid++) {
				for (Plant p : w.plants) {
					if ((x + xGrid == p.getX()) && (y + yGrid == p.getY()) && moveOnce) {
						moveOnce = true;
						w.setFoodNear(true);
						gridMove(xGrid, yGrid, w);
						
//						if (xGrid == 0) {
//							if (yGrid == 2 || yGrid == -2) {
//								y = y + yGrid / 2;
//							} else {
//								y = y + yGrid;
//							}
//						} else {
//							if (xGrid == 2 || xGrid == -2) {
//								x = x + xGrid / 2;
//							} else {
//								x = x + xGrid;
//							}

						
					}
				}
			}
		}

	}
	
	public void gridMove(int xGrid, int yGrid, World w) {
		boolean obstruction = true;
		
		boolean xUpObstruction = false;
		boolean xDownObstruction = false;
		boolean yUpObstruction = false;
		boolean yDownObstruction = false;
		
		for (Obstacle o : w.obstacles ) {
			if (o.getX()== (x+1) && o.getY() == y) {
				xUpObstruction = true;
			}
			else if (o.getX()== (x-1) && o.getY() == y) {
				xDownObstruction = true;
			}
			else if (o.getX()== x && o.getY() == (y+1)){
				yUpObstruction = true;
			}
			else if (o.getX()== x && o.getY() == (y-1)) {
				yDownObstruction = true;
			}
		}
		
		if ((xGrid > 0) && !xUpObstruction ) {
			x++;
			obstruction = false;
		}
		else if ((xGrid < 0) && !xDownObstruction) {
			x--;
			obstruction = false;
		}
		else if ((yGrid > 0) && !yUpObstruction){
			y++;
			obstruction = false;
		}
		else if ((yGrid < 0) && !yDownObstruction){
			y--;
			obstruction = false;
		}
		else {
			System.out.println("gridmove err");
		}
		
		if (obstruction == true) {
			randomMove(w);
			// some boolean to make bug go to random move.
		}
	}

	public void randomMove(World w) {
		int direction = (int) (Math.random() * 4);
		int yOld = y;
		int xOld = x;
		if (direction == 0) {
			y ++;
		} else if (direction == 1) {
			y --;
		} else if (direction == 2) {
			x ++;
		} else if (direction == 3) {
			x --;
		} else {
			System.out.println("random direction err");
		}

		// moving bug back into bounds
		if (x == (w.getWidth())) {
			x--;
		}
		if (x == 0) {
			x++;
		}
		if (y == (w.getHeight())) {
			y--;
		}
		if (y == 0) {
			y++;
		}

		// checking bug not trying to move onto a obstacle
		for (Obstacle o : w.obstacles) {
			if (x == o.getX() && y == o.getY()) {
				x = xOld;
				y = yOld;
				randomMove(w);			//is it OK for a method to call itself?
			}
		}

	}

	public void UserMove(String direction) {
//		Scanner myDir = new Scanner(System.in); // Create a Scanner object
//		System.out.println("Enter the Bug direction \n N, S, E or W");
//		String direction = myDir.next().toUpperCase();
		if (direction.equals("N")) {
			y += 1;
		} else if (direction.equals("S")) {
			y -= 1;
		} else if (direction.equals("E")) {
			x -= 1;
		} else if (direction.equals("W")) {
			x += 1;
		} else {
			System.out.println("input errrr");
		}
//		myDir.close();
	}

	public Bug UserBug() {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		species = "Bug";
		System.out

				.println("Enter Bug name");
		name = myObj.nextLine();
		System.out.println("Enter Bug symbol");
		symbol = myObj.next().charAt(0);
		myObj.nextLine();
		x = (int) (Math.random() * 20);
		y = (int) (Math.random() * 20);
		energy = 10;
		System.out.println("Enter Bug ID");
		ID = myObj.nextInt();
		myObj.close();
		Bug bug = new Bug(species, name, symbol, x, y, energy, ID);
		return bug;
	}

	public String toString() {
		return "Bug Species " + species + ", name " + name + ", symbol " + symbol;
	}

	public String toText() {
		return "Bug Sprecies " + species + ", name " + name + ", symbol " + symbol + ", x " + x + ", y " + y
				+ ", energy " + energy + ", ID " + ID;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
