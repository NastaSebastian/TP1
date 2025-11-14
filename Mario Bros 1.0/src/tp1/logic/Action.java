package tp1.logic;

public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);
	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static Action parse(String input) {
		Action action;
        input = input.toLowerCase();

        switch (input) {
            case "left": case "l": action = Action.LEFT; break;
            case "right": case "r": action = Action.RIGHT; break;
            case "up": case "u": action = Action.UP; break;
            case "down": case "d": action = Action.DOWN; break;
            case "stop": case "s": action = Action.STOP; break;
            default: action = null; 
        }
        return action;
    }
}
