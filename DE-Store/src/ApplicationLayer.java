import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSession;
import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

public class ApplicationLayer implements ApplicationLayerInterface {
	private DataLayerInterface dataLayer;
	public ApplicationLayer(DataLayerInterface dataLayer)
	{
		this.dataLayer = dataLayer;
	}
	
	public boolean loginStoreManager(String username, String password) {
		return dataLayer.loginStoreManager(username, password);
	}
	
	public boolean loginNormalEmployee(String username, String password) {
		return dataLayer.loginNormalEmployee(username, password);
		
	}
	
	public ArrayList<String> getProducts(String sale_offer){
		ArrayList<String> products = dataLayer.getProducts(sale_offer);
		return products; 
	}
	
	public ArrayList<String> getAllProducts(){
		ArrayList<String> products = dataLayer.getAllProducts();
		return products; 
	}
	
	
	public ArrayList<String> getAllCustomers(){
		ArrayList<String> customers = dataLayer.getAllCustomers();
		return customers; 
	}
	
	
	
	public void updatePrice(String price, String product_id) {
		dataLayer.updatePrice(price, product_id);
	}
	
	public void updateOffer(String offer, String product_id) {
		dataLayer.updateOffer(offer, product_id);
	}
	
	public boolean addLoyaltyCard(boolean loyaltyCard, int CustomerID) {
		return dataLayer.addLoyaltyCard(loyaltyCard, CustomerID);
	}
	public boolean giveFinancialApproval(Boolean financeApproval, int customerID) {
		return dataLayer.giveFinancialApproval(financeApproval, customerID);
	}
	
	public void setPurchaseInformation() {
		dataLayer.setPurchaseInformation();
		
	}
	
	public ArrayList<String> getPurchaseInformation() {
		return dataLayer.getPurchaseInformation();
	}
	
	public void sendEmail() {
		ArrayList<String> emailaddreses = dataLayer.sendEmailAddresses();
		ArrayList<String> products = dataLayer.monitorStock();
		
		for(String email_address : emailaddreses) {
			for(int i = 2; i < products.size(); i = i + 3) {
				final String username = "de.store.stock.management@gmail.com";
		        final String password = "Set10101";

		        Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
		        prop.put("mail.smtp.port", "465");
		        prop.put("mail.smtp.auth", "true");
		        prop.put("mail.smtp.socketFactory.port", "465");
		        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        
		        Session session = Session.getInstance(prop,
		                new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(username, password);
		                    }
		                });

		        try {

		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress("de.store.stock.management@gmail.com"));
		            message.setRecipients(
		                    Message.RecipientType.TO,
		                    InternetAddress.parse(email_address)
		            );
		            message.setSubject("Stock Warning");
		            message.setText("Stock Warning: We only have " + products.get(i) + " " + products.get(i-1) + "(" + products.get(i-2) + ") " + " left in stock!");

		            Transport.send(message);

		            System.out.println("Done");

		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
			}
		}
		
	}
	
	public ArrayList<String> monitorStock() {
		return dataLayer.monitorStock2();
	}
	
	public DefaultCategoryDataset getReport(){
		return dataLayer.getReport();
}
	
	public void registerStoreManager(String password, String username, String Email) {
		dataLayer.registerStoreManager(password, username, Email);
	}
	
	public boolean validateUsername(String username) {
		return dataLayer.validateUsername(username);
	}
	
	public boolean validateEmailAddress(String emailAddress) {
		return dataLayer.validateEmailAddress(emailAddress);
	}
	
	public void registerNormalEmployee(String password, String username, String email_address) {
		dataLayer.registerNormalEmployee(password, username, email_address);
	}
	
	public boolean validateUsername2(String username) {
		return dataLayer.validateUsername2(username);
	}
	
	public boolean validateEmailAddress2(String emailAddress) {
		return dataLayer.validateEmailAddress2(emailAddress);
	}

	
	
}
