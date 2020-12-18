/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyconverterfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 *
 * @author Rohit
 */
public class HomeViewController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField txtValue;
    @FXML
    private ComboBox<String> combobox1;
    @FXML
    private ComboBox<String> combobox2;
    
    ObservableList<String> list1 = FXCollections.observableArrayList("USD","EUR","SAR","INR","PHP","BYR","AUD","BGN","CAD","DKK","BRL","MVR","RUB","GBP");
    ObservableList<String> list2 = FXCollections.observableArrayList("USD","INR","PHP","EUR","SAR","BYR","AUD","BGN","CAD","DKK","BRL","MVR","RUB","GBP");
    
    MoneyConverterAPI convertapi = new MoneyConverterAPI();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox1.setItems(list1);
        combobox2.setItems(list2);
    }    

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void convert(ActionEvent event) throws IOException {
        double value = Integer.parseInt(txtValue.getText());
        String From = combobox1.getValue();
        String To = combobox2.getValue();
        
        double rate = convertapi.rate(From,To);
        double ans = rate*value;
        String val = Double.toString(ans);
        label.setText(val + " " + To);
       
    }
    
}
