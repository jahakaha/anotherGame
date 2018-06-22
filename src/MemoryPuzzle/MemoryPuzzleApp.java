import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// Creating "MemoryPuzzleApp" class
public class MemoryPuzzleApp extends Application {

    // Creating 2 objects
    private static final int NUM_OF_PAIRS = 8; // Number of, how many pairs it have
    private static final int NUM_PER_ROW = 4; // Number of, how many rows it have

    private Tile selected = null;
    private int clickCount = 2; // Number of clicks

    private Parent createContent() {
        Pane root = new Pane(); // Here we create a Pane which name is "root"
        root.setPrefSize(200, 200); // Our Pane's size

        char c = 'A';

        // We create a collection: List
        List<Tile> tiles = new ArrayList<>();
        for (int index = 0; index < NUM_OF_PAIRS; index++) {

            // Instantiating Tile class
            tiles.add(new Tile(String.valueOf(c)));
            tiles.add(new Tile(String.valueOf(c)));
            c++;
        }

        // There we use shuffle method to shuffle our elements in List
        Collections.shuffle(tiles);

        for (int index = 0; index < tiles.size(); index++) {

            // Instantiating Tile class
            Tile tile = tiles.get(index);
            tile.setTranslateX(50 * (index % NUM_PER_ROW)); // Coordinates for X
            tile.setTranslateY(50 * (index / NUM_PER_ROW)); // Coordinates for Y
            root.getChildren().add(tile);
        }

        // Returning the Pane
        return root;
    }


    @Override

    //Implementing start method
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Memory Puzzle");
        primaryStage.show();
    }

    // Here we create a class: Title
    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile(String value) {

            // Creating a rectangle
            Rectangle border = new Rectangle(50, 50); // Giving size for rectangle

            // By using Color class, we give colors for our objects
            border.setFill(Color.BLACK);
            border.setStroke(Color.DARKGRAY);

            // Here we set a text
            text.setText(value);
            text.setFill(Color.WHITE); // Color of text
            text.setFont(Font.font(30)); // Size of text

            setAlignment(Pos.CENTER);
            // Here, by using addAll method on getChildren we add 2 objects
            getChildren().addAll(border, text);

            setOnMouseClicked(this::handleMouseClick);
            close();
        }

        // Implementing handleMouseClick method
        public void handleMouseClick(MouseEvent event) {

            // As shown above, we use lambda expression
            if (isOpen() || clickCount == 0)
                return;

            clickCount--;

            // Here we use if/else
            if (selected == null) {
                selected = this;
                open(() -> {});
            }
            else {
                open(() -> {
                    if (!hasSameValue(selected)) {
                        selected.close();
                        this.close();
                    }

                    selected = null;
                    clickCount = 2;
                });
            }
        }

        // Implementing isOpen method for our tiles
        public boolean isOpen() {
            return text.getOpacity() == 1; // Giving a variable for opacity
        }

        // Implementing open method
        public void open(Runnable action) {

            // FadeTransition class for simple animation
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        // Implementing close method
        public void close() {

            // Here we also use FadeTransition
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(0);
            ft.play();
        }

        // Implementing hasSameValue method
        public boolean hasSameValue(Tile other) {
            return text.getText().equals(other.text.getText());
        }
    }

    // Implementing main method to launch our application
    public static void main(String[] args) {
        launch(args);
    }
}