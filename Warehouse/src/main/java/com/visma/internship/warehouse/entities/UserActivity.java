package com.visma.internship.warehouse.entities;

import javax.persistence.*;

@Entity
@Table(name="activity_table")
public class UserActivity {
    // TODO: Quantity prideti nebent.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private ShopUser shopUser;

    @OneToOne
    private Item item;

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
}
