package com.hexaware.MLP174.model;
//import static org.junit.Assert.assertEquals;
// import com.hexaware.MLP175.persistence.MenuDAO;
// import com.hexaware.MLP175.factory.MenuFactory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.junit.Test;
import org.junit.Before;
//import org.junit.runner.RunWith;

// import mockit.Expectations;
// import mockit.MockUp;
// import mockit.Mocked;
// import mockit.Mock;
//import mockit.integration.junit4.JMockit;

// import java.util.ArrayList;
    /**
   * test for orders.
   */
public class OrdersTest {
    /**
   * test for orders.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests the equals/hashcode methods of the orders class.
   *  @throws ParseException for date format validation.
   */
  @Test
  public final void testEquals() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2019-03-15");
    Date odt = sdf.parse(dt);
    Orders m = new Orders();
    Orders m1 = null;
    Orders m2 = new Orders(1, 1, 4, 101, OrderStatus.ACCEPTED, "SPICY", 150.00000, odt, 2, WalletType.PAYTM);
    Orders m3 = new Orders(1, 1, 4, 101, OrderStatus.ACCEPTED, "SPICY", 150.00000, odt, 2, WalletType.PAYTM);
    assertTrue(m2.equals(m3));
    assertFalse(m2.equals(m1));
    Orders c = null;
    assertFalse(m2.equals(m));
    Vendor v = new Vendor();
    assertFalse(m2.equals(v));
    assertFalse(m2.equals(c));
    assertEquals(m2.hashCode(), m3.hashCode());
  }
  /**
   * Tests the toString() methods of the Order class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2019-03-15");
    Date odt = sdf.parse(dt);
    Orders m2 = new Orders(1, 1, 4, 101, OrderStatus.ACCEPTED, "SPICY", 150.00000, odt, 2, WalletType.PAYTM);
    String result = String.format("%5s %5s %5s %5s %10s %15s %7s %10s %5s %10s",
        m2.getOrderId(), m2.getCustomerId(), m2.getVendorId(), m2.getMenuId(), m2.getOrderStatus(),
        m2.getOrderComments(), m2.getOrderTotalamount(), m2.getOrderDate(), m2.getOrderQuantity(),
        m2.getWalletType());
    assertEquals(result, m2.toString());
  }
  /**
   * test for orders.
   * @throws ParseException for Checking Date Format.
   */
  @Test
   public final void testOrders() throws ParseException {
    Orders m = new Orders();
    assertNotNull(m);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    Orders order = new Orders(1, 1, 4, 101, OrderStatus.ACCEPTED, "SPICY", 150.00000, odt, 2, WalletType.PAYTM);
    assertEquals(1, order.getOrderId());
    assertEquals(1, order.getCustomerId());
    assertEquals(4, order.getVendorId());
    assertEquals(101, order.getMenuId());
    assertEquals(OrderStatus.ACCEPTED, order.getOrderStatus());
    assertEquals("SPICY", order.getOrderComments());
    assertEquals(150.00000, order.getOrderTotalamount(), 0);
    assertEquals(odt, order.getOrderDate());
    assertEquals(2, order.getOrderQuantity());
    assertEquals(WalletType.PAYTM, order.getWalletType());

    m.setOrderId(1);
    m.setCustomerId(1);
    m.setVendorId(4);
    m.setMenuId(101);
    m.setOrderStatus(OrderStatus.ACCEPTED);
    m.setOrderComments("SPICY");
    m.setOrderTotalamount(150.00000);
    m.setOrderDate(odt);
    m.setOrderQuantity(2);
    m.setWalletType(WalletType.PAYTM);

    assertEquals(1, m.getOrderId());
    assertEquals(1, m.getCustomerId());
    assertEquals(4, m.getVendorId());
    assertEquals(101, m.getMenuId());
    assertEquals(OrderStatus.ACCEPTED, m.getOrderStatus());
    assertEquals("SPICY", m.getOrderComments());
    assertEquals(150.00000, m.getOrderTotalamount(), 0);
    assertEquals(odt, m.getOrderDate());
    assertEquals(2, m.getOrderQuantity());
    assertEquals(WalletType.PAYTM, m.getWalletType());
  }
}
