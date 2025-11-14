package tp1.logic;

import tp1.logic.GameObjectContainer;
import tp1.logic.Position;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Land;

import tp1.view.ConsoleView;
import tp1.view.GameView;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int remainingTime;
	private int nLevel;
	private int points;
	private int lives;	
	
	private GameObjectContainer container;
	private ConsoleView view;
	
	private boolean playerExits = false;
	private boolean playerWins = false;
	
	public Game(int nLevel) {
		this.remainingTime = 100;
		this.nLevel = nLevel;
		this.lives = 3;
		
		this.container = new GameObjectContainer();
		this.view = new ConsoleView(this);
		
		if (this.nLevel == 0) {
			initLevel0();
		} else if (this.nLevel == 1) {
			initLevel1();
		}
	}
	
	// Metodos de GameModel ---------------------------------------------------
	
	@Override
	public boolean isFinished() {
		return this.playerLoses() || this.playerExits() || this.playerWins();
	}
	
	@Override
	public void update() {
		this.remainingTime--;
		this.container.update();
	}
	
	@Override
    public void reset() {
        this.reset(this.nLevel);
    }

	@Override
    public void reset(int level) {
    	int currentLevel = this.nLevel;
        this.nLevel = level;
        this.remainingTime = 100;

        if (level == 0) this.initLevel0();
        else if (level == 1) this.initLevel1();
        else this.reset(currentLevel);
    }
	
	@Override
	public void exit() {
		this.playerExits = true;
	}
	
	@Override
	public void addObject(GameObject obj) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void addAction(Action action) {
		this.container.addAction(action);
	}
	
	@Override
	public GameObject createObject(String[] objWords) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Metodos de GameWorld ---------------------------------------------------
	
	@Override
	public boolean isSolid(Position pos) {
		return this.container.isSolid(pos);
	}
	
	// Metodos de GameStatus --------------------------------------------------

	@Override
	public String positionToString(int col, int row) {
		return this.container.positionToString(new Position(row, col));
	}

	@Override
	public boolean playerExits() {
		return this.playerExits;
	}
	
	@Override
	public boolean playerLoses() {
		return this.lives == 0;
	}
	
	@Override
	public boolean playerWins() {
		return this.playerWins;
	}
	
	@Override
	public int remainingTime() {
		return this.remainingTime;
	}
	
	@Override
	public int numLives() {
		return this.lives;
	}

	@Override
	public int points() {
		return this.points;
	}

	
	
	
	public void removeGoomba(Goomba goomba) {
		this.container.removeGoomba(goomba);
	}
	
	public void onMarioDeath() {
	    this.lives--;
	    if (!this.playerLoses()) this.reset();
	}
	
	public void marioExited() {
		this.points += this.remainingTime * 10;
		this.remainingTime = 0;
		this.playerWins = true;
	}
	
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void doInteractionsFrom(Mario mario) {
		this.container.doInteractionsFrom(mario);
	}
	
	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		this.container = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			container.add(new Land(new Position(13,col)));
			container.add(new Land(new Position(14,col)));		
		}

		container.add(new Land(new Position(Game.DIM_Y-3,9)));
		container.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			container.add(new Land(new Position(Game.DIM_Y-2, col)));
			container.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		container.add(new Land(new Position(9,2)));
		container.add(new Land(new Position(9,5)));
		container.add(new Land(new Position(9,6)));
		container.add(new Land(new Position(9,7)));
		container.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				container.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		container.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		container.add(new Mario(this, new Position(Game.DIM_Y-3, 0), true));
		container.add(new Goomba(this, new Position(0, 19)));
	}
	
	private void initLevel1() {
		this.nLevel = 1;
		this.remainingTime = 100;
		
		// 1. Mapa
		this.container = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			container.add(new Land(new Position(13,col)));
			container.add(new Land(new Position(14,col)));		
		}

		container.add(new Land(new Position(Game.DIM_Y-3,9)));
		container.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			container.add(new Land(new Position(Game.DIM_Y-2, col)));
			container.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		container.add(new Land(new Position(9,2)));
		container.add(new Land(new Position(9,5)));
		container.add(new Land(new Position(9,6)));
		container.add(new Land(new Position(9,7)));
		container.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				container.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		container.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));
	
		 // 3. Personajes
		
		container.add(new Mario(this, new Position(Game.DIM_Y-3, 0), true)); // big Mario inicial
		container.add(new Goomba(this, new Position(0, 19))); //modif this
		container.add(new Goomba(this, new Position(4, 6)));
		container.add(new Goomba(this, new Position(12, 6)));
		container.add(new Goomba(this, new Position(12, 8)));
		container.add(new Goomba(this, new Position(10, 10)));
		container.add(new Goomba(this, new Position(12, 11)));
		container.add(new Goomba(this, new Position(12, 14)));	
	}
}
