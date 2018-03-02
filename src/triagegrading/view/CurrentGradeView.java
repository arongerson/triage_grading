package triagegrading.view;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CurrentGradeView extends HBox {
    
    private Label numericalGradeLabel;
    private Label letterGradeLabel;
    
    public CurrentGradeView() {
        super();
        init();
    }
    
    public void updateNumericalGrade(String numericalGrade) {
        numericalGradeLabel.setText(numericalGrade);
    }
    
    public void updateLetterGrade(String letterGrade) {
        letterGradeLabel.setText(letterGrade);
    }

    private void init() {
        createAndSetDropShadowEffect();
        setGradeViewProperties();
        getChildren().add(createNumericalGradeLabel());
        getChildren().add(createLetterGradeLabel());
    }
    
    private void createAndSetDropShadowEffect() {
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetY(4.0f);
        dropShadowEffect.setOffsetX(4.0f);
        dropShadowEffect.setColor(Color.GRAY);
        setEffect(dropShadowEffect);
    }
    
    private void setGradeViewProperties() {
        getStyleClass().add("grade-view");
    }
    
    private Label createNumericalGradeLabel() {
        numericalGradeLabel = new Label("0%");
        numericalGradeLabel.getStyleClass().add("grade-view-label");
        numericalGradeLabel.getStyleClass().add("grade-view-numerical-label");
        return numericalGradeLabel;
    }
    
    private Label createLetterGradeLabel() {
        letterGradeLabel = new Label("F");
        letterGradeLabel.getStyleClass().add("grade-view-label");
        letterGradeLabel.getStyleClass().add("grade-view-letter-label");
        return letterGradeLabel;
    }
}
