package tp1.logic;

public interface GameStatus {

	public String positionToString(int col, int row);
	public boolean playerExits();
	public boolean playerLoses();
	public boolean playerWins();
	public int remainingTime();
	public int numLives();
	public int points();
}
