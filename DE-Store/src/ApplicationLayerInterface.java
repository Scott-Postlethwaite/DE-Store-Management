import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.Session;

import org.jfree.data.category.DefaultCategoryDataset;

public interface ApplicationLayerInterface {
	public boolean loginStoreManager(String username, String password);
	public boolean loginNormalEmployee(String username, String password);
	public ArrayList<String> getProducts(String sale_offer);
	public ArrayList<String> getAllProducts();
	public ArrayList<String> getAllCustomers();
	public void updatePrice(String price, String product_id);
	public void updateOffer(String offer, String product_id);
	public boolean addLoyaltyCard(boolean loyaltyCard, int CustomerID);
	public boolean giveFinancialApproval(Boolean financeApproval, int customerID);
	public void setPurchaseInformation();
	public ArrayList<String> getPurchaseInformation();
	public void sendEmail();
	public ArrayList<String> monitorStock(); 
	public DefaultCategoryDataset getReport();
	public void registerStoreManager(String password, String username, String email_address);
	public void registerNormalEmployee(String password, String username, String email_address);
	public boolean validateUsername2(String username);
	public boolean validateEmailAddress2(String emailAddress); 
}
