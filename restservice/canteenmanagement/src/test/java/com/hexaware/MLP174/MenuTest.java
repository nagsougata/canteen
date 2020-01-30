package com.hexaware.MLP174.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import com.hexaware.MLP174.persistence.MenuDAO;
import com.hexaware.MLP174.factory.MenuFactory;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.text.ParseException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

 import mockit.Expectations;
 import mockit.MockUp;
 import mockit.Mocked;
 import mockit.Mock;
 import mockit.integration.junit4.JMockit;
 import java.util.ArrayList;

/**
 * Test class for Menu.
 */
@RunWith(JMockit.class)
public class MenuTest {
    /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests for equals.
   */
  @Test
  public final void testEquals() {
    Menu m = new Menu();
    Menu m1 = null;
    Menu m2 = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    Menu m3 = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new Vendor();
    assertFalse(m2.equals(vendor));
    assertFalse(m.equals(m2));
    Menu m4 = new Menu();
    assertFalse(m4.equals(m2));
    assertEquals(m2.hashCode(), m3.hashCode());
  }
  /**
   * Tests the toString() methods of the Menu class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Menu m = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    String result = String.format("%5s %10s %15s %5s %10s %8s",
          m.getMenuId(), m.getMenuCat(), m.getMenuItem(), m.getMenuQuantity(),
          m.getMenuCost(), m.getMenuRating());
    assertEquals(result, m.toString());
  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testMenu() {
    Menu m = new Menu();
    assertNotNull(m);
    Menu menu = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    menu.setMenuId(101);
    assertEquals(101, menu.getMenuId());
    menu.setMenuCat(MenuCat.VEG);
    assertEquals(MenuCat.VEG, menu.getMenuCat());
    menu.setMenuItem("VEG BIRIYANI");
    assertEquals("VEG BIRIYANI", menu.getMenuItem());
    menu.setMenuQuantity(1);
    assertEquals(1, menu.getMenuQuantity());
    menu.setMenuCost(60);
    assertEquals(60, menu.getMenuCost());
    menu.setMenuRating(4);
    assertEquals(4, menu.getMenuRating());

    m.setMenuId(101);
    m.setMenuCat(MenuCat.VEG);
    m.setMenuItem("VEG BIRIYANI");
    m.setMenuQuantity(1);
    m.setMenuCost(60);
    m.setMenuRating(4);

    assertEquals(101, m.getMenuId());
    assertEquals(MenuCat.VEG, m.getMenuCat());
    assertEquals("VEG BIRIYANI", m.getMenuItem());
    assertEquals(1, m.getMenuQuantity());
    assertEquals(60, m.getMenuCost());
    assertEquals(4, m.getMenuRating());
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
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
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllSome(@Mocked final MenuDAO dao) {
    final Menu m2 = new Menu(101, MenuCat.VEG, "VEG BIRIYANI", 1, 60, 4);
    final Menu m3 = new Menu(105, MenuCat.NONVEG, "FISH FRY", 1, 250, 5);
    final ArrayList<Menu> mn = new ArrayList<Menu>();
    new Expectations() {
      {
        mn.add(m2);
        mn.add(m3);
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
    assertEquals(101, mn1[0].getMenuId());
    assertEquals(105, mn1[1].getMenuId());
    assertEquals(MenuCat.VEG, mn1[0].getMenuCat());
    assertEquals(MenuCat.NONVEG, mn1[1].getMenuCat());
    assertEquals("VEG BIRIYANI", mn1[0].getMenuItem());
    assertEquals("FISH FRY", mn1[1].getMenuItem());
    assertEquals(1, mn1[0].getMenuQuantity());
    assertEquals(1, mn1[1].getMenuQuantity());
    assertEquals(60, mn1[0].getMenuCost());
    assertEquals(250, mn1[1].getMenuCost());
    assertEquals(4, mn1[0].getMenuRating());
    assertEquals(5, mn1[1].getMenuRating());
  }
}
