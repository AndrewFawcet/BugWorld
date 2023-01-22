
public class Obstacle {

	private String name;
	private char symbol;
	private int x;
	private int y;
	
	public Obstacle(String name, int size, char symbol, int x, int y) {
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
	}
	
	public Obstacle() {
		name = "blocky";
		symbol = 'Ø';
		x = (int) (Math.random() * 20);
		y = (int) (Math.random() * 20);				
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
	
	
	
}
