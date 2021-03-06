package com.softmed.rucodia.Dom.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.softmed.rucodia.Dom.entities.Balances;
import com.softmed.rucodia.Dom.entities.ProductBalance;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BalanceModelDao {

    @Query("select " +
            "Product.sub_category_id as subcategoryId, " +
            "Product.description as productDescription, " +
            "SubCategory.name as subCategoryName," +
            "Product.name as productName ," +
            "Unit.name as unit, " +
            "Balances.price as price, " +
            "Balances.image_path as image_path, " +
            "Balances.balance, " +
            "Product.id as productId " +

            "FROM Balances " +
            "INNER JOIN Product ON Balances.product_id=Product.id " +
            "INNER JOIN Unit ON Product.unit_id=Unit.id " +
            "INNER JOIN SubCategory ON Product.sub_category_id = Subcategory.id  ")
    LiveData<List<ProductBalance>> getBalances();




    @Query("select Product.id as productId, " +
            "Product.sub_category_id as subcategoryId, " +
            "Product.description as productDescription, " +
            "SubCategory.name as subCategoryName, " +
            "Product.name AS productName, " +
            "Unit.name as unit, " +
            "Balances.price as price, " +
            "Balances.image_path as imagePath, " +
            "Balances.balance, " +
            "Product.id as productId " +
            " FROM Balances " +

            "INNER JOIN Product ON Balances.product_id=Product.id " +
            "INNER JOIN SubCategory ON Product.sub_category_id = SubCategory.id " +
            "INNER JOIN Unit ON Product.unit_id=Unit.id " +
            "where Product.id = :id")
    LiveData<ProductBalance> getProductBalanceById(long id);




    @Query("select * from Balances WHERE Balances.product_id=:product_id")
    Balances getBalance(int product_id);


    @Insert(onConflict = REPLACE)
    void addBalance(Balances balances);

}
