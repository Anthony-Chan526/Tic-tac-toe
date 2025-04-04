package org.example;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class NoughtsAndCrosses extends Application {
    private Button[][] buttons = new Button[3][3];
    private boolean gameOver = false;
    private String current = "X";
    private Label resultLabel = new Label();
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(200, 200);
                grid.add(buttons[i][j], i, j);
                buttons[i][j].setOnAction(new ButtonClickHandler(i, j));
            }
        }
        resultLabel.setFont(new Font("Arial", 50));
        grid.add(resultLabel, 0, 3);
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("NoughtsAndCrosses");
        stage.show();
    }

    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private int row;
        private int col;

        public ButtonClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void handle(ActionEvent event) {
            if (gameOver) {
                return;
            }else if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(current);
                buttons[row][col].setFont(new Font(80));
                if (checkWinner()) {
                    resultLabel.setText(current + " Wins!");
                    gameOver = true;
                    return;
                }
                current = (current=="X") ? "O" : "X";
            }
        }
    }

    public boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(current) && buttons[i][1].getText().equals(current) && buttons[i][2].getText().equals(current)) {
                return true;
            }
            if (buttons[0][i].getText().equals(current) && buttons[1][i].getText().equals(current) && buttons[2][i].getText().equals(current)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(current) && buttons[1][1].getText().equals(current) && buttons[2][2].getText().equals(current)) {
            return true;
        }
        if (buttons[0][2].equals(current) && buttons[1][1].getText().equals(current) && buttons[2][0].getText().equals(current)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) { launch(); }
}
