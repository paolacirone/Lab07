package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model; 
	
	private ObservableList<Nerc> choiceBoxList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> choiceBox;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button bttnWorst;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaWorstCase(ActionEvent event) {
    	
    	int maxYears=0;
    	int maxHours=0; 
    	
    	try {
    		maxYears = Integer.valueOf(this.txtYears.getText());
    	     maxHours = Integer.valueOf(this.txtHours.getText());
    	}catch(NumberFormatException e ) {
    		this.txtResult.setText("Devi inserire un numero.");
    	}
    	
    	Nerc c = this.choiceBox.getValue();
    	
    	this.txtResult.appendText(" "+model.massimizzaSequenza(maxYears, maxHours, c));

    }

    @FXML
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnWorst != null : "fx:id=\"bttnWorst\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model=m;
    	this.choiceBoxList = FXCollections.observableArrayList(m.getNercList());
    	this.choiceBox.setItems(choiceBoxList);
    }
}
