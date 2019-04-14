package com.iu3.binaryformatconverter;

import com.iu3.binaryformatconverter.model.BinaryView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController
        implements Initializable {

    BinaryView number;
    @FXML
    private Label nLabel;
    @FXML
    private Label zLabel;
    @FXML
    private Label qLabel;
    @FXML
    private TextField nField;
    @FXML
    private TextField zField;
    @FXML
    private TextField qField;
    @FXML
    private TextField qzField;
    @FXML
    private TextField hexField;
    @FXML
    private TextField binField;
    @FXML
    private TextField decField;

    @FXML
    private void hexHandler(ActionEvent event) {
        this.number = new BinaryView(this.hexField.getText(), BinaryView.Format.HEX);
        initFields();
    }

    @FXML
    private void binHandler(ActionEvent event) {
        this.number = new BinaryView(this.binField.getText(), BinaryView.Format.BIN);
        initFields();
    }

    @FXML
    private void decHandler(ActionEvent event) {
        this.number = new BinaryView(this.decField.getText(), BinaryView.Format.DEC);
        initFields();
    }

    private void initFields() {
        this.hexField.setText(this.number.getHex());
        this.binField.setText(this.number.getBin());
        this.decField.setText(this.number.getDec());
        this.nField.setText(this.number.getDecimalN());
        this.zField.setText(this.number.getDecimalZ());
        this.qField.setText(this.number.getDecimalQ(Integer.parseInt(this.qzField.getText())));
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
