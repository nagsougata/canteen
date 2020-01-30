package com.hexaware.MLP174.factory;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.hexaware.MLP174.model.Menu;
import com.hexaware.MLP174.model.OrderStatus;
import com.hexaware.MLP174.model.Orders;
import com.hexaware.MLP174.model.WalletType;
import com.hexaware.MLP174.persistence.OrderDAO;
import com.hexaware.MLP174.model.MenuCat;
import com.hexaware.MLP174.model.Wallet;


import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
/**
 * Test class for order.
 */
@RunWith(JMockit.class)
public class OrderFactoryTest {
  /**
   * tests for constructor.
   */
  @Test
  public final void testConstructor() {
    assertNotNull(new OrderFactory());
    assertNotNull(OrderFactory.dao());
  }
  /**
   * tests that empty order list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllEmpty(@Mocked final OrderDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Orders>();
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] o = OrderFactory.showOrder();
    assertEquals(0, o.length);
  }
   /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException for date format validation.
   */
  @Test
   public final void testListAllSome(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-04-28");
    Date odt = sdf.parse(dt);
    final Orders o2 = new Orders(1, 3, 5, 101, OrderStatus.ACCEPTED, "SWEET", 260.00000, odt, 4, WalletType.PAYTM);
    final Orders o3 = new Orders(2, 4, 6, 102, OrderStatus.REJECTED, "SPICY", 291.00000, odt, 5, WalletType.CREDIT_CARD);

    final ArrayList<Orders> ven = new ArrayList<Orders>();
    new Expectations() {
      {
        ven.add(o2);
        ven.add(o3);
        dao.show(); result = ven;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    // (1, 100, 4, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM);
    // (2, 101, 5, 2, OrderStatus.REJECTED, "SPICY", 201.00000, odt, 5, WalletType.GOOGLE_PAY);

    Orders[] ord1 = OrderFactory.showOrder();
    assertEquals(2, ord1.length);
    assertEquals(1, ord1[0].getOrderId());
    assertEquals(2, ord1[1].getOrderId());
    assertEquals(3, ord1[0].getCustomerId());
    assertEquals(4, ord1[1].getCustomerId());
    assertEquals(5, ord1[0].getVendorId());
    assertEquals(6, ord1[1].getVendorId());
    assertEquals(101, ord1[0].getMenuId());
    assertEquals(102, ord1[1].getMenuId());
    assertEquals(OrderStatus.ACCEPTED, ord1[0].getOrderStatus());
    assertEquals(OrderStatus.REJECTED, ord1[1].getOrderStatus());
    assertEquals("SWEET", ord1[0].getOrderComments());
    assertEquals("SPICY", ord1[1].getOrderComments());
    assertEquals(260.00000, ord1[0].getOrderTotalamount(), 0);
    assertEquals(291.00000, ord1[1].getOrderTotalamount(), 0);
    assertEquals(odt, ord1[0].getOrderDate());
    assertEquals(odt, ord1[1].getOrderDate());
    assertEquals(4, ord1[0].getOrderQuantity());
    assertEquals(5, ord1[1].getOrderQuantity());
    assertEquals(WalletType.PAYTM, ord1[0].getWalletType());
    assertEquals(WalletType.CREDIT_CARD, ord1[1].getWalletType());
    // assertEquals("9999977654", ord1[0].getOrderNumber());
    // assertEquals("9988877654", ord1[1].getOrderNumber());
    // assertEquals("XYZ@GMAIL.COM", ord1[0].getOrderEmail());
    // assertEquals("RKP@GMAIL.COM", ord1[1].getOrderEmail());
    // assertEquals("512", ord1[0].getOrderPassword());
    // assertEquals("507", ord1[1].getOrderPassword());
  }
     /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testAcceptOrReject(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-04-28");
    final Date odt = sdf.parse(dt);
    final Orders order1 = new Orders(1, 3, 5, 101, OrderStatus.ACCEPTED, "SWEET", 260.00000, odt, 4, WalletType.PAYTM);
    new Expectations() {
        {
          dao.findByOrderId(1); result = order1;
          dao.findByOrderId(2); result = null;
          dao.acceptOrReject("ACCEPTED", 1);
          dao.acceptOrReject("REJECTED", 1);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result1 = OrderFactory.acceptOrRejectOrder(1, 5, "ACCEPTED");
    assertEquals(result1, "Order Accepted Successfully...");
    String result2 = OrderFactory.acceptOrRejectOrder(1, 5, "REJECTED");
    assertEquals(result2, "Order Rejected and Amount Refunded...");
    String result3 = OrderFactory.acceptOrRejectOrder(1, 6, "REJECTED");
    assertEquals(result3, "You are Unauthorized Vendor for this order...");
    String result4 = OrderFactory.acceptOrRejectOrder(2, 5, "REJECTED");
    assertEquals(result4, "Invalid OrderId...");
  }
   /**
  * @param dao for mocking cancleorder Mock Test.
  * @throws ParseException for converting date to string.
   */
  @Test
  public final void testcancelOrder(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-04-28");
    final Date odt = sdf.parse(dt);
    final Orders order2 = new Orders(1, 3, 5, 101, OrderStatus.PENDING, "SWEET", 260.00000, odt, 4, WalletType.PAYTM);
    final Orders order3 = new Orders(2, 3, 5, 101, OrderStatus.ACCEPTED, "SWEET", 260.00000, odt, 4, WalletType.PAYTM);
    //final Orders order3 = new Orders(2, 4, 100, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM);
    new Expectations() {
        {
          dao.findByOrderId(1); result = order2;
          dao.findByOrderId(2); result = order3;
          //dao.acceptOrReject("CANCELLED", 1);
          dao.acceptOrReject("REJECTED", 1);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result5 = OrderFactory.cancelOrder(1, 3, "YES");
    assertEquals(result5, "Order Cancelled Successfully and amount refundued to PAYTM");
    String result6 = OrderFactory.cancelOrder(1, 4, "YES");
    assertEquals(result6, "You are unauthorized to cancel this order...");
    String result7 = OrderFactory.cancelOrder(2, 3, "YES");
    assertEquals(result7, "You cannot cancel this order..");
  }
/**
  * @param dao for mocking PlaceOrder Mock Test.
  * @throws ParseException for converting date to string.
   */
  @Test
  public final void testplaceOrder(@Mocked final OrderDAO dao) throws ParseException {
    final Menu m2 = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    final Menu m3 = new Menu(102, MenuCat.VEG, "GOBIMANJURIAN", 1, 40, 5);
    // final Menu m2 = new Menu(10, "Biryani", 120.0, "Non-Veg", MenStatus.AVAILABLE, 4);
    // final Menu m3 = new Menu(20, "Curd Rice", 60.0, "Veg", MenStatus.AVAILABLE, 5);
    final Wallet w1 = new Wallet(1, WalletType.CREDIT_CARD, 1500.45, 100);
    final Wallet w2 = new Wallet(2, WalletType.CREDIT_CARD, 2000.65, 200);
    final Wallet w3 = new Wallet(3, WalletType.PAYTM, 2500.45, 100);
    final Wallet w4 = new Wallet(4, WalletType.PAYTM, 3000.65, 200);
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String ord1 = new String("2020-04-28");
    final Date orDate1 = sdf.parse(ord1);
    final String ord2 = new String("2020-01-18");
    final Date orDate2 = sdf.parse(ord2);
    final Orders order1 = new Orders();
    order1.setOrderId(1);
    order1.setCustomerId(100);
    order1.setMenuId(101);
    order1.setVendorId(1000);
    order1.setOrderQuantity(1);
    order1.setWalletType(WalletType.PAYTM);
    order1.setOrderDate(orDate1);
    order1.setOrderComments("Spicy");

    final Orders order2 = new Orders();
    order2.setOrderId(2);
    order2.setCustomerId(100);
    order2.setMenuId(101);
    order2.setOrderStatus(OrderStatus.PENDING);
    order2.setVendorId(1000);
    order2.setOrderQuantity(2300);
    order2.setWalletType(WalletType.PAYTM);
    order2.setOrderDate(orDate1);
    order2.setOrderComments("Spicy");

    final Orders order3 = new Orders();
    order3.setOrderId(3);
    order3.setCustomerId(100);
    order3.setMenuId(101);
    order3.setVendorId(1000);
    order3.setOrderQuantity(2);
    order3.setWalletType(WalletType.PAYTM);
    order3.setOrderDate(orDate2);
    order3.setOrderComments("Spicy");
    new Expectations() {
        {
          dao.findByMenuId(101); result = m2;
          dao.getWalletInfo(WalletType.PAYTM, 100); result = w3;
          dao.placeOrder(order1);
        //   dao.placeOrder(order2);
        //   dao.placeOrder(order3);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result1 = OrderFactory.placeOrder(order1);
    assertEquals(result1, "Order Placed Successfully...");
    String result2 = OrderFactory.placeOrder(order2);
    assertEquals(result2, "Insufficient Funds...");
    String result3 = OrderFactory.placeOrder(order3);
    assertEquals(result3, "order cannot be placed for previous day");
  }
 /**
  * Tests that a list with some pending vendor is handled correctly.
  * @param dao mocking the dao class.
  * @throws ParseException for handling Dateformat errors.
  */
  @Test
  public final void testVendorHistory(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-04-28");
    final Date odt = sdf.parse(dt);
    final Orders n1 = new Orders(15, 9, 12, 100, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders n2 = new Orders(16, 10, 12, 101, OrderStatus.ACCEPTED, "TASTY", 201.00000, odt, 10, WalletType.CREDIT_CARD);
    final Orders n3 = new Orders(17, 11, 12, 102, OrderStatus.REJECTED, "LESSSPICY", 202.00000, odt, 11, WalletType.DEBIT_CARD);
    final Orders n4 = new Orders(18, 12, 12, 103, OrderStatus.ACCEPTED, "LESSOIL", 389.00000, odt, 12, WalletType.PHONE_PE);
    final Orders n5 = new Orders(19, 13, 12, 104, OrderStatus.ACCEPTED, "MORESPICY", 451.00000, odt, 13, WalletType.GOOGLE_PAY);
    final ArrayList<Orders> ven12 = new ArrayList<Orders>();
    ven12.add(n1);
    ven12.add(n2);
    ven12.add(n3);
    ven12.add(n4);
    ven12.add(n5);
    final Orders n6 = new Orders(20, 8, 14, 105, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 8, WalletType.PAYTM);
    final Orders n7 = new Orders(21, 7, 14, 106, OrderStatus.REJECTED, "MORESPICY", 199.00000, odt, 7, WalletType.PHONE_PE);
    final Orders n8 = new Orders(22, 6, 14, 107, OrderStatus.PENDING, "TASTY", 199.00000, odt, 6, WalletType.DEBIT_CARD);
    final Orders n9 = new Orders(23, 5, 14, 108, OrderStatus.ACCEPTED, "LESSOIL", 199.00000, odt, 5, WalletType.GOOGLE_PAY);
    final ArrayList<Orders> ven14 = new ArrayList<Orders>();
    ven14.add(n6);
    ven14.add(n7);
    ven14.add(n8);
    ven14.add(n9);
    new Expectations() {
        {
          dao.vendorHistory(12); result = ven12;
          dao.vendorHistory(14); result = ven14;
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    Orders[] pending1 = OrderFactory.showVendorHistory(12);
    Orders[] pending2 = OrderFactory.showVendorHistory(14);
    assertEquals(5, pending1.length);
    assertEquals(4, pending2.length);
  }
  /**
  * Tests that a list with some pending vendor is handled correctly.
  * @param dao mocking the dao class.
  * @throws ParseException for handling Dateformat errors.
  */
  @Test
  public final void testCustomerHistory(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-03-18");
    final Date odt = sdf.parse(dt);
    final Orders c1 = new Orders(15, 10, 12, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders c2 = new Orders(16, 10, 13, 201, OrderStatus.ACCEPTED, "TASTY", 201.00000, odt, 10, WalletType.CREDIT_CARD);
    final Orders c3 = new Orders(17, 10, 14, 202, OrderStatus.REJECTED, "LESSSPICY", 202.00000, odt, 11, WalletType.DEBIT_CARD);
    final Orders c4 = new Orders(18, 10, 15, 203, OrderStatus.ACCEPTED, "LESSOIL", 389.00000, odt, 12, WalletType.PHONE_PE);
    final Orders c5 = new Orders(19, 10, 16, 204, OrderStatus.ACCEPTED, "MORESPICY", 451.00000, odt, 13, WalletType.GOOGLE_PAY);
    final ArrayList<Orders> cus10 = new ArrayList<Orders>();
    cus10.add(c1);
    cus10.add(c2);
    cus10.add(c3);
    cus10.add(c4);
    cus10.add(c5);
    final Orders c6 = new Orders(20, 11, 17, 205, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 8, WalletType.PAYTM);
    final Orders c7 = new Orders(21, 11, 18, 206, OrderStatus.REJECTED, "MORESPICY", 199.00000, odt, 7, WalletType.PHONE_PE);
    final Orders c8 = new Orders(22, 11, 19, 207, OrderStatus.PENDING, "TASTY", 199.00000, odt, 6, WalletType.DEBIT_CARD);
    final Orders c9 = new Orders(23, 11, 20, 208, OrderStatus.ACCEPTED, "LESSOIL", 199.00000, odt, 5, WalletType.GOOGLE_PAY);
    final ArrayList<Orders> cus11 = new ArrayList<Orders>();
    cus11.add(c6);
    cus11.add(c7);
    cus11.add(c8);
    cus11.add(c9);
    new Expectations() {
        {
          dao.customerHistory(10); result = cus10;
          dao.customerHistory(11); result = cus11;
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    Orders[] pending1 = OrderFactory.showCustomerHistory(10);
    Orders[] pending2 = OrderFactory.showCustomerHistory(11);
    assertEquals(5, pending1.length);
    assertEquals(4, pending2.length);
  }
   /**
  * Tests that a list with some pending vendor is handled correctly.
  * @param dao mocking the dao class.
  * @throws ParseException for handling Dateformat errors.
  */
  @Test
  public final void testpendingVendor(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-03-18");
    final Date odt = sdf.parse(dt);
    final Orders v1 = new Orders(15, 9, 31, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders v2 = new Orders(15, 10, 31, 200, OrderStatus.PENDING, "SPICY", 199.00000, odt, 9, WalletType.PHONE_PE);
    final ArrayList<Orders> ven31 = new ArrayList<Orders>();
    ven31.add(v1);
    ven31.add(v2);
    final Orders v3 = new Orders(25, 19, 41, 213, OrderStatus.PENDING, "HOT", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders v4 = new Orders(26, 29, 41, 245, OrderStatus.PENDING, "SPICY", 199.00000, odt, 9, WalletType.DEBIT_CARD);
    final Orders v5 = new Orders(27, 30, 41, 600, OrderStatus.PENDING, "FAST", 199.00000, odt, 9, WalletType.GOOGLE_PAY);
    final ArrayList<Orders> ven41 = new ArrayList<Orders>();
    ven41.add(v3);
    ven41.add(v4);
    ven41.add(v5);
    new Expectations() {
        {
          dao.pendingVendor(31); result = ven31;
          dao.pendingVendor(41); result = ven41;
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    Orders[] pendvend1 = OrderFactory.showpendingVendorOrders(31);
    Orders[] pendvend2 = OrderFactory.showpendingVendorOrders(41);
    assertEquals(2, pendvend1.length);
    assertEquals(3, pendvend2.length);
  }
  /**
  * Tests that a list with some pending customer is handled correctly.
  * @param dao mocking the dao class.
  * @throws ParseException for handling Dateformat errors.
  */
  @Test
  public final void testpendingCustomer(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-03-18");
    final Date odt = sdf.parse(dt);
    final Orders m1 = new Orders(15, 60, 18, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders m2 = new Orders(15, 60, 13, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final ArrayList<Orders> cus60 = new ArrayList<Orders>();
    cus60.add(m1);
    cus60.add(m2);
    final Orders m3 = new Orders(16, 50, 18, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders m4 = new Orders(12, 50, 19, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final Orders m5 = new Orders(11, 50, 13, 200, OrderStatus.PENDING, "TOPPING", 199.00000, odt, 9, WalletType.PAYTM);
    final ArrayList<Orders> cus50 = new ArrayList<Orders>();
    cus50.add(m3);
    cus50.add(m4);
    cus50.add(m5);
    new Expectations() {
        {
          dao.pendingCustomer(60); result = cus60;
          dao.pendingCustomer(50); result = cus50;
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    Orders[] pending1 = OrderFactory.showpendingCustomerOrders(60);
    Orders[] pending2 = OrderFactory.showpendingCustomerOrders(50);
    assertEquals(2, pending1.length);
    assertEquals(3, pending2.length);
  }
}
