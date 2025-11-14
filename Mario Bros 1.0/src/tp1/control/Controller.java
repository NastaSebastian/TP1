package tp1.control;

import tp1.logic.Game;
import tp1.logic.Action;
import tp1.view.Messages;
import tp1.view.GameView;

public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}

	public void run() {
		view.showWelcome();
		view.showGame();
		
		while (!game.isFinished()) {
			
			String[] userWords = view.getPrompt();
			Command command = CommandGenerator.parse(userWords);
			
			if (command != null) 
				command.execute(game, view);
		    else 
		        view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", words)));
		}
		
		view.showEndMessage();
	}
	
	
    private void execute(String[] words) {
        if (words == null || words.length == 0) {
            view.showError(Messages.UNKNOWN_COMMAND);
            return;
        }

        switch (words[0].trim().toLowerCase()) {
            case "action":
            case "a":
                handleAction(words);
                break;

            case "update":
            case "u":
            case "":
                handleUpdate(words);
                break;

            case "reset":
            case "r":
                handleReset(words);
                break;

            case "help":
            case "h":
                handleHelp(words);
                break;

            case "exit":
            case "e":
                handleExit(words);
                break;

            default:
                view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0].trim()));
        }
    }

    // Tratamos words como un array de comando + acciones
    private void handleAction(String[] words) {
        if (words.length < 2) {
            view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
            return;
        }

        for (int i = 1; i < words.length; i++) {
            Action action = Action.parse(words[i]);
            if (action != null) {
                game.addAction(action);
            } else {
                view.showError(Messages.UNKNOWN_ACTION.formatted(words[i]));
            }
        }

        game.update();
        view.showGame();
    }

    private void handleUpdate(String[] words) {
        if (words.length != 1) {
            view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
            return;
        }

        game.update();
        view.showGame();
    }

	    private void handleReset(String[] words) {
	        if (words.length == 1) {
	            game.reset();
	        } else if (words.length == 2) {
	            try {
	                game.reset(Integer.parseInt(words[1]));
	            } catch (NumberFormatException e) {
	                view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	                return;
	            }
	        } else {
	            view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	            return;
	        }
	
	        view.showGame();
	    }

    private void handleHelp(String[] words) {
        if (words.length != 1) {
            view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
            return;
        }

        view.showMessage(Messages.HELP);
    }

    private void handleExit(String[] words) {
        if (words.length != 1) {
            view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
            return;
        }

        game.exit();
    }
}
