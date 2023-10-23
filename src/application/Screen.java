package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Screen extends HBox {
    private Button restart = new Button("Rejouer");
    private Button changeGameButton = new Button("Changer de jeu");
    private Button randomSolveButton = new Button("Aléatoire");
    private Button cancelPlayButton = new Button("Annuler");
    private Label gameNameLabel = new Label("Version Morpion Solitaire 5D");
    private Label gameOver = new Label();
    private int actualScore;

    public Screen() {
        getChildren().addAll(restart, changeGameButton, randomSolveButton, cancelPlayButton, gameNameLabel, gameOver);
        setSpacing(5);
        setFillHeight(true);
        gameOver.setTextFill(Color.RED);
    }

    /**
     * Gets the restart button.
     *
     * @return the restart button.
     */
    public Button getRestart() {
        return restart;
    }

  
    /**
     * Gets the change game button.
     *
     * @return the change game button.
     */
    public Button getChangeGameButton() {
        return changeGameButton;
    }
    
    /**
     * Gets the random solve button.
     *
     * @return the random solve button.
     */
    public Button getRandomSolveButton() {
        return randomSolveButton;
    }

    /**
     * Gets the cancel play button.
     *
     * @return the cancel play button.
     */
    public Button getCancelPlayButton() {
        return cancelPlayButton;
    }

    /**
     * Gets the game name label.
     *
     * @return the game name label.
     */
    public Label getGameNameLabel() {
        return gameNameLabel;
    }

    /**
     * Gets the game over label.
     *
     * @return the game over label.
     */
    public Label getGameOver() {
        return gameOver;
    }

    /**
     * Gets the actual score.
     *
     * @return the actual score.
     */
    public int getActualScore() {
        return actualScore;
    }

    /**
     * Sets the actual score.
     *
     * @param actualScore the actual score to set.
     * @throws IllegalArgumentException if the score is negative.
     */
    public void setActualScore(int actualScore) {
        if (actualScore < 0) {
            throw new IllegalArgumentException("Le score ne peut pas être négatif.");
        }
        this.actualScore = actualScore;
    }

    /**
     * Updates the display of the menu.
     * Note: Implementation needs to be added to update the display as per the game logic.
     */
    public void repaint() {
        // TODO: 
    }
}
