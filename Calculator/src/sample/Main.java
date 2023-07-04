package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class Main extends Application {

    Stage window;
    Scene calculatorMain;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    Button buttonEQ, buttonPL, buttonMN, buttonTM, buttonDV, buttonCL, buttonER;
    TextField display;
    String displayString;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Calculator");

        //Setup variables:
        inputNext = false;
        eqActionDone = false;
        number1STR = "";
        number2STR = "";

        //Grid setup:
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        int windowVbox = 6;
        int windowHbox = 8;
        grid.setVgap(windowVbox);
        grid.setHgap(windowHbox);

        //Display:
        display = new TextField();
        display.setText("0");
        displayString = "0";
        display.setEditable(false);
        //GridPane.setConstraints(display, 1, 1);

        //Top Menu:
        HBox topDisplay = new HBox();
        topDisplay.getChildren().add(display);

        //Button 0-9
        button0 = new Button();
        button0.setText("0");
        button0.setOnAction(e -> {
            sendInput("0");
            changeText();
        });
        GridPane.setConstraints(button0, 2, 6);

        button1 = new Button();
        button1.setText("1");
        button1.setOnAction(e -> {
            sendInput("1");
            changeText();
        });
        GridPane.setConstraints(button1, 1, 5);

        button2 = new Button();
        button2.setText("2");
        button2.setOnAction(e -> {
            sendInput("2");
            changeText();
        });
        GridPane.setConstraints(button2, 2, 5);

        button3 = new Button();
        button3.setText("3");
        button3.setOnAction(e -> {
            sendInput("3");
            changeText();
        });
        GridPane.setConstraints(button3, 3, 5);

        button4 = new Button();
        button4.setText("4");
        button4.setOnAction(e -> {
            sendInput("4");
            changeText();
        });
        GridPane.setConstraints(button4, 1, 4);

        button5 = new Button();
        button5.setText("5");
        button5.setOnAction(e -> {
            sendInput("5");
            changeText();
        });
        GridPane.setConstraints(button5, 2, 4);

        button6 = new Button();
        button6.setText("6");
        button6.setOnAction(e -> {
            sendInput("6");
            changeText();
        });
        GridPane.setConstraints(button6, 3, 4);

        button7 = new Button();
        button7.setText("7");
        button7.setOnAction(e -> {
            sendInput("7");
            changeText();
        });
        GridPane.setConstraints(button7, 1, 3);

        button8 = new Button();
        button8.setText("8");
        button8.setOnAction(e -> {
            sendInput("8");
            changeText();
        });
        GridPane.setConstraints(button8, 2, 3);

        button9 = new Button();
        button9.setText("9");
        button9.setOnAction(e -> {
            sendInput("9");
            changeText();
        });
        GridPane.setConstraints(button9, 3, 3);

        //+ - * / =
        buttonEQ = new Button();
        buttonEQ.setText("=");
        buttonEQ.setOnAction(e -> {
            sendInput("=");
            changeText();
        });
        GridPane.setConstraints(buttonEQ, 3, 6);

        buttonPL = new Button();
        buttonPL.setText("+");
        buttonPL.setOnAction(e -> {
            sendInput("+");
            changeText();
        });
        GridPane.setConstraints(buttonPL, 4, 3);

        buttonMN = new Button();
        buttonMN.setText("-");
        buttonMN.setOnAction(e -> {
            sendInput("-");
            changeText();
        });
        GridPane.setConstraints(buttonMN, 4, 4);

        buttonTM = new Button();
        buttonTM.setText("*");
        buttonTM.setOnAction(e -> {
            sendInput("*");
            changeText();
        });
        GridPane.setConstraints(buttonTM, 4, 6);

        buttonDV = new Button();
        buttonDV.setText("/");
        buttonDV.setOnAction(e -> {
            sendInput("/");
            changeText();
        });
        GridPane.setConstraints(buttonDV, 4, 5);

        // Clear and erase
        buttonCL = new Button();
        buttonCL.setText("C");
        buttonCL.setOnAction(e -> {
            sendInput("C");
            changeText();
        });
        GridPane.setConstraints(buttonCL, 4, 2);

        buttonER = new Button();
        buttonER.setText("E");
        buttonER.setOnAction(e -> {
            sendInput("E");
            changeText();
        });
        GridPane.setConstraints(buttonER, 3, 2);

        //Grid add all:
        grid.getChildren().addAll(display, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
                                    buttonEQ, buttonPL, buttonMN, buttonTM, buttonDV, buttonCL, buttonER);


        //BorderPane:
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setTop(topDisplay);
        mainBorderPane.setCenter(grid);

        //Scene set:
        calculatorMain = new Scene(mainBorderPane, 600, 400);

        //Setting Scene One to start first
        window.setScene(calculatorMain);
        window.show();
    }

    final Pattern patternNumber = Pattern.compile("\\d");
    final Pattern patternAction = Pattern.compile("(\\+|\\-|\\/|\\*)");
    private float number1, number2;
    private String action, number1STR, number2STR;
    private boolean inputNext, eqActionDone;

    private void sendInput (String a) {
        if (patternNumber.matcher(a).matches()) {
            if (displayString == null || displayString.equals("0") || eqActionDone) {
                displayString = a;
            } else {
                displayString = displayString + a;
            }
            if (eqActionDone) {
                eqActionDone = false;
                number1STR = a;
            } else if (!inputNext) {
                number1STR = number1STR + a;
            } else {
                number2STR = number2STR + a;
            }
        } else if (patternAction.matcher(a).matches()) {
            if (eqActionDone) {
                eqActionDone = false;
            }
            if (displayString == null || displayString.equals("0") || displayString.isEmpty()) {
                if (a.equals("-")) {
                    displayString = a;
                    number1STR = a;
                }
            } else if (!inputNext) {
                inputNext = true;
                displayString = displayString + a;
                action = a;
            } else {
                if (!number2STR.isEmpty()) {
                    displayString = getMeResult(action);
                    //inputNext = true;
                    displayString = displayString + a;
                    action = a;
                } else {
                    // Change the action
                    //inputNext = true;
                    displayString = displayString.substring(0, displayString.length() - 1);
                    displayString = displayString + a;
                    action = a;
                }
            }
        } else if (a.equals("=")) {
            if (inputNext && !number2STR.isEmpty()) {
                displayString = getMeResult(action);
                inputNext = false;
                eqActionDone = true;
            }
        } else if (a.equals("E")) {
            if (displayString != null && !displayString.equals("0") && !displayString.isEmpty()) {
                //ability to modify result:
                eqActionDone = false;
                String lastChar = displayString.substring(displayString.length() - 1);
                displayString = displayString.substring(0, displayString.length() - 1);
                //Remove tha last number for first or second number
                if (patternNumber.matcher(lastChar).matches() || lastChar.equals(".")) {
                    // Case if negative number:
                    if (displayString.equals("-") || displayString.isEmpty()) {
                        displayString = "0";
                        number1STR = "";
                    } else if (!inputNext) {
                        number1STR = number1STR.substring(0, number1STR.length() - 1);
                    } else {
                        number2STR = number2STR.substring(0, number2STR.length() - 1);
                    }
                } else if (patternAction.matcher(lastChar).matches()) {
                    action = null;
                    inputNext = false;
                }
            }
        } else if (a.equals("C")) {
            eqActionDone = false;
            action = null;
            inputNext = false;
            displayString = "0";
            number1STR = "";
            number2STR = "";
        }
    }

    private void changeText () {
        display.setText(displayString);
    }

    private String getMeResult (String ac) {
        inputNext = false;
        if (number1STR != null) {
            number1 = Float.parseFloat(number1STR);
        } else {
            number1 = 0;
        }
        if (number2STR != null) {
            number2 = Float.parseFloat(number2STR);
        } else {
            number2 = 0;
        }

        switch(ac) {
            case "+":
                number1 = number1 + number2;
                break;
            case "-":
                number1 = number1 - number2;
                break;
            case "*":
                number1 = number1 * number2;
                break;
            case "/":
                number1 = number1 / number2;
                break;
            default:
                // do nothing
        }
        // Setup for next number:
        number1STR = number1 + "";
        number2STR = "";

        return number1STR;
    }
}
