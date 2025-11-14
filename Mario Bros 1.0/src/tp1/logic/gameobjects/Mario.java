package tp1.logic.gameobjects;

import tp1.logic.ActionList;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.logic.Game;

public class Mario {
	private ActionList actionList;
    private Position pos;
    private Game game;
    
    private boolean facingLeft;
    private boolean falling;
    private boolean stopped;
    private boolean big;
    
    private void move() {
		if (this.game.isSolid(this.pos.move(Action.DOWN))) {
			this.falling = false;
	        Position next = facingLeft ? this.pos.move(Action.LEFT) : this.pos.move(Action.RIGHT);
	        if (this.game.isSolid(next) || !next.isInside()) this.facingLeft = !facingLeft;
	        else this.pos = next;
	    } else {
	        Position next = this.pos.move(Action.DOWN);
	        if (next.isInside()) {
	        	this.pos = next;
	        	this.falling = true;
	        }
	        else this.die();
	    }
	}
    
	private void die() {
		game.onMarioDeath();
	}
    
    public Mario(Game game, Position pos, boolean big) {
    	this.actionList = new ActionList();
        this.game = game;
        this.pos = pos;
        
        this.facingLeft = false;
        this.stopped = false;
        this.big = big;
    }

    public boolean isInPosition(Position other) {
    	boolean isInPosition = false;
        if (this.pos.equals(other)) isInPosition = true;
        if (this.big && this.pos.move(Action.UP).equals(other)) isInPosition = true;
        return isInPosition;
    }

    public String getIcon() {
        if (stopped) {
            return Messages.MARIO_STOP;
        } else {
            return facingLeft ? Messages.MARIO_LEFT : Messages.MARIO_RIGHT;
        }
    }
    
    public void addAction(Action act) {
        actionList.add(act);
    }
    
    // TODO Ver lo de actionlist
    public void update() {
    	boolean moved = false;

        for (Action action : actionList.cut()) {
        	switch (action) {
	            case LEFT, RIGHT -> {
	                this.facingLeft = action == Action.LEFT;
	                this.stopped = false;
	                Position next = this.pos.move(action);
	                if (next.isInside() && !game.isSolid(next)) this.pos = next;
	            }
	
	            case UP -> {
	                Position up = this.pos.move(Action.UP);
	                if (up.isInside() && !game.isSolid(up)) this.pos = up;
	            }
	
	            case DOWN -> {
	                Position below = this.pos.move(Action.DOWN);
	                boolean fell = false;
	
	                if (game.isSolid(below)) stopped = true;
	                else {
	                    // Caer mientras haya espacio libre hacia abajo
	                    while (below.isInside() && !game.isSolid(below)) {
	                    	this.pos = below;
	                        below = this.pos.move(Action.DOWN);
	                        fell = true;
	                        game.doInteractionsFrom(this);
	                    }
	
	                    if (!below.isInside()) {
	                        game.onMarioDeath();
	                        return;
	                    }
	                }
	
	                this.falling = fell;
	                moved = true;
	            }
	
	            case STOP -> {
	            	this.stopped = true;
	            	this.falling = false;
	            	moved = true;
	            }
	        }

            game.doInteractionsFrom(this);
        }

        if (!moved && !this.stopped) {
        	this.move();
        	game.doInteractionsFrom(this);
        }
	}
    
    public boolean interactWith(ExitDoor other) {
        boolean samePosition = other.isInPosition(pos);
        if (big) {
            Position upper = pos.move(Action.UP);
            samePosition = samePosition || other.isInPosition(upper);
        }

        if (samePosition) {
            game.marioExited();
            return true;
        }
        return false;
    }
    
    public boolean interactWith(Goomba goomba) {
        if (goomba.isInPosition(this.pos) || (this.big && goomba.isInPosition(this.pos.move(Action.UP)))) {
        	// Goomba muere siempre
            goomba.receiveInteraction(this); // suma 100 puntos

            if (!this.falling) {
                if (big) {
                    this.big = false;
                } else {
                    this.game.onMarioDeath();
                }
            }
            return true;
        }
        return false;
    }
}	