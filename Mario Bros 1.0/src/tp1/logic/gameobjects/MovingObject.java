package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.Game;

public abstract class MovingObject extends GameObject {

	protected boolean facingLeft;
    protected boolean isFalling;

    public MovingObject(Game game, Position pos) {
        super(game, pos);
        this.facingLeft = false;
        this.isFalling = false;
    }

    protected void move() {
    	Position down = this.pos.move(Action.DOWN);
		if (this.game.isSolid(down)) {
			this.isFalling = false;
	        Position next = facingLeft ? this.pos.move(Action.LEFT) : this.pos.move(Action.RIGHT);
	        if (this.game.isSolid(next) || !next.isInside()) this.facingLeft = !facingLeft;
	        else this.pos = next;
	    } else {
	        if (down.isInside()) {
	        	this.pos = down;
	        	this.isFalling = true;
	        }
	        else this.onFallOutOfMap();
	    }
    }

    protected abstract void onFallOutOfMap();

    @Override
    public boolean isSolid() {
        return false;
    }
}
