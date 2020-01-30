package com.hexaware.MLP174.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


// import com.hexaware.MLP174.persistence.VendorDAO;
// import com.hexaware.MLP174.factory.VendorFactory;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

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
 * Test class for Vendor.
 */
//@RunWith(JMockit.class)
public class WalletTest {
    /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testWallet() {
    Wallet m = new Wallet();
    assertNotNull(m);
    Wallet wallet = new Wallet(36, WalletType.PAYTM, 4000.87, 1);
    assertEquals(36, wallet.getWalletId());
    assertEquals(WalletType.PAYTM, wallet.getWalletType());
    assertEquals(4000.87, wallet.getWalletAmount(), 0);
    assertEquals(1, wallet.getCustomerId());

    m.setWalletId(36);
    m.setWalletType(WalletType.PAYTM);
    m.setWalletAmount(4000.87);
    m.setCustomerId(1);

    assertEquals(36, wallet.getWalletId());
    assertEquals(WalletType.PAYTM, wallet.getWalletType());
    assertEquals(4000.87, wallet.getWalletAmount(), 0);
    assertEquals(1, wallet.getCustomerId());
  }
  /**
   * Tests the toString() methods of the Wallet class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Wallet m = new Wallet();
    String result = String.format("%5s %15s %15s %15s",  m.getWalletId(), m.getWalletType(),
        m.getWalletAmount(), m.getCustomerId());
    assertEquals(result, m.toString());
  }
  /**
   * Tests for equals.
   */
  @Test
  public final void testEquals() {
    Wallet m = new Wallet();
    Wallet m1 = null;
    Wallet m2 = new Wallet(36, WalletType.PAYTM, 4000.87, 1);
    Wallet m3 = new Wallet(36, WalletType.PAYTM, 4000.87, 1);
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new Vendor();
    assertFalse(m2.equals(vendor));
    assertFalse(m.equals(m2));
  }

  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  // @Test
  // public final void testListAllEmpty(@Mocked final VendorDAO dao) {
  //   new Expectations() {
  //     {
  //       dao.show(); result = new ArrayList<Vendor>();
  //     }
  //   };
  //   new MockUp<VendorFactory>() {
  //     @Mock
  //     VendorDAO dao() {
  //       return dao;
  //     }
  //   };
  //   Vendor[] me = VendorFactory.showVendor();
  //   assertEquals(0, me.length);
  // }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  // @Test
  // public final void testListAllSome(@Mocked final VendorDAO dao) {
  //   final Vendor m100 = new Vendor(100);
  //   final Vendor m101 = new Vendor(101);
  //   final ArrayList<Vendor> mn = new ArrayList<Vendor>();
  //   new Expectations() {
  //     {
  //       mn.add(m100);
  //       mn.add(m101);
  //       dao.show(); result = mn;
  //     }
  //   };
  //   new MockUp<VendorFactory>() {
  //     @Mock
  //     VendorDAO dao() {
  //       return dao;
  //     }
  //   };
  //   Vendor[] mn1 = VendorFactory.showVendor();
  //   assertEquals(2, mn1.length);
  //   assertEquals(new Vendor(100).getFoodId(),
  //       mn1[0].getFoodId());
  //   assertEquals(new Vendor(101).getFoodId(),
  //       mn1[1].getFoodId());
  // }
}
