package org.example.pojektzaliczeniowy;

import Classes.Progressive;
import Classes.Regressive;
import Classes.TXTFileReader;
import Classes.XLSXFileReader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProgramController {

    private FileChooser fileChooser = new FileChooser();
    private Stage stage, popUpStage;
    private Progressive progResult;
    private Regressive regResult;
    private String fileNameBase, fileNameFacts;
    @FXML
    private TextField baseArea, factArea;
    @FXML
    private Button loadBase, loadFacts, checkTarget, generateAnswer, clearTextFieldButton,clearFacts;
    @FXML
    private RadioButton progRadio, regRadio;
    @FXML
    private CheckBox targetCheckBox;
    @FXML
    private ComboBox listOfTargets;
    @FXML
    private TextArea answerArea;

    @FXML
    protected void onMouseClickedBase() {
        fileChooser.setTitle("Open a File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files","*.txt","*.xlsx"),
                new FileChooser.ExtensionFilter("TxT File", "*.txt"),
                new FileChooser.ExtensionFilter("XLSX File", "*.xlsx"));
        File initialDirectory = new File(System.getProperty("user.dir") + File.separator + "Data");

        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        File file = fileChooser.showOpenDialog(stage);
        try {
                fileNameBase = file.getName();
                baseArea.setText(file.getAbsolutePath());
                factArea.setVisible(true);
                loadFacts.setVisible(true);
                clearFacts.setVisible(true);
                checkBoxChecked();
        } catch (Exception e) {
            clearVisibilityWhenBaseNull();
            baseArea.setText("Wrong File");
        }

    }

    @FXML
    protected void onMouceClickedLoadFacts() {
        fileChooser.setTitle("Open a File");

        File initialDirectory = new File(System.getProperty("user.dir") + File.separator + "Data");

        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        File file = fileChooser.showOpenDialog(stage);
        try {
                fileNameFacts = file.getName();
                factArea.setText(file.getAbsolutePath());
                progRadio.setVisible(true);
                regRadio.setVisible(true);
                progRadio.setSelected(false);
                regRadio.setSelected(false);
                targetCheckBox.setSelected(false);
                radioChecked();
        } catch (Exception e) {
                clearVisibilityWhenFactsNull();
                factArea.setText("Wrong File");
        }

    }


    @FXML
    protected void onMouseClickedClear(){
        answerArea.clear();
    }

    @FXML
    protected void onMouseClickedClearBase(){
        baseArea.clear();
        clearVisibilityWhenBaseNull();
        if (progRadio.isSelected()){
                if (progResult != null){
                    progResult.eraseKnowledgeBase();
                }
        } else if (regRadio.isSelected()) {
            if (regResult != null){
                regResult.eraseKnowledgeBase();
            }
        }
    }
    @FXML
    protected void onMouseClickedClearFacts(){
        factArea.clear();
        clearVisibilityWhenFactsNull();
        if (progRadio.isSelected()){
            if (progResult != null){
                progResult.eraseFactSet();
            }
        } else if (regRadio.isSelected()) {
            if (regResult != null){
                regResult.eraseFactSet();
            }
        }
    }

    protected void clearVisibilityWhenBaseNull(){
        progRadio.setVisible(false);
        regRadio.setVisible(false);
        factArea.setVisible(false);
        loadFacts.setVisible(false);
        targetCheckBox.setVisible(false);
        generateAnswer.setVisible(false);
        listOfTargets.setVisible(false);
        clearFacts.setVisible(false);
        checkTarget.setVisible(false);
        factArea.clear();
        baseArea.clear();
        answerArea.clear();
    }

    protected void clearVisibilityWhenFactsNull(){
        progRadio.setVisible(false);
        regRadio.setVisible(false);
        targetCheckBox.setVisible(false);
        generateAnswer.setVisible(false);
        listOfTargets.setVisible(false);
        checkTarget.setVisible(false);
        factArea.clear();
        answerArea.clear();
    }

    @FXML
    protected void onMouseClickedGenFacts() {

            if (progRadio.isSelected() && !targetCheckBox.isSelected()) {
                if (!baseArea.getText().equals("") && !baseArea.getText().equals("Wrong File")) {
                    progResult = new Progressive();
                    progResult.loadKnowledgeBaseSet(baseArea.getText());
                    if (!factArea.getText().equals("") && !factArea.getText().equals("Wrong File")) {
                        progResult.loadFactSet(factArea.getText());
                        answerArea.setText(progResult.execute().stream().collect(Collectors.joining("\n")));
                    }
                }

            } else if (progRadio.isSelected() && targetCheckBox.isSelected()) {
                if (!baseArea.getText().equals("") && !baseArea.getText().equals("Wrong File")) {
                    if (listOfTargets.getValue() != null) {
                        progResult = new Progressive(listOfTargets.getValue().toString());
                        progResult.loadKnowledgeBaseSet(baseArea.getText());
                        if (!factArea.getText().equals("") && !factArea.getText().equals("Wrong File")) {
                            progResult.loadFactSet(factArea.getText());
                            List<String> checkingTargetList = progResult.execute();
                            if (checkingTargetList.contains(listOfTargets.getValue())){
                                answerArea.setText(progResult.execute().stream().collect(Collectors.joining("\n"))
                                        + "\nCel jest osiągalny :)");
                            }else {
                                answerArea.setText(progResult.execute().stream().collect(Collectors.joining("\n"))
                                        + "\nCel jest nieosiągalny :(");
                            }
                        }
                    }
                }
            }
    }

    @FXML
    protected void onMouseClickedTargets() {
        if (regRadio.isSelected()){
            if (!baseArea.getText().equals("") && !baseArea.getText().equals("Wrong File")) {
                if (listOfTargets.getValue() != null) {
                        regResult = new Regressive();
                        regResult.loadKnowledgeBaseSet(baseArea.getText());
                    if (!factArea.getText().equals("") && !factArea.getText().equals("Wrong File")) {
                            regResult.loadFactSet(factArea.getText());
                            if (regResult.execute(listOfTargets.getValue().toString())){
                                answerArea.setText("Cel \"" + listOfTargets.getValue() + "\" jest osiągalny :)");
                            }else {
                                answerArea.setText("Cel \"" + listOfTargets.getValue() + "\" jest nieosiągalny :(");
                            }
                    }
                }
            }
        }
    }

    @FXML
    protected void infoClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("popUp.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 300, 150);
            Stage stage = new Stage();
            stage.setTitle("Informacje");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void specClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("popUp2.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 300, 150);
            Stage stage = new Stage();
            stage.setTitle("Specyfikacje");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void radioChecked() {
        if (progRadio.isSelected()) {
            targetCheckBox.setVisible(true);
            targetCheckBox.setSelected(false);
            targetCheckBox.setDisable(false);
            checkBoxChecked();
            if (!baseArea.getText().equals("") && !baseArea.getText().equals("Wrong File")) {
                generateAnswer.setVisible(true);
            }
        } else if (regRadio.isSelected()) {
            listOfTargets.getSelectionModel().clearSelection();
            listOfTargets.getItems().clear();
            targetCheckBox.setVisible(true);
            targetCheckBox.setSelected(true);
            targetCheckBox.setDisable(true);
            generateAnswer.setVisible(false);
            checkBoxChecked();
        } else {
            targetCheckBox.setVisible(false);
            listOfTargets.setVisible(false);
            generateAnswer.setVisible(false);
            checkTarget.setVisible(false);
            checkBoxChecked();
        }
    }

    @FXML
    protected void onMouseClickedLoadDefault(){
        File file2 = new File("PojektZaliczeniowy/Data/G/rules.txt");
        File file = new File("PojektZaliczeniowy/Data/G/facts.txt");
        baseArea.setText(file2.getAbsolutePath());
        loadFacts.setVisible(true);
        factArea.setVisible(true);
        clearFacts.setVisible(true);
        clearVisibilityWhenFactsNull();
        factArea.setText(file.getAbsolutePath());
        progRadio.setVisible(true);
        progRadio.setSelected(false);
        regRadio.setVisible(true);
        regRadio.setSelected(false);
        targetCheckBox.setSelected(false);
        radioChecked();
        checkBoxChecked();

    }

    @FXML
    protected void checkBoxChecked() {
        if (targetCheckBox.isSelected()) {
            if (!baseArea.getText().equals("") && !baseArea.getText().equals("Wrong File")) {
                try {
                    List<String> listTarget = null;
                    if(baseArea.getText().endsWith(".txt")){
                        listTarget = TXTFileReader.getTargetList(baseArea.getText());
                    }else {
                        listTarget = XLSXFileReader.getTargetList(baseArea.getText());
                    }
                    listOfTargets.setItems(FXCollections.observableList(listTarget));
                    listOfTargets.setVisible(true);
                    listOfTargets.setValue(listOfTargets.getItems().get(0));
                    if (regRadio.isSelected()){
                        checkTarget.setVisible(true);
                    }else checkTarget.setVisible(false);
                } catch (IOException e) {
                    listOfTargets.setVisible(false);
                    checkTarget.setVisible(false);
                }
            }
        } else if (!targetCheckBox.isSelected()) {
            listOfTargets.setVisible(false);
            checkTarget.setVisible(false);
            listOfTargets.getSelectionModel().clearSelection();
            listOfTargets.getItems().clear();
        }
    }
}


