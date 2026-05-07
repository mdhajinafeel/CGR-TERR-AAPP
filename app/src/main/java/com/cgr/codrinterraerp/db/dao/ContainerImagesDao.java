package com.cgr.codrinterraerp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ContainerImages;

import java.util.List;

@Dao
public interface ContainerImagesDao {

    @Insert
    long insert(ContainerImages image);

    @Query("SELECT * FROM container_images WHERE tempDispatchId = :tempDispatchId ORDER BY id DESC")
    LiveData<List<ContainerImages>> getContainerImages(String tempDispatchId);

    @Delete
    void delete(ContainerImages image);

    @Query("DELETE FROM container_images WHERE tempContainerImageId = :tempContainerImageId")
    int hardDeleteImage(String tempContainerImageId);

    @Query("UPDATE container_images SET isDeleted=1 WHERE tempContainerImageId = :tempContainerImageId")
    int softDeleteImage(String tempContainerImageId);
}