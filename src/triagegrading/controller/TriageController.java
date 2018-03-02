package triagegrading.controller;

import triagegrading.utils.SoundFeedback;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import triagegrading.model.Grade;
import triagegrading.model.TriageModel;
import triagegrading.view.TriageView;

public class TriageController {

    private TriageView triageView;
    private final TriageModel triageModel;

    public TriageController() {
        triageModel = new TriageModel();
    }

    public void init(TriageView triageView) {
        this.triageView = triageView;
        initModel();
        initListeners();
        updateAssignmentNumberLabel();
        updateGraph();
    }

    private void initModel() {
        try {
            triageModel.init();
        } catch (IOException ex) {
            triageView.updateErrorMessage("the saved data is corrupted, "
                    + " to delete.");
        } catch (ClassNotFoundException ex) {
            triageView.updateErrorMessage("the saved data is corrupted, clear to delete.");
        }
    }

    private void initListeners() {
        triageView.setEnterScoreButtonActionListener((ActionEvent event) -> {
            saveNewAssignment();
        });
        triageView.setClearScoreButtonActionListener((ActionEvent event) -> {
            confirmClearAssignment();
        });
        triageView.setEnterScoreTextFieldKeyListener((KeyEvent event) -> {
            saveIfEnterPressed(event);
        });
        triageView.setEnterScoreTextFieldChangeListener((
                ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            checkInputValidity(oldValue, newValue);
        });
    }

    private void saveNewAssignment() {
        try {
            int score = Integer.parseInt(triageView.getScoreTextFieldInput());
            saveNewAssignment(score);
        } catch (NumberFormatException exception) {
            Logger.getLogger(TriageController.class.getName()).log(
                    Level.SEVERE, "Error saving new assignment", exception);
        }
    }

    private void saveNewAssignment(int score) {
        triageModel.saveAssignment(score);
        SoundFeedback.playEnteredSound();
        updateGraph();
        updateAssignmentNumberLabel();
        saveInFile();
    }

    private void saveInFile() {
        try {
            triageModel.saveToFile();
        } catch (IOException ex) {
            triageView.updateErrorMessage("The saved data is corrupted. Clear to delete.");
        }
    }

    private void updateGraph() {
        List<Grade> grades = triageModel.getGrades();
        triageView.updateGraph(grades);
        triageView.updateNumericalLetterGrade(formatNumericalGrade(), triageModel.getCurrentLetterGrade());
    }

    private String formatNumericalGrade() {
        double grade = triageModel.getCurrentNumericalGrade();
        if (!triageModel.hasGradeAdded()) {
            return "0%";
        }
        DecimalFormat df2 = new DecimalFormat(".##");
        return df2.format(grade) + "%";
    }

    private void confirmClearAssignment() {
        Alert clearDataConfirmationDialog = createConfimationDialog();
        Optional<ButtonType> result = clearDataConfirmationDialog.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            clearAssignments();
        }
    }

    private Alert createConfimationDialog() {
        Alert clearDataConfirmationDialog = new Alert(AlertType.CONFIRMATION);
        clearDataConfirmationDialog.setTitle("Confrimation");
        String message = "Are you sure you want to permanently delete all saved assignments?";
        clearDataConfirmationDialog.setContentText(message);
        return clearDataConfirmationDialog;
    }

    private void clearAssignments() {
        triageModel.clearData();
        updateGraph();
        updateAssignmentNumberLabel();
    }

    private void checkInputValidity(String oldValue, String newValue) {
        if (!newValue.isEmpty()) {
            String enteredKey = extractEnteredKey(newValue);
            processInput(enteredKey, oldValue);
        }
    }

    private void processInput(String enteredKey, String oldValue) {
        if (TriageModel.isInputLengthValid(enteredKey)) {
            triageView.setScoreInputText(enteredKey);
        } else {
            SoundFeedback.playErrorSound();
            triageView.setScoreInputText(oldValue);
        }
    }
    private String extractEnteredKey(String newValue) {
        String enteredKey = newValue.substring(newValue.length() - 1);
        return enteredKey;
    }

    private void saveIfEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            saveNewAssignment();
        }
    }

    private void updateAssignmentNumberLabel() {
        triageView.updateAssignmentLabel(triageModel.getNextAssignmentNumber());
    }
}
