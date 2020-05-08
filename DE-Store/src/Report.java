import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Report extends JFrame {
	private DataLayer dataLayer = new DataLayer();
	private ApplicationLayer appLayer = new ApplicationLayer(dataLayer);
	public Report(String appTitle, String chartTitle) {
		CategoryDataset dataset = createDataset(); 
		JFreeChart chart = createChart(dataset, chartTitle); 
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300)); 
		setContentPane(chartPanel); 
	}
	
	private CategoryDataset createDataset() {
		DefaultCategoryDataset  report= appLayer.getReport();
		return report; 
	}
	
	private JFreeChart createChart(CategoryDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createBarChart(title, "Product","Profit", dataset); 
		 ChartPanel chartPanel = new ChartPanel( chart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel ); 
		return chart; 
	}
}
