package triagegrading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class TriageGrading extends Application {

    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 600;
    private TriageView triageView;

    @Override
    public void start(Stage primaryStage) {
        triageView = new TriageView();
        Scene scene = new Scene(triageView, SCENE_WIDTH, SCENE_HEIGHT);
        loadExternalCss(scene);
        primaryStage.setTitle("Triage Grading");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private WebView createView() {
        WebView introductionView = new WebView();
        WebEngine webEngine = introductionView.getEngine();
        try {
            webEngine.load(findMainFilePath());
        } catch (IOException ex) {
            Logger.getLogger(TriageGrading.class.getName()).log(Level.SEVERE, null, ex);
        }
        return introductionView;
    }

    private String findMainFilePath() throws FileNotFoundException, IOException {
        File file = new File("view/triage.html");
        String path = "file:///" + file.getAbsolutePath().replace("\\", "/");
        return path;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void loadExternalCss(Scene scene) {
        File f = new File("view/css/fx-styles.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }

}
