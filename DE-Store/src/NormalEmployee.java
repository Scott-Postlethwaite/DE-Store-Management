import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NormalEmployee extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalEmployee frame = new NormalEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NormalEmployee() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNormalEmployee = new JLabel("Employee Menu");
		lblNormalEmployee.setBounds(26, 13, 158, 16);
		contentPane.add(lblNormalEmployee);
		
		JButton btnCustomers = new JButton("Manage Customers");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Customers customers = new Customers();
				customers.setVisible(true);
			}
		});
		btnCustomers.setBounds(130, 50,180, 30);
		contentPane.add(btnCustomers);
		
		JButton btnMessages = new JButton("Check Stock");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreManagerGUI sm = new StoreManagerGUI(); 
				sm.setVisible(false); 
				Messages messages = new Messages();
				messages.setVisible(true);
			}
		});
		btnMessages.setBounds(130, 100,180, 30);
		contentPane.add(btnMessages);
		
		JButton btnPurchaseInformation = new JButton("Purchase Information");
		btnPurchaseInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PurchaseInformation pi = new PurchaseInformation();
				pi.setVisible(true);
			}
		});
		btnPurchaseInformation.setBounds(130, 150,180, 30);
		contentPane.add(btnPurchaseInformation);
	}

}
