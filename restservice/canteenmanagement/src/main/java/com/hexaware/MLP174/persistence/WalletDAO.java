package com.hexaware.MLP174.persistence;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import com.hexaware.MLP174.model.Wallet;
import org.skife.jdbi.v2.sqlobject.Bind;
/**
 * MenuDAO class used to fetch data from data base.
 * @author hexware
 */
public interface WalletDAO {
    /**
     * @return the all the Menu record.
     */
  @SqlQuery("Select * from Wallet")
    @Mapper(WalletMapper.class)
    List<Wallet> show();
    /**
     * @param custId for input customer id.
     * @return the all the wallet records of customer.
     */
  @SqlQuery("SELECT * FROM WALLET WHERE CUS_ID=:custId")
    @Mapper(WalletMapper.class)
    List<Wallet> showByWalletId(@Bind("custId") int custId);
}

