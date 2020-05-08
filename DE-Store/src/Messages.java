import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Messages extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	static ApplicationLayer appLayer;
	/**
	 * Create the frame.
	 */
	public Messages() {
		
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(227, 38, 56, 16);
		contentPane.add(lblStock);
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> lstStock = new JList(model);
		lstStock.setBounds(22, 67, 457, 200);
		contentPane.add(lstStock);
		
		JButton btnMonitorStock = new JButton("Monitor Stock");
		btnMonitorStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> products = appLayer.monitorStock(); 
				for(String product : products) {
					model.addElement(product);
				}
				appLayer.sendEmail();
			}
		});
		btnMonitorStock.setBounds(183, 290, 155, 25);
		contentPane.add(btnMonitorStock);
		
		
		
	}
}
