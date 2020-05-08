import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmailAddress;

	/**
	 * Launch the application.
	 */
	
	static ApplicationLayer appLayer;
	private JPasswordField passwordField;
	/**
	 * Create the frame.
	 */
	public Register() {
		
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(186, 10, 56, 16);
		contentPane.add(lblRegister);
		
		JLabel lblUsername = new JLabel(" Username: ");
		lblUsername.setBounds(12, 42, 91, 16);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(105, 39, 244, 22);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(12, 71, 76, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(12, 100, 91, 16);
		contentPane.add(lblEmailAddress);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setBounds(105, 97, 249, 22);
		contentPane.add(txtEmailAddress);
		txtEmailAddress.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		JRadioButton rdbtnStoreManager = new JRadioButton("Store Manager");
		rdbtnStoreManager.setBounds(105, 125, 127, 25);
		contentPane.add(rdbtnStoreManager);
		
		JRadioButton rdbtnNormalEmployee = new JRadioButton("Normal Employee");
		rdbtnNormalEmployee.setBounds(236, 125, 146, 25);
		contentPane.add(rdbtnNormalEmployee);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnStoreManager);
		group.add(rdbtnNormalEmployee);
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 71, 244, 22);
		contentPane.add(passwordField);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnStoreManager.isSelected()) {
					boolean emailAddressValidation = dataLayer.validateEmailAddress(txtEmailAddress.getText());
					boolean usernameValidation = dataLayer.validateUsername(txtUsername.getText());
					if(emailAddressValidation == true && usernameValidation == true) {
						appLayer.registerStoreManager(passwordField.getText(),txtUsername.getText(), txtEmailAddress.getText());
						JOptionPane.showMessageDialog(null, "You are now registered");
						Login login = new Login();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Username or Email Address taken.");
					}
				}
				else if(rdbtnNormalEmployee.isSelected()) {
					boolean emailAddressValidation = dataLayer.validateEmailAddress2(txtEmailAddress.getText());
					boolean usernameValidation = dataLayer.validateUsername2(txtUsername.getText());
					if(emailAddressValidation == true && usernameValidation == true) {
						appLayer.registerNormalEmployee(passwordField.getText(),txtUsername.getText(), txtEmailAddress.getText());
						JOptionPane.showMessageDialog(null, "You are now registered");
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Username or Email Address taken");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please choose your role");
				}
			}
		});
		btnRegister.setBounds(168, 156, 97, 25);
		contentPane.add(btnRegister);
		
		
		
		
	}
}
