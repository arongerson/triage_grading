package triagegrading.view;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import triagegrading.model.Grade;

public class TriageView extends BorderPane {

    public static final double TITLE_STACK_PANE_HEIGHT = 80;
    public static final double DEFAULT_CONTAINER_SPACING = 10;
    public static final double DEFAULT_ANCHOR_SPACING_FROM_CONTAINER = 10;

    private Label errorLabel;
    private GraphGradeProgressTab graphGradeProgressTab;
    private Button clearScoreButton;
    private Button enterScoreButton;
    private TextField scoreTextField;
    private Label scoreTextLabel;

    public TriageView() {
        super();
        init();
    }
    
    public void setEnterScoreButtonActionListener(EventHandler<ActionEvent> actionEvent) {
        enterScoreButton.setOnAction(actionEvent);
    }
    
    public void setClearScoreButtonActionListener(EventHandler<ActionEvent> actionEvent) {
        clearScoreButton.setOnAction(actionEvent);
    }
    
    public void setEnterScoreTextFieldKeyListener(EventHandler<KeyEvent> keyEvent) {
        scoreTextField.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent);
    }
    
    public void setEnterScoreTextFieldChangeListener(ChangeListener<String> changeListener) {
        scoreTextField.textProperty().addListener(changeListener);
    }

    public String getScoreTextFieldInput() {
        return scoreTextField.getText();
    }
    
    public void updateAssignmentLabel(int assignmentNumber) {
        String label = "Assignment " + assignmentNumber + ":";
        scoreTextLabel.setText(label);
    }
    
    public void updateErrorMessage(String errorMessage) {
        errorLabel.setText(errorMessage);
        errorLabel.setVisible(true);
    }
    
    public void updateGraph(List<Grade> grades) {
        graphGradeProgressTab.updateLineChartGradeSeries(grades);
    }
    
    public void updateNumericalLetterGrade(String numericalGrade, String letterGrade) {
        graphGradeProgressTab.updateNumericalLetterGrade(numericalGrade, letterGrade);
    }

    public void setScoreInputText(String text) {
        scoreTextField.setText(text);
    }

    private void init() {
        createTopView();
        createCenterView();
        createBottomView();
    }

    private void createTopView() {
        StackPane stackPane = new StackPane();
        setTitleStackPaneProperties(stackPane);
        stackPane.getChildren().add(createTitleLabel());
        setTop(stackPane);
    }

    private Label createTitleLabel() {
        Label titleLabel = new Label("Triage Grading");
        titleLabel.setFont(new Font(45));
        titleLabel.getStyleClass().add("title-label");
        return titleLabel;
    }

    private void setTitleStackPaneProperties(StackPane stackPane) {
        stackPane.setPrefHeight(TITLE_STACK_PANE_HEIGHT);
        stackPane.getStyleClass().add("title-stack-pane");
    }

    private void createCenterView() {
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("tabpane");
        tabPane.setPrefHeight(100);
        tabPane.getTabs().add(createGraphDisplayTab());
        setCenter(tabPane);
    }

    private Tab createGraphDisplayTab() {
        graphGradeProgressTab = new GraphGradeProgressTab();
        return graphGradeProgressTab;
    }

    private void createBottomView() {
        VBox vbox = new VBox();
        vbox.setSpacing(DEFAULT_CONTAINER_SPACING);
        vbox.getChildren().add(createTopAnchorPane());
        vbox.getChildren().add(createBottomHBox());
        setBottom(vbox);
    }

    private AnchorPane createTopAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(createInputHBox());
        anchorPane.getChildren().add(createClearButton());
        return anchorPane;
    }

    private HBox createInputHBox() {
        HBox hbox = new HBox();
        hbox.setSpacing(DEFAULT_CONTAINER_SPACING);
        hbox.getStyleClass().add("input-hbox");
        hbox.getChildren().add(createAssignmentNumberLabelStackPane());
        hbox.getChildren().add(createScoreTextField());
        hbox.getChildren().add(createEnterScoreTextButton());
        return hbox;
    }
    
    private StackPane createAssignmentNumberLabelStackPane() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(createAssignmentNumberLabel());
        return stackPane;
    }
    
    private Label createAssignmentNumberLabel() {
        scoreTextLabel = new Label("Assignment 1");
        scoreTextLabel.setFont(new Font(20));
        scoreTextLabel.setLayoutY(scoreTextLabel.getLayoutY() + 100);
        scoreTextLabel.setAlignment(Pos.CENTER_LEFT);
        return scoreTextLabel;
    }

    private TextField createScoreTextField() {
        scoreTextField = new TextField();
        scoreTextField.setPromptText("(0 - 3)"); 
        scoreTextField.setFont(new Font(25));
        scoreTextField.setPrefWidth(100d);
        return scoreTextField;
    }

    private Button createEnterScoreTextButton() {
        enterScoreButton = new Button("Enter");
        enterScoreButton.getStyleClass().add("custom-button");
        enterScoreButton.setFont(new Font(20));
        enterScoreButton.setAlignment(Pos.CENTER_RIGHT);
        return enterScoreButton;
    }

    private Button createClearButton() {
        clearScoreButton = new Button("Clear");
        clearScoreButton.getStyleClass().add("custom-button");
        clearScoreButton.setFont(new Font(20));
        AnchorPane.setTopAnchor(clearScoreButton, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        AnchorPane.setRightAnchor(clearScoreButton, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        return clearScoreButton;
    }

    private HBox createBottomHBox() {
        HBox hbox = new HBox();
        hbox.setVisible(false);
        hbox.setManaged(false); 
        hbox.getChildren().add(createErrorLabelView());
        return hbox;
    }

    private Label createErrorLabelView() {
        errorLabel = new Label("only integers between 0 and 3");
        errorLabel.getStyleClass().add("error-label");
        return errorLabel;
    }
}
