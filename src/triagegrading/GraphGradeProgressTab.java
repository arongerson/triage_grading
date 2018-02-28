package triagegrading;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

class GraphGradeProgressTab extends Tab {
    
    private CurrentGradeView graphCurrentGradeView;
    
    public GraphGradeProgressTab() {
        super("Graph");
        init();
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
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart
                = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series);
        AnchorPane.setTopAnchor(lineChart, 0d);
        AnchorPane.setBottomAnchor(lineChart, 0d);
        AnchorPane.setLeftAnchor(lineChart, 0d);
        AnchorPane.setRightAnchor(lineChart, 0d);
        return lineChart;
    }

    private HBox createGraphCurrentGradeView() {
        graphCurrentGradeView = new CurrentGradeView();
        AnchorPane.setTopAnchor(graphCurrentGradeView, 45d);
        AnchorPane.setRightAnchor(graphCurrentGradeView, 20d);
        return graphCurrentGradeView;
    }
}
