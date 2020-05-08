import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

public class Products extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	
	private JComboBox combo1;

	private JTextField txtPrice;
	private JTextField txtProductId;
	private JTextField txtOffer;
	/**
	 * @wbp.nonvisual location=63,-6
	 */
	private final JLabel label_1 = new JLabel("New label");
	static ApplicationLayer appLayer;
	public Products() {
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setBounds(22, 13, 56, 16);
		contentPane.add(lblProducts);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(22, 86, 56, 16);
		contentPane.add(lblSearch);
		
		JTextArea txtSearch = new JTextArea();
		txtSearch.setBounds(112, 83, 261, 22);
		contentPane.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		DefaultListModel<String> model = new DefaultListModel<>(); 
		JList<String> lstProducts = new JList<>(model);
		lstProducts.setBounds(22, 174, 450, 219);
		contentPane.add(lstProducts);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSearch.getText() != null) {
					model.clear();

					ArrayList<String> products = appLayer.getProducts(txtSearch.getText());
					for(String product : products) {
						model.addElement(product);
					}
			
				}
			
				else {
					JOptionPane.showMessageDialog(null, "Search Box is empty!"); 
				}
			}
		});
		btnSearch.setBounds(175, 118, 97, 25);
		contentPane.add(btnSearch);
		
		
		
		JButton btnFill = new JButton("List All");
		DefaultListModel<String> model1 = new DefaultListModel<>(); 
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
					ArrayList<String> products = appLayer.getAllProducts();
					for(String product : products) {
						model.addElement(product);
					}
			
				}
			
		});
		btnFill.setBounds(175, 140, 97, 25);
		contentPane.add(btnFill);
		
		
		JLabel label = new JLabel("");
		label.setBounds(132, 148, 56, 16);
		contentPane.add(label);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(140, 417, 97, 16);
		contentPane.add(lblProductPrice);
		
		JLabel lblProductID = new JLabel("Product ID");
		lblProductID.setBounds(51, 439, 91, 16);
		contentPane.add(lblProductID);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(180, 439, 56, 16);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(180, 468, 116, 22);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		txtProductId = new JTextField();
		txtProductId.setBounds(41, 468, 116, 22);
		contentPane.add(txtProductId);
		txtProductId.setColumns(10);
		
		

		JLabel lblOffer = new JLabel("Offer");
		lblOffer.setBounds(350, 439, 56, 16);
		contentPane.add(lblOffer);
		

		
		JComboBox combo1 = new JComboBox();
		combo1.setBounds(350, 468, 116, 22);
		contentPane.add(combo1);
		combo1.addItem("Buy One Get One Free");
		combo1.addItem("Free Shipping");
		combo1.addItem("10% Off");
		combo1.addItem("20% Off");
		combo1.addItem("30% Off");
		combo1.addItem("40% Off");
		combo1.addItem("50% Off");
		combo1.addItem("None");






		
		
		
		JButton btnSetPrice = new JButton("Set Price");
		btnSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtPrice.getText() != "" && txtProductId.getText() != "") {
					appLayer.updatePrice(txtPrice.getText(), txtProductId.getText());
			
					txtPrice.setText(""); 
					txtProductId.setText("");
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "The relevant textboxes are empty!"); 
				}
			}
			
		});
		btnSetPrice.setBounds(175, 508, 97, 25);
		contentPane.add(btnSetPrice);
		
		
		
		
		JButton btnSetOffer = new JButton("Set Offer");
		btnSetOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(combo1.getSelectedItem() != "" && txtProductId.getText() != "") {
					appLayer.updateOffer((String)combo1.getSelectedItem(), txtProductId.getText());
			
					txtProductId.setText("");
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please fill the relevant forms"); 
				}
			}
			
		});
		btnSetOffer.setBounds(175, 540, 97, 25);
		contentPane.add(btnSetOffer);
		
		
	}
}
