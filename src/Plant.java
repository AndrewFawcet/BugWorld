
public class Plant {

	
	
	
	private String name;
	private int size;
	private char symbol;
	private int x;
	private int y;
	
	public Plant(String name, int size, char symbol, int x, int y) {
		this.name = name;
		this.size = size;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
	}
	
	public Plant() {
		name = "b-angoes";
		size = (int)(Math.random() *10);
		symbol = 't';
		x = (int) (Math.random() * 20);
		y = (int) (Math.random() * 20);
				
	}
	
	public void plantGrow() {
		size++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
