package com.timotiusoktorio.inventoryapp.dom.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.timotiusoktorio.inventoryapp.dom.objects.Location;
import com.timotiusoktorio.inventoryapp.dom.objects.Unit;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface LocationsModelDao {

    @Query("select * from Location")
    LiveData<List<Location>> getLocations();


    @Insert(onConflict = REPLACE)
    void addLocation(Location location);

    @Update
    void updateLocation(Location location);

    @Delete
    void deleteLocation(Location location);

}
