package com.softmed.rucodia.Dom.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.softmed.rucodia.Dom.entities.Orders;
import com.softmed.rucodia.Dom.entities.OrdersItems;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderModelDao {

    @Query("select Product.name, Orders.ordered, Product.price as prices from Orders INNER JOIN Product ON Orders.product_id = Product.id WHERE batch=:batch")
    LiveData<List<OrdersItems>> getOrdersByBatchNo(String batch);

    @Query("select * from Orders WHERE status = 0")
    List<Orders> getUnpostedOrders();


    @Insert(onConflict = REPLACE)
    void addOrder(Orders order);

}
