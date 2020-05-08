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
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customers extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */

	//Connection conn = null;
	private JTextField txtPhoneNumber;
	private JTextField txtLoyaltyCard;
	private JTextField txtPhoneNumber2;
	private JTextField txtFinanceApproval;
	private JComboBox combo1;
	static ApplicationLayer appLayer;
	
	/**
	 * Create the frame.
	 */
	public Customers() {
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomers = new JLabel("Customers ");
		lblCustomers.setBounds(0, 0, 103, 16);
		contentPane.add(lblCustomers);
		DefaultListModel<String> model = new DefaultListModel<>(); 
		
		JList<String> lstCustomers = new JList<>(model);
		lstCustomers.setBounds(12, 47, 793, 226);
		contentPane.add(lstCustomers);
		
		JLabel lblLoyaltyCard = new JLabel("Loyalty Card");
		lblLoyaltyCard.setBounds(212, 325, 123, 16);
		contentPane.add(lblLoyaltyCard);
		
		JLabel lblPhoneNumber = new JLabel("Customer ID");
		lblPhoneNumber.setBounds(68, 338, 112, 16);
		contentPane.add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(32, 361, 169, 22);
		contentPane.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblGiveLoyaltyCard = new JLabel("Give Loyalty Card");
		lblGiveLoyaltyCard.setBounds(309, 338, 116, 16);
		contentPane.add(lblGiveLoyaltyCard);
		
		JComboBox combo1 = new JComboBox();
		combo1.setBounds(295, 361, 170, 22);
		contentPane.add(combo1);
		combo1.addItem("True");
		combo1.addItem("False");
		
		JButton btnLoyaltyCard = new JButton("Submit Loyalty Card");
		btnLoyaltyCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtPhoneNumber.getText() != null && combo1.getSelectedItem()!= null) {
					try {
						boolean flag = appLayer.addLoyaltyCard(Boolean.parseBoolean((String)combo1.getSelectedItem()), Integer.parseInt(txtPhoneNumber.getText()));
						if(flag == true) {
						}
						else {
							JOptionPane.showMessageDialog(null, "Customer does not exist or customer is not loyal enough!");
						}
						txtPhoneNumber.setText("");
					}
					catch(Exception pe) {
						JOptionPane.showMessageDialog(null, pe); 
					}
				}else {
					JOptionPane.showMessageDialog(null, "The relevant textboxes are empty!"); 
				}
				
			}
		});
		btnLoyaltyCard.setBounds(178, 396, 157, 25);
		contentPane.add(btnLoyaltyCard);
		
		
		JButton btnFill = new JButton("List All");
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
					ArrayList<String> customers = appLayer.getAllCustomers();
					for(String customer : customers) {
						model.addElement(customer);
					}
			
				}
			
		});
		btnFill.setBounds(190, 309, 123, 16);
		contentPane.add(btnFill);
		
		
		JLabel lblFinanceApproval = new JLabel("Finance Approval");
		lblFinanceApproval.setBounds(212, 445, 102, 16);
		contentPane.add(lblFinanceApproval);
		
		JLabel lblPhoneNumber2 = new JLabel("Customer ID");
		lblPhoneNumber2.setBounds(50, 472, 112, 16);
		contentPane.add(lblPhoneNumber2);
		
		txtPhoneNumber2 = new JTextField();
		txtPhoneNumber2.setBounds(32, 499, 169, 22);
		contentPane.add(txtPhoneNumber2);
		txtPhoneNumber2.setColumns(10);
		
		JLabel lblGiveFinanceApproval = new JLabel("Approve Finance");
		lblGiveFinanceApproval.setBounds(314, 472, 169, 16);
		contentPane.add(lblGiveFinanceApproval);
		
		
		
		JComboBox combo2 = new JComboBox();
		combo2.setBounds(296, 499, 169, 22);
		contentPane.add(combo2);
		combo2.addItem("True");
		combo2.addItem("False");
		
		JButton btnFinanceApproval = new JButton("Submit Finance Approval");
		btnFinanceApproval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPhoneNumber2.getText() != null && combo2.getSelectedItem()!= null ) {
					try {
						boolean flag = appLayer.giveFinancialApproval(Boolean.parseBoolean((String)combo2.getSelectedItem()), Integer.parseInt(txtPhoneNumber2.getText()));
						if(flag == true) {
						}
						else {
							JOptionPane.showMessageDialog(null, "Insufficient credit.");
						}
						txtPhoneNumber2.setText("");
					}
					catch(Exception pe) {
						JOptionPane.showMessageDialog(null, pe); 
					}
				}else {
					JOptionPane.showMessageDialog(null, "Fill text boxes and try again."); 
				}
			}
		});
		btnFinanceApproval.setBounds(154, 534, 209, 25);
		contentPane.add(btnFinanceApproval);
		
		
	}
		
		

}
