package triagegrading;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CurrentGradeView extends HBox {
    
    private Label numericalGradeLabel;
    private Label letterGradeLabel;
    public CurrentGradeView() {
        super();
        init();
    }

    private void init() {
        setGradeViewProperties();
        getChildren().add(createNumericalGradeLabel());
        getChildren().add(createLetterGradeLabel());
    }
    
    private void setGradeViewProperties() {
        getStyleClass().add("grade-view");
    }
    
    private Label createNumericalGradeLabel() {
        numericalGradeLabel = new Label("92%");
        numericalGradeLabel.getStyleClass().add("grade-view-label");
        numericalGradeLabel.getStyleClass().add("grade-view-numerical-label");
        return numericalGradeLabel;
    }
    
    private Label createLetterGradeLabel() {
        letterGradeLabel = new Label("A+");
        letterGradeLabel.getStyleClass().add("grade-view-label");
        letterGradeLabel.getStyleClass().add("grade-view-letter-label");
        return letterGradeLabel;
    }
}
