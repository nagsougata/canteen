package com.hexaware.MLP174.persistence;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import com.hexaware.MLP174.model.Menu;
import org.skife.jdbi.v2.sqlobject.Bind;

/**
 * MenuDAO class used to fetch data from data base.
 * @author hexware
 */
public interface MenuDAO {
    /**
     * @return the all the Menu record.
     */
  @SqlQuery("Select * from Menu")
    @Mapper(MenuMapper.class)
    List<Menu> show();
    /**
     * @return the all the Menu record.
     *@param menuId returns menuId.
     */
  @SqlQuery("Select * from Menu WHERE MEN_ID=:menuId")
    @Mapper(MenuMapper.class)
    Menu show(@Bind("menuId") int menuId);
}

