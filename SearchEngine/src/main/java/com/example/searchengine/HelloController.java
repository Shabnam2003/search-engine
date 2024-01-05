package com.example.searchengine;

import com.example.searchengine.searchsection.CompleteMap;
import com.example.searchengine.searchsection.FindWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    CompleteMap map = new CompleteMap();

    @FXML
    private TextArea result;

    @FXML
    private TextField text;

    public HelloController() throws IOException {
    }

    @FXML
    void showResult(ActionEvent event) {
        try {
            FindWord findWord = new FindWord(text.getText(), this.map);
            result.setText(findWord.searching());
            result.setVisible(true);
        } catch (NullPointerException nullPointerException) {
            result.setVisible(false);
        }
    }


}