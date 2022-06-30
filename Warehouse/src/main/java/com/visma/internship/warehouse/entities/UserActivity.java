package com.visma.internship.warehouse.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="UserActivity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private ShopUser shopUser;

    @ManyToOne
    private Item item;

    @CreationTimestamp
    private LocalDateTime activityTime;

    public UserActivity() {
    }

    public UserActivity(ShopUser shopUser, Item item) {
        this.shopUser = shopUser;
        this.item = item;
    }

    public UserActivity(long id, ShopUser shopUser, Item item) {
        this.id = id;
        this.shopUser = shopUser;
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }
}
