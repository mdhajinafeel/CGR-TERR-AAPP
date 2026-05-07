package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "container_images",
        indices = {
                @Index(name = "idx_dispatch_id_container_images", value = {"tempDispatchId"}),
                @Index(name = "idx_image_path", value = {"imagePath"}),
                @Index(name = "idx_temp_container_image_id", value = {"tempContainerImageId"}),
                @Index(name = "idx_synced_container_images", value = {"isSynced"}),
                @Index(name = "idx_deleted_container_images", value = {"isDeleted"}),
                @Index(name = "idx_created_at_container_images", value = {"createdAt"})
        })
public class ContainerImages implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String tempDispatchId;
    public String tempContainerImageId;
    public String imagePath;
    public long createdAt;
    public boolean isSynced;
    public boolean isDeleted;
}