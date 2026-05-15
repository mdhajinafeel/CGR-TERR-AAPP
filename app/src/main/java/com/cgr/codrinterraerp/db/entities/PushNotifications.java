package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "push_notifications",
        indices = {
                @Index(name = "idx_id_notifications", value = {"id"}),
                @Index(name = "idx_title_notifications", value = {"title"}),
                @Index(name = "idx_type_notifications", value = {"type"}),
                @Index(name = "idx_message_notifications", value = {"message"}),
                @Index(name = "idx_status_notifications", value = {"status"}),
                @Index(name = "idx_read_notifications", value = {"isRead"})
        }
)
public class PushNotifications implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String message;
    public String type;
    public String status;
    public boolean isRead = false;
    public long createdAt = System.currentTimeMillis();
}