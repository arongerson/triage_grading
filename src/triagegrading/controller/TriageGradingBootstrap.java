package triagegrading.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import triagegrading.view.TriageView;

public class TriageGradingBootstrap extends Application {

    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 600;
    private TriageView triageView;
    TriageController controller = new TriageController();

    @Override
    public void start(Stage primaryStage) {
        triageView = new TriageView();
        Scene scene = new Scene(triageView, SCENE_WIDTH, SCENE_HEIGHT);
        loadExternalCss(scene);
        primaryStage.setTitle("Triage Grading");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.init(triageView);
    }

    private void loadExternalCss(Scene scene) {
        File f = new File("src/fx-styles.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }

}
