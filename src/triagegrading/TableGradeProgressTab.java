package triagegrading;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

class TableGradeProgressTab extends Tab {
    
    private CurrentGradeView tableCurrentGradeView;
    private TableView<String> tabularGradeView;
    
    public TableGradeProgressTab() {
        super("Table");
        init();
    }

    private void init() {
        setClosable(false);
        setContent(createTableDispayAnchorPane());
    }
    
    private AnchorPane createTableDispayAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("tab-anchor-pane");
        anchorPane.getChildren().add(createTableDisplayVBox());
        anchorPane.getChildren().add(createTableCurrentGradeView());
        return anchorPane;
    }
    
    private VBox createTableDisplayVBox() {
        VBox vbox = new VBox();
        vbox.setSpacing(TriageView.DEFAULT_CONTAINER_SPACING);
        vbox.getChildren().add(createTableDisplayTitleLabel());
        vbox.getChildren().add(createTableView());
        return vbox;
    }
    
    private Label createTableDisplayTitleLabel() {
        Label tableTitleLabel = new Label("Tabular Track of Grade Progress");
        return tableTitleLabel;
    }
    
    private TableView createTableView() {
        tabularGradeView = new TableView<>();
        tabularGradeView.setEditable(false);
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        tabularGradeView.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        return tabularGradeView;
    }
    
    private CurrentGradeView createTableCurrentGradeView() {
        tableCurrentGradeView = new CurrentGradeView();
        AnchorPane.setTopAnchor(tableCurrentGradeView, 45d);
        AnchorPane.setRightAnchor(tableCurrentGradeView, 20d);
        return tableCurrentGradeView;
    }
}
