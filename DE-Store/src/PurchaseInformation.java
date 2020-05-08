import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


public class PurchaseInformation extends JFrame {

	private JPanel contentPane;
	static ApplicationLayer appLayer;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PurchaseInformation() {
		
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPurchaseInformation = new JLabel("Purchase Information");
		lblPurchaseInformation.setBounds(0, 0, 191, 16);
		contentPane.add(lblPurchaseInformation);
		DefaultListModel<String> model = new DefaultListModel<>(); 
		JList<String> lstPurchaseInfomation = new JList<>(model);
		lstPurchaseInfomation.setBounds(22, 20, 480, 219);
		contentPane.add(lstPurchaseInfomation);
		
		JButton btnPurchaseInformation = new JButton("Purchase Information");
		btnPurchaseInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
				
				ArrayList<String> products = appLayer.getPurchaseInformation();
				for(String product : products) {
					model.addElement(product);
				}
			}
		});
		btnPurchaseInformation.setBounds(200, 308, 172, 25);
		contentPane.add(btnPurchaseInformation);
		
		JButton btnProduceReport = new JButton("Produce Report");
		btnProduceReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Report report = new Report("Report", "Summary of Company Performance");
				report.pack(); 
				report.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				report.setVisible(true);
				//model2.clear(); 
				//ArrayList<Integer> report2 = appLayer.produceReport();
				//for(int content : report2) {
					//model2.addElement(Integer.toString(content));
				//}
				
				
			}
		});
		btnProduceReport.setBounds(200, 360, 172, 25);
		contentPane.add(btnProduceReport);
		
		
	}
}
