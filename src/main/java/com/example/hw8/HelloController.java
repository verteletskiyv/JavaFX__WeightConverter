package com.example.hw8;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class HelloController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> checkbox;
    @FXML
    private Label g_res;
    @FXML
    private Label kg_res;
    @FXML
    private TextField label;
    @FXML
    private Label t_res;
    @FXML
    private Label warning_label;

    String currentType = "";
    long num = 0;
    String [] weightType = {"Тонны", "Килограммы", "Граммы"};

    @FXML
    void initialize() {
        checkbox.getItems().addAll(weightType);
        checkbox.setOnAction(this::getCurrentType);
    }

    @FXML
    void convert(KeyEvent event) {
        try {
            if (event.getCode().isDigitKey() || event.getCode().toString().equals("BACK_SPACE")) {
                convertation();
                warning_label.setText("");
            } else
                warning_label.setText("Вводите только числа!");
        }catch (NumberFormatException e){
            System.out.println("Ошибка, с которой я не придумал, как справиться :(");
        }
        }

    @FXML
    void getCurrentType(ActionEvent event) {
        currentType = checkbox.getValue();
        convertation();
    }

    public void convertation() {
        if (label.getText().isBlank()) {
            t_res.setText("0");
            kg_res.setText("0");
            g_res.setText("0");
        } else {
            num = Long.parseLong(label.getText());
            switch (currentType) {
                case "Тонны" -> {
                    t_res.setText(String.valueOf(num));
                    kg_res.setText(String.valueOf(num * 1000));
                    g_res.setText(String.valueOf(num * 1000 * 1000));
                }
                case "Килограммы" -> {
                    kg_res.setText(String.valueOf(num));
                    g_res.setText(String.valueOf(num * 1000));
                    t_res.setText(String.valueOf(num * 0.001));
                }
                case "Граммы" -> {
                    g_res.setText(String.valueOf(num));
                    kg_res.setText(String.valueOf(num * 0.001));
                    t_res.setText(String.valueOf(num * 0.001 * 0.001));
                }
            }
        }
    }

}
