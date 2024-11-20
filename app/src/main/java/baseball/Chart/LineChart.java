package baseball.Chart;
import baseball.Data.Series;
import baseball.Panel.SelectionData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;


import javax.swing.*;
import java.awt.*;
/**
 * The LineChart class contains a JFreeChart and itself is a Jpanel.
 * It provides methods to update the chart's dataset based on the selection data.
*/
public class LineChart extends JPanel {

    private SelectionData Data;
    private JButton menuButton;


    public LineChart() {
        initUI();
    }
 /**
     * Constructor to initialize the LineChart JPanel.
     */
    private void initUI() {
        // Create Menu button
        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> switchToSelectionMenu());
        Series series = new Series();
        // GetLineChartDataset will be called with Data as null here

        JFreeChart chart = createLineChart(null); // Pass null for now, will update later

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 400));

        setLayout(new BorderLayout());
        add(menuButton, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);
    }

    
 /**
     * Creates a JFreeChart with an initial dataset.
     * 
     * @param dataset the dataset for the chart
     * @return the created JFreeChart
     */
    private JFreeChart createLineChart(XYSeriesCollection dataset) {
        if (Data != null) {
            Series series = new Series();
            dataset = series.GetLineChartDataset(Data);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Performance over time",
                "Year",
                (Data != null) ? Data.selectedMetric : "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.yellow);
        plot.setRangeGridlinePaint(Color.GRAY);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
         rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }
 /**
     * Updates the chart
     * 
     * @param data the selection data to update the chart
     */
    public void updateData(SelectionData data) {
        this.Data = data;
        
        if (Data != null) {
            Series series = new Series();
            XYSeriesCollection dataset = series.GetLineChartDataset(Data);
            updateChart(dataset);
        } else {
            System.out.println("Data object is null. Cannot update chart.");
        }
    }
     /**
     * helper to udateData 
     * responsible for updating Chart
     * 
     * @param dataset the new dataset for the chart
     */
    private void updateChart(XYSeriesCollection dataset) {
        if (dataset != null) {
            JFreeChart chart = createLineChart(dataset);
            ChartPanel chartPanel = (ChartPanel) getComponent(1); 
            chartPanel.setChart(chart);
            chartPanel.repaint();  // Refresh the chart panel
        } else {
            System.out.println("Dataset is null. Cannot update chart.");
        }
    }
        /**
     * Switches to the selection menu using CardLayout.
     */
    private void switchToSelectionMenu() {
        ((CardLayout) getParent().getLayout()).show(getParent(), "SelectionMenu");
    }

}

