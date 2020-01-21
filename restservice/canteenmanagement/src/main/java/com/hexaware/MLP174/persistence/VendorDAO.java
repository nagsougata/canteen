package com.hexaware.MLP174.persistence;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import com.hexaware.MLP174.model.Vendor;
/**
 * VendorDAO class used to fetch data from data base.
 * @author hexware
 */
public interface VendorDAO {
    /**
     * @return the all the Vendor record.
     */
  @SqlQuery("Select * from Vendor")
    @Mapper(VendorMapper.class)
    List<Vendor> show();
     /**
     * @param vendorName for reading vendor name.
     * @return the customer selected record.
     */
  @SqlQuery("Select * from Vendor WHERE VEN_NAME=:vendorName")
     @Mapper(VendorMapper.class)
     Vendor findByVendorName(@Bind("vendorName") String vendorName);
    /**
     * @param vendorName for accepting vendor name.
     * @param vendorPassword for accepting vendor password.
     * @return count for authentication.
     */
  @SqlQuery("SELECT COUNT(*) FROM Vendor WHERE VEN_NAME=:vendorName AND VEN_PASSWORD=:vendorPassword")
     int authenticate(@Bind("vendorName")String vendorName, @Bind("vendorPassword")String vendorPassword);
}
