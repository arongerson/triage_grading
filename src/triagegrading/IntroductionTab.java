package triagegrading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class IntroductionTab extends Tab {
    public IntroductionTab() {  
        super("Introduction");
        init();
    }
    
    private void init() {
        setClosable(false);
        setContent(createIntroductionWebView());
    }
    
    private WebView createIntroductionWebView() {
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
    
}
