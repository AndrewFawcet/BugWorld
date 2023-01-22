import java.util.ArrayList;

public class World {

	public ArrayList<Bug> bugs = new ArrayList<>();
	public ArrayList<Plant> plants = new ArrayList<>();
	public ArrayList<Obstacle> obstacles = new ArrayList<>();

	private int height;
	private int width;
	private boolean foodNear;
	
	// world constructor
	public World(int height, int width) {
		this.height = height;
		this.width = width;

		this.setupScene();

		this.drawWorld();
		this.moveTest();

	}

	private void moveTest() {
		for (int i = 0; i < 50; i++) {
			Bug deadBug = null;
			for (Bug b : bugs) {
				b.eatPlant(this);
				b.setEnergy(b.getEnergy()-2); //lose 2 energy per move
				if (b.getEnergy() > 10) {
					b.randomMove(this);
				}
				else if (b.getName().equals("Flug")) {
					b.smellMeat(this);
					b.eatMeat(this);
//					b.randomMove(this); // will be the carnivore
				}
				else {
					b.smellFood(this);
				}
				if (foodNear == false) {	//checks if foodNear,
					b.randomMove(this);
				}
				else {
					foodNear = false;		//resets boolean
				}
			}

			for (Plant p : plants) {
				p.plantGrow();
			}
			this.drawWorld();
		}

	}

	private void setupScene() {
		Bug one = new Bug("Dragonfly", "Bob", '%', (int) (Math.random() * 20), (int) (Math.random() * 20), 15, 1323);
		Bug two = new Bug("BigBug", "Flug", '+', (int) (Math.random() * 20), (int) (Math.random() * 20), 15, 4323);
		Bug three = new Bug("SlimeBug", "Lob", '*', (int) (Math.random() * 20), (int) (Math.random() * 20), 15, 3323);
		Bug four = new Bug();

		bugs.add(one);
		bugs.add(two);
		bugs.add(three);
		bugs.add(four);

		Plant five = new Plant();
		checkPlantToBugs(five); // checking plants location to where the bugs are
		Plant six = new Plant();
		checkPlantToBugs(six);
		Plant seven = new Plant();
		checkPlantToBugs(seven);

		plants.add(five);
		plants.add(six);
		plants.add(seven);

		Obstacle eight = new Obstacle();
		checkObstacleToPlantsAndBugs(eight); // checking objects location to where plants and bugs are
		Obstacle nine = new Obstacle();
		checkObstacleToPlantsAndBugs(nine);
		Obstacle ten = new Obstacle();
		checkObstacleToPlantsAndBugs(ten);

		obstacles.add(eight);
		obstacles.add(nine);
		obstacles.add(ten);

		for (Bug b : bugs) {
			System.out.println(b.toString());
		}
		for (Plant p : plants) {
			System.out.println(p.getName() + " " + p.getSize());
		}

	}

	public void checkPlantToBugs(Plant p) {
		boolean seperate = false;
		while (!seperate) {
			int i = 0;
			for (Bug b : bugs) {
				if ((p.getX() == b.getX()) && (p.getY() == b.getY())) {
					p = new Plant();
				} else {
					i++;
				}
			}
			if (bugs.size() == i)
				seperate = true;
		}
	}

	public void checkObstacleToPlantsAndBugs(Obstacle o) {
		boolean seperate = false;
		while (!seperate) {
			int i = 0;
			for (Bug b : bugs) {
				if ((o.getX() == b.getX()) && (o.getY() == b.getY())) {
					o = new Obstacle();
				} else {
					i++;
				}
			}
			int j = 0;
			for (Plant p : plants) {
				if ((o.getX() == p.getX()) && (o.getY() == p.getY())) {
					o = new Obstacle();
				} else {
					j++;
				}
			}
			if ((bugs.size() == i) && (plants.size() == j))
				seperate = true;
		}

	}

	public void drawWorld() {
		System.out.print('+');
		for (int i = 0; i < width; i++)
			System.out.print('-');
		System.out.println('+');
		for (int y = 0; y < height; y++) {
			System.out.print('|');
			for (int x = 0; x < width; x++) {
				int i;
				int j;
				int k;
				boolean bugHere = false;
				for (i = 0; i < bugs.size(); i++) {
					Bug b = bugs.get(i);
					if (b.getX() == x && b.getY() == y) {
						System.out.print(b.getSymbol());
						bugHere = true;
						break;
					}
				}
				for (j = 0; j < plants.size(); j++) {
					Plant p = plants.get(j);
					if (p.getX() == x && p.getY() == y) {
						if (bugHere == false) {
							System.out.print(p.getSymbol());
						}
						break;
					}
				}
				for (k = 0; k < obstacles.size(); k++) {
					Obstacle o = obstacles.get(k);
					if (o.getX() == x && o.getY() == y) {
						if (bugHere == false) {
							System.out.print(o.getSymbol());
						}
						break;
					}
				}

				if (i == bugs.size() && j == plants.size() && k == obstacles.size()) {
					System.out.print(' ');
				}
//				else if (j == plants.size()){
//					System.out.print(' ');
//				}

			}
			System.out.println('|');
		}
		System.out.print('+');
		for (int i = 0; i < width; i++)
			System.out.print('-');
		System.out.println('+');
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isFoodNear() {
		return foodNear;
	}

	public void setFoodNear(boolean foodNear) {
		this.foodNear = foodNear;
	}

}
