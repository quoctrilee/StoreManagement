package View;

import Controller.StatisticalController;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class StatisticalView extends JPanel {
    private static final long serialVersionUID = 1L;
    private StatisticalController statisticalController;
    private ChartPanel chartPanel;

    public StatisticalView() {
        statisticalController = new StatisticalController();
        setLayout(new BorderLayout());
        JFreeChart lineChart = createLineChart();
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 400)); // Set preferred size
        add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createLineChart() {
        TimeSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Total Sales by Date",
            "Date",
            "Total Amount",
            dataset,
            false, // no legend
            true,
            false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        // Format the date axis to only show day
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));

        return chart;
    }

    private TimeSeriesCollection createDataset() {
        TimeSeries series = new TimeSeries("Sales");

        Map<Date, Float> totalAmountByDate = statisticalController.getTotalAmountByDate();
        for (Map.Entry<Date, Float> entry : totalAmountByDate.entrySet()) {
            series.add(new Day(entry.getKey()), entry.getValue());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public void updateChart() {
        JFreeChart chart = createLineChart();
        chartPanel.setChart(chart);
    }
}
