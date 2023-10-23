package application;

import javafx.scene.layout.VBox;

public class GameScreen extends VBox {
    private Axis gridView;
    private Screen menuView;
    public GameScreen(Screen menuView, GameInterface gameModel) {
        this.menuView = menuView;
        this.gridView = new Axis();
        
        // Adds the grid view and menu view to the VBox.
        getChildren().addAll(gridView, menuView);
        
        // Sets the spacing between the grid view and menu view.
        setSpacing(10);
        
        // Associates the game model with the grid view.
        gridView.setGameModel(gameModel);
    }

    /**
     * Triggers the grid view to redraw itself to reflect any changes in the game state.
     */
    public void updateView() {
        gridView.drawGrid();
    }

    /**
     * Gets the grid view associated with this GameScreen.
     * 
     * @return The grid view.
     */
    public Axis getGridView() {
        return gridView;
    }

    /**
     * Gets the menu view associated with this GameScreen.
     * 
     * @return The menu view.
     */
    public Screen getMenuView() {
        return menuView;
    }
}
