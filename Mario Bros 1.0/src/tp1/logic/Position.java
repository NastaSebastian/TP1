package tp1.logic;

public class Position {

	private int col;
	private int row;

	public Position (int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Position move(Action action) {
        return new Position(this.row + action.getY(), this.col + action.getX());
    }
	
	public boolean isInside() {
	    return this.row >= 0 
	        && this.row <= Game.DIM_Y - 1
	        && this.col >= 0 
	        && this.col <= Game.DIM_X - 1;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }
}
