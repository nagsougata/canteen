package com.hexaware.MLP174.factory;

import com.hexaware.MLP174.persistence.VendorDAO;
import com.hexaware.MLP174.persistence.DbConnection;
import java.util.List;
import com.hexaware.MLP174.model.Vendor;
/**
 * MenuFactory class used to fetch menu data from database.
 * @author hexware
 */
public class VendorFactory {
  /**
   *  Protected constructor.
   */
  protected VendorFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  private static VendorDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(VendorDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static Vendor[] showVendor() {
    List<Vendor> vendor = dao().show();
    return vendor.toArray(new Vendor[vendor.size()]);
  }
  /**
  * retrieve vendor information.
  * @return the array of menu object.
  * @param vendorName to accept vendor name.
  */
  public static Vendor findByVendorName(final String vendorName) {
    Vendor vendor = dao().findByVendorName(vendorName);
    return vendor;
  }
  /**
  * Call authenticate method for validate user.
  * @return the count.
  * @param user to initialize vendor list.
  * @param password to initialize vendor password.
  */
  public static int validateVendor(final String user, final String password) {
    int count = dao().authenticate(user, password);
    return count;
  }
}
