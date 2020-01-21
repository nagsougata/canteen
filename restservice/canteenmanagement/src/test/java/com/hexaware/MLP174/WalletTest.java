package com.hexaware.MLP174.model;

//import com.hexaware.MLP174.persistence.MenuDAO;
//import com.hexaware.MLP174.factory.MenuFactory;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.text.ParseException;
import org.junit.Test;
import org.junit.Before;
//import org.junit.runner.RunWith;

//import mockit.Expectations;
//import mockit.MockUp;
//import mockit.Mocked;
//import mockit.Mock;
//import mockit.integration.junit4.JMockit;
//import java.util.ArrayList;

/**
 * Test class for Menu.
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
   public final void testEquals() {
    Wallet m1 = null;
    Wallet m2 = new Wallet(501,"CREDIT_CARD",1000.12,101);
    Wallet m3 = new Wallet(501,"CREDIT_CARD",1000.12,101);
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new vendor();
    assertFalse(m2.equals(vendor));
  }
   /**
   * Tests the toString() methods of the Menu class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Wallet m = new wallet(501,"CREDIT_CARD",1000.12,101;)
    String result = String.format("%15s %15s %15s %15s",
          m.getWalletId(),m.getWalletType(), m.getWalletAmount(),
          m.getCustomerId());
    assertEquals(result, m.toString());
  }
   /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testWallet() {
    Wallet wallet  = new Wallet();
    wallet.setWalletId(1);
    assertEquals(501, wallet.getWalletId());
    wallet.setWalletType("CREDIT_CARD");
    assertEquals("CREDIT_CARD", wallet.getWalletType());
    wallet.setWalletAmount(1);
    assertEquals(501, wallet.getMenuQuantity());
    menu.setMenuCost(40);
    assertEquals(40, menu.getMenuCost());
    menu.setMenuCalories(90);
    assertEquals(90, menu.getMenuCalories());
    menu.setMenuReviews("***");
    assertEquals("***", menu.getMenuReviews());
  }
  /**public final void testMenu() {
    Menu m = new Menu();
    Menu m100 = new Menu(100);
    Menu m101 = new Menu(101);
    assertNotEquals(m100, null);
    assertNotEquals(m101, null);
    assertEquals(m100.getFoodId(),
        new Menu(100).getFoodId());
    m101.setFoodId(100);
    assertNotEquals(m101, new Menu(101));
    assertEquals(m100.hashCode(),
        new Menu(100).hashCode());
    assertEquals(m100, new Menu(100));
  } */
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
   /**
  @Test
  public final void testListAllEmpty(@Mocked final MenuDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Menu>();
      }
    };
    new MockUp<MenuFactory>() {
      @Mock
      MenuDAO dao() {
        return dao;
      }
    };
    Menu[] me = MenuFactory.showMenu();
    assertEquals(0, me.length);
  } */
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  /**
  @Test
  public final void testListAllSome(@Mocked final MenuDAO dao) {
    final Menu m100 = new Menu(100);
    final Menu m101 = new Menu(101);
    final ArrayList<Menu> mn = new ArrayList<Menu>();
    new Expectations() {
      {
        mn.add(m100);
        mn.add(m101);
        dao.show(); result = mn;
      }
    };
    new MockUp<MenuFactory>() {
      @Mock
      MenuDAO dao() {
        return dao;
      }
    };
    Menu[] mn1 = MenuFactory.showMenu();
    assertEquals(2, mn1.length);
    assertEquals(new Menu(100).getFoodId(),
        mn1[0].getFoodId());
    assertEquals(new Menu(101).getFoodId(),
        mn1[1].getFoodId());
  }*/
}
