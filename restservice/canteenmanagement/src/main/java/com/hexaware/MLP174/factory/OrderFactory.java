package com.hexaware.MLP174.factory;

import com.hexaware.MLP174.persistence.OrderDAO;
import com.hexaware.MLP174.persistence.DbConnection;
import java.util.List;
import com.hexaware.MLP174.model.Menu;
import com.hexaware.MLP174.model.Orders;
import com.hexaware.MLP174.model.Wallet;
import com.hexaware.MLP174.model.WalletType;
import com.hexaware.MLP174.model.OrderStatus;
import java.util.Date;
/**
 * MenuFactory class used to fetch menu data from database.
 * @author hexware
 */
public class OrderFactory {
  /**
   *  Protected constructor.
   */
  protected OrderFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  public static OrderDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(OrderDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static Orders[] showOrder() {
    List<Orders> order = dao().show();
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Customer Orders History.
   * @param custId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showCustomerHistory(final int custId) {
    List<Orders> order = dao().customerHistory(custId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Vendor Orders History.
   * @param vendId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showVendorHistory(final int vendId) {
    List<Orders> order = dao().vendorHistory(vendId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Customer Pending Orders.
   * @param custId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showpendingCustomerOrders(final int custId) {
    List<Orders> order = dao().pendingCustomer(custId);
    return order.toArray(new Orders[order.size()]);
  }
    /**
   * Show Customer Pending Orders.
   * @param vendId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showpendingVendorOrders(final int vendId) {
    List<Orders> order = dao().pendingVendor(vendId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param vendorId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String acceptOrRejectOrder(final int orderId, final int vendorId, final String status) {
    Orders order = dao().findByOrderId(orderId);
    String result = "";
    if (order != null) {
      if (order.getVendorId() == vendorId) {
        if (status.equals("ACCEPTED")) {
          dao().acceptOrReject(status, orderId);
          result = "Order Accepted Successfully...";
        }
        if (status.equals("REJECTED")) {
          dao().acceptOrReject(status, orderId);
          WalletType type = order.getWalletType();
          int custId = order.getCustomerId();
          double billAmount = order.getOrderTotalamount();
          dao().refundAmount(billAmount, type, custId);
          result = "Order Rejected and Amount Refunded...";
        }
      } else {
        result = "You are Unauthorized Vendor for this order...";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }
   /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param custId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String cancelOrder(final int orderId, final int custId, final String status) {
    Orders order = dao().findByOrderId(orderId);
    int customerId = order.getCustomerId();
    OrderStatus ostatus = order.getOrderStatus();
    String result = "";
    if (order != null) {
      if (ostatus == OrderStatus.PENDING) {
        if (customerId == custId) {
          if (status.equals("YES")) {
            String st = "REJECTED";
            dao().acceptOrReject(st, orderId);
            double billAmount = order.getOrderTotalamount();
            WalletType type = order.getWalletType();
            billAmount = billAmount - (billAmount / 10);
            dao().refundAmount(billAmount, type, custId);
            result = ("Order Cancelled Successfully and amount refundued to " + type);
          }
        } else {
          result = "You are unauthorized to cancel this order...";
        }
      } else {
        result = "You cannot cancel this order..";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }
  /**
   * Place order for customer.
   * @param order for accepting order details.
   * @return order status.
   */
  public static String placeOrder(final Orders order) {
    Menu menu = dao().findByMenuId(order.getMenuId());
    Wallet wallet = dao().getWalletInfo(order.getWalletType(), order.getCustomerId());
    System.out.println(wallet.getWalletAmount());
    double walAmount = wallet.getWalletAmount();
    double price = menu.getMenuCost();
    Date today = new Date();
    System.out.println(order.getOrderDate());
    long diffTime = order.getOrderDate().getTime() - today.getTime();
    long diffDays = diffTime / (60 * 60 * 1000 * 24);
    double totalAmount = price * order.getOrderQuantity();
    if (walAmount < totalAmount) {
      return "Insufficient Funds...";
    } else if (diffDays < 0) {
      return "order cannot be placed for previous day";
    } else {
      double diff = walAmount - totalAmount;
      System.out.println("Price is  " + menu.getMenuCost());
      order.setOrderStatus(OrderStatus.PENDING);
      order.setOrderTotalamount(totalAmount);
      dao().placeOrder(order);
      dao().updateBalance(diff, order.getWalletType(), order.getCustomerId());
      return "Order Placed Successfully...";
    }
  }
}

