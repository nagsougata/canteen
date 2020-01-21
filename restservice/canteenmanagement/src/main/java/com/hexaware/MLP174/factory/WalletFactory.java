package com.hexaware.MLP174.factory;

import com.hexaware.MLP174.persistence.WalletDAO;
import com.hexaware.MLP174.persistence.DbConnection;
import java.util.List;

import com.hexaware.MLP174.model.Wallet;
/**
 * MenuFactory class used to fetch menu data from database.
 * @author hexware
 */
public class WalletFactory {
  /**
   *  Protected constructor.
   */
  protected WalletFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  private static WalletDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(WalletDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static Wallet[] showWallet() {
    List<Wallet> wallet = dao().show();
    return wallet.toArray(new Wallet[wallet.size()]);
  }
}
