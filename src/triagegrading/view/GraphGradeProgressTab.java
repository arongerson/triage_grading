package triagegrading.view;

import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import triagegrading.model.Grade;

class GraphGradeProgressTab extends Tab {

    private CurrentGradeView graphCurrentGradeView;
    private LineChart<Number, Number> lineChart;
    private XYChart.Series gradeSeries;

    public GraphGradeProgressTab() {
        super("Track Grade Progress");
        init();
    }
    
    public void updateLineChartGradeSeries(List<Grade> grades) {
        gradeSeries.getData().clear();
        if (grades != null) {
            grades.forEach((grade) -> {
                gradeSeries.getData().add(
                        new XYChart.Data(grade.getAssignmentNumber(), grade.getNumericalPercentageGrade()));
            });
        }
    }

    public void updateNumericalLetterGrade(String numericalGrade, String letterGrade) {
        graphCurrentGradeView.updateNumericalGrade(numericalGrade);
        graphCurrentGradeView.updateLetterGrade(letterGrade);
    }

    private void init() {
        setClosable(false);
        setContent(createGraphDisplayContent());
    }

    private AnchorPane createGraphDisplayContent() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("tab-anchor-pane");
        anchorPane.getChildren().add(createGradeGraphProgressDisplay());
        anchorPane.getChildren().add(createGraphCurrentGradeView());
        return anchorPane;
    }

    private LineChart<Number, Number> createGradeGraphProgressDisplay() {
        lineChart = new LineChart<>(createLineChartXAxis(), createLineChartYAxis());
        lineChart.setTitle("Track Grade Progress by Graph");
        lineChart.getData().add(makeLineChartSeries());
        createLineChartAnchors();
        return lineChart;
    }

    private NumberAxis createLineChartXAxis() {
        NumberAxis assignmentNumberAxis = new NumberAxis(0, 50, 1);
        assignmentNumberAxis.setLabel("Assignment");
        assignmentNumberAxis.setMinorTickVisible(false);
        return assignmentNumberAxis;
    }

    private NumberAxis createLineChartYAxis() {
        final NumberAxis gradeAxis = new NumberAxis(0, 140, 10);
        gradeAxis.setLabel("Grade (%)");
        return gradeAxis;
    }

    private XYChart.Series makeLineChartSeries() {
        gradeSeries = new XYChart.Series();
        gradeSeries.setName("Grade Progress");
        return gradeSeries;
    }

    private void createLineChartAnchors() {
        AnchorPane.setTopAnchor(lineChart, 0d);
        AnchorPane.setBottomAnchor(lineChart, 0d);
        AnchorPane.setLeftAnchor(lineChart, 0d);
        AnchorPane.setRightAnchor(lineChart, 0d);
    }

    private HBox createGraphCurrentGradeView() {
        graphCurrentGradeView = new CurrentGradeView();
        AnchorPane.setTopAnchor(graphCurrentGradeView, 45d);
        AnchorPane.setRightAnchor(graphCurrentGradeView, 20d);
        return graphCurrentGradeView;
    }
}
