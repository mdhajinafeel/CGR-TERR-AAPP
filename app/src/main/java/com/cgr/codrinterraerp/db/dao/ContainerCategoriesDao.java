package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ContainerCategories;

import java.util.List;

@Dao
public interface ContainerCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContainerCategories(List<ContainerCategories> containerCategoriesList);

    @Query("DELETE FROM container_categories")
    void clearAll();

    @Query("SELECT * FROM container_categories WHERE productTypeId = :productTypeId")
    List<ContainerCategories> fetchContainerCategories(int productTypeId);
}