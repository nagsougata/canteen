package com.hexaware.MLP174.util;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP174.model.Orders;
import com.hexaware.MLP174.factory.OrderFactory;
import javax.ws.rs.Consumes;
/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/orders")
public class OrderRest {
    /**
    * Return Order details.
    * @return the order details.
    */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Orders[] listOrder() {
    final Orders[] order = OrderFactory.showOrder();
    return order;
  }
  /**
   * Returns Order details.
   * @return the Order details.
   * @param id for history of customer.
   */
  @GET
  @Path("/customerhistory/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderTest1ById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showCustomerHistory(id);
    if (order == null) {
      throw new NotFoundException("No such customer ID: " + id);
    }
    return order;
  }
   /**
   * Returns Order details.
   * @return the Order details.
   * @param id for pending vendors.
   */
  @GET
  @Path("/vendorhistory/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderTest2ById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showVendorHistory(id);
    if (order == null) {
      throw new NotFoundException("No such Vendor ID: " + id);
    }
    return order;
  }
  /**
   * Returns Order details.
   * @return the Order details.
   * @param id for pending customers
   */
  @GET
  @Path("/pendingcustomer/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderListById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showpendingCustomerOrders(id);
    if (order == null) {
      throw new NotFoundException("No such Customer ID: " + id);
    }
    return order;
  }
  /**
   * Returns Order details.
   * @return the Order details.
   * @param id for pending customers
   */
  @GET
  @Path("/pendingvendororders/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderTestById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showpendingVendorOrders(id);
    if (order == null) {
      throw new NotFoundException("No such vendor ID: " + id);
    }
    return order;
  }

/**
   * Returns Order details.
   * @param orderId for cancel order.
   * @param custId for customer id.
   * @param status for status.
   * @return the cancelled order details
   */
  @POST
  @Path("CancelOrder/{orderId}/{custId}/{status}")
  @Produces(MediaType.APPLICATION_JSON)
  public final String cancelOrder(@PathParam("orderId") final int orderId, @PathParam("custId") final int custId,
                                  @PathParam("status") final String status) {
    String result = OrderFactory.cancelOrder(orderId, custId, status);
    return result;
  }
  /**
   * Approve or Deny order.
   * @return the Order details.
   * @param orderId for place order object.
   * @param vendorId for vendor id.
   * @param status for status
   */
  @POST
  @Path("/acceptOrRejectOrder/{orderId}/{vendorId}/{status}")
  @Produces(MediaType.APPLICATION_JSON)
   public final String acceptOrRejectOrder(@PathParam("orderId") final int orderId,
        @PathParam("vendorId") final int vendorId, @PathParam("status") final String status) {
    String result = OrderFactory.acceptOrRejectOrder(orderId, vendorId, status);
    return result;
  }
   /**
   * Returns Menu details.
   * @param order for cancel order.
   * @return the cancelled order details
   */
  @POST
  @Path("/placeOrder")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String placeorder(final Orders order) {
    String result = OrderFactory.placeOrder(order);
    return result;
  }
}


