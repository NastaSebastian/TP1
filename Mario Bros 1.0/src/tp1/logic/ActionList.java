package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
    private static final int MAX_REPEAT = 4;
    private final List<Action> actions = new ArrayList<>();
    
    private Action getOpposite(Action action) {
    	Action opposite;
    	switch (action) {
	    	case LEFT:
	    		opposite = Action.RIGHT;
	    		break;
	    	case RIGHT:
	    		opposite = Action.LEFT;
	    		break;
	    	case UP:
	    		opposite = Action.DOWN;
	    		break;
	    	case DOWN:
	    		opposite = Action.UP;
	    		break;
	    	default:
	    		opposite = null;
    	}
    	return opposite;
    }
    
    public void add(Action action) {
    	if (!actions.contains(getOpposite(action))) {
			int count = 0;
			for (Action aux : actions) {
				if (aux == action) {
					count++;
				}
			}
			if (count < MAX_REPEAT) {
				actions.add(action);
			}
		}
    }
    
    public List<Action> cut() {
    	List<Action> copy = new ArrayList<>(actions);
        actions.clear();
        System.out.println("gfddfgdf");
        return copy;
    }
}