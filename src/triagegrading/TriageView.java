package triagegrading;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TriageView extends BorderPane {

    public static final double TITLE_STACK_PANE_HEIGHT = 80;
    public static final double DEFAULT_CONTAINER_SPACING = 10;
    public static final double DEFAULT_ANCHOR_SPACING_FROM_CONTAINER = 10;

    private Label errorLabel;
    private IntroductionTab introductionTab;
    private GraphGradeProgressTab graphGradeProgressTab;
    private TableGradeProgressTab tableGradeProgressTab;

    public TriageView() {
        super();
        init();
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
        tabPane.getTabs().add(createIntroductionTab());
        tabPane.getTabs().add(createGraphDisplayTab());
        tabPane.getTabs().add(createTableDisplayTab());
        setCenter(tabPane);
    }

    private Tab createIntroductionTab() {
        introductionTab = new IntroductionTab();
        return introductionTab;
    }

    private Tab createGraphDisplayTab() {
        graphGradeProgressTab = new GraphGradeProgressTab();
        return graphGradeProgressTab;
    }

    private Tab createTableDisplayTab() {
        tableGradeProgressTab = new TableGradeProgressTab();
        return tableGradeProgressTab;
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
        AnchorPane.setTopAnchor(hbox, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        AnchorPane.setLeftAnchor(hbox, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        hbox.getChildren().add(createScoreTextField());
        hbox.getChildren().add(createEnterScoreTextButton());
        return hbox;
    }

    private TextField createScoreTextField() {
        TextField scoreTextField = new TextField();
        return scoreTextField;
    }

    private Button createEnterScoreTextButton() {
        Button enterScoreButton = new Button("Enter");
        return enterScoreButton;
    }

    private Button createClearButton() {
        Button clearScoreButton = new Button("Clear");
        AnchorPane.setTopAnchor(clearScoreButton, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        AnchorPane.setRightAnchor(clearScoreButton, DEFAULT_ANCHOR_SPACING_FROM_CONTAINER);
        return clearScoreButton;
    }

    private HBox createBottomHBox() {
        HBox hbox = new HBox();
        hbox.getChildren().add(createErrorLabelView());
        return hbox;
    }

    private Label createErrorLabelView() {
        errorLabel = new Label();
        return errorLabel;
    }
}
