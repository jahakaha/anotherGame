package MemoryPuzzle;

// Importing needed classes
import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

// Creating Controller class
public class Controller implements Initializable {

    // This string array is for derives
    private String[] derives = {     "ln'(x)",
                                     "(sinx)'",
                                     "(cscx)'",
                                     "(cosx)'",
                                     "(secx)'",
                                     "(sechx)'",    "1/x",
                                                    "cosx",
                                                    "-cscxcotx",
                                                    "-sinx",
                                                    "secxtanx",
                                                    "-tanhxsechx"};

    // This string array is for integrals
    private String[] integrals = {    "∫dx",
                                      "∫cosxdx",
                                      "∫sinxdx",
                                      "∫secxtanxdx",
                                      "∫cscxcotxdx",
                                      "∫tanxdx",      "x+C",
                                                      "sinx+C",
                                                      "-cosx+C",
                                                      "secx+C",
                                                      "-cscx+C",
                                                      "ln|secx|+C"};

    // Creating a string array for colors
    private String[] colors = { "#0cadda", "#23da44", "#FA58F4", "#B45F04", "#5830da", "#FE2E2E"};

    private boolean first = true; // Boolean value for first picked tile

    private int index = 0, count = 0, recolor = 0;

    // 2 strings for button IDs
    private String a = "", b = "";


    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_no;

    @FXML
    private Button btn_yes;

    @FXML
    private Button btn_play;

    @FXML
    private JFXButton btn_main;

    @FXML
    private JFXButton btn_howto;

    @FXML
    private Text txt_derivative;


    @FXML
    private Pane integra_pane;

    @FXML
    private Text txt_integrals;

    @FXML
    private GridPane integrals_matrix = new GridPane();


    @FXML
    private Button btn_deriv;


    @FXML
    private Button btn_integrals;


    @FXML
    private GridPane derives_matrix = new GridPane();



    @FXML

    // Implementing play method
    void play(ActionEvent event) throws IOException{

        // Loading by fxmlloader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("play.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage)btn_play.getScene().getWindow();
        st.close();
    }

    @FXML
    // Implementing yes_action method
    void yes_action(ActionEvent event) {

        System.exit(0); // Closing the application

    }

    @FXML
    // Implementing no_action method
    void no_action(ActionEvent event) {

        Stage p_stage = (Stage) btn_no.getScene().getWindow();
        p_stage.close();

    }
    @FXML
    void back(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage) btn_back.getScene().getWindow();
        st.close();
    }
    @FXML
    // Implementing howto method
    void howto(ActionEvent event) throws IOException{

        // Loading fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HowToPlay.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage) btn_play.getScene().getWindow();
        st.close();


    }


    @FXML
    // Implementing exit method
    void exit(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quit_menu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); // Undecorated stage using method

//        ScaleTransition st = new ScaleTransition(Duration.millis(1000), root1);
//        st.setByX(0.1);
//        st.setByY(0.1);
//        st.play();

        stage.setScene(new Scene(root1));
        stage.show();

//        Stage st = (Stage) btn_play.getScene().getWindow();
//        st.close();

    }

    @FXML
    // Implementing derivatives method
    void derivatives(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("derivative.fxml"));
        Parent root_der = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root_der));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage)btn_deriv.getScene().getWindow();
        st.close();
    }


    @FXML
    // Implementing integrals method
    void integrals(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("integral.fxml"));
        Parent root_der = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root_der));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage) btn_integrals.getScene().getWindow();
        st.close();
    }


    @FXML
    // Implementing main_menu method
    void main_menu(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();

        Stage st = (Stage)btn_main.getScene().getWindow();
        st.close();

    }

    @Override
    // Implementing the main initialize method
    public void initialize(URL location, ResourceBundle resources) {

        // Creating 2 string arrays
        String[] derivatives = new String[derives.length];
        String[] intgrls = new String[integrals.length];

        // Here we use for loop for giving an index value
        for (int index = 0; index < derives.length; index++) {
            derivatives[index] = derives[index];
            intgrls[index] = integrals[index];
        }

        // Then we create an ArrayList for derivatives
        List<String> list1 = Arrays.asList(derivatives);
        Collections.shuffle(list1);
        derivatives = list1.toArray(new String[list1.size()]);

        // And for integrals also
        List<String> list2 = Arrays.asList(intgrls);
        Collections.shuffle(list2);
        intgrls = list2.toArray(new String[list2.size()]);

        // In this for loop, we use our created addButtons method for derivatives
        for (int index1 = 0; index1 < 4; index1++) {
            for (int index2 = 0; index2 < 3; index2++) {
                addButtons(index1, index2, derivatives, derives, derives_matrix);
            }
        }

        // Here we also use created addButtons method, but for integrals
        for (int index1 = 0; index1 < 4; index1++) {
            for (int index2 = 0; index2 < 3; index2++) {
                addButtons(index1, index2, intgrls, integrals,integrals_matrix);
            }
        }


    }

    // Implementing addButtons method for creating 12 buttons
    private void addButtons(int index1, int index2, String[] stringArray1, String[] stringArray2, GridPane pane) {

        // Creating a PauseTransition for showing a text in button
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));

        JFXButton jfxButton = new JFXButton(""); // Here I downloaded an external components from the Net

        jfxButton.setStyle("-fx-text-fill: black; -fx-background-color: white"); // Giving a color style

        jfxButton.setMaxHeight(100); // Size for height
        jfxButton.setMaxWidth(100); // Size for width
        jfxButton.setId(stringArray1[index++%12]);

        // Here we add them to pane
        pane.add(jfxButton, index1, index2);

        // Lambda expression is used above
        jfxButton.setOnAction(e -> {

            // Setting text for button
            jfxButton.setText(jfxButton.getId());

            // if/else is used above
            if (first) {
                a = jfxButton.getId(); // Giving ID for button
                first = false;

            }
            else {
                b = jfxButton.getId(); // Here we also give an ID
                first = true;

                // Lambda expression
                pause.setOnFinished(event -> {
                    check(a, b, jfxButton.getScene(), stringArray2, pane);
                });
                pause.play();

            }
        });

    }

    // Implementing check method
    private void check (String a, String b, Scene scene, String[] stringArray, GridPane pane) {

        int number1 = 0, number2 = 0;

        // Creating 2 buttons
        Button button1 = (Button) scene.lookup("#" + a);
        Button button2 = (Button) scene.lookup("#" + b);

        for (int index = 0; index < stringArray.length; index++) {

            // Using if for giving an index value for number1 and number2
            if (a.equals(stringArray[index]))  number1 = index;
            if (b.equals(stringArray[index]))  number2 = index;

        }

        // Using if statement for button empty text
        if (Math.abs(number1 - number2) != 6) {

                button1.setText("");
                button2.setText("");
        }

        // Else statement for setting colors (style) for buttons
        else  {

            button1.setStyle("-fx-text-fill: white; -fx-background-color: " + colors[recolor]);
            button2.setStyle("-fx-text-fill: white; -fx-background-color: " + colors[recolor]);

            count += 2;
            recolor++;
        }

        // Using if statement above for count
        if (count == 12) {

            try {
                GameOver(pane);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Implementing GameOver method
    public void GameOver(GridPane pane) throws IOException {

        // PauseTransition when game is over
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

        // Lambda expression
        pause.setOnFinished(e ->{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameover.fxml"));
            Parent root1 = null;

            // Using try/catch
            try {
                root1 = fxmlLoader.load();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // Creating a stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false); // Is not resizable
            stage.show();

            Stage st = (Stage)pane.getScene().getWindow();
            st.close();

        });
        pause.play();
    }

}
