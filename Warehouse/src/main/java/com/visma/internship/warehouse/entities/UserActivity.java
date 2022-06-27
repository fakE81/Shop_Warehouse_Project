package com.visma.internship.warehouse.entities;

import javax.persistence.*;

@Entity
@Table(name="activity_table")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "item_id")
    private long item_id;

    public UserActivity() {
    }

    public UserActivity(long id, long user_id, long item_id) {
        this.id = id;
        this.user_id = user_id;
        this.item_id = item_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }
}
