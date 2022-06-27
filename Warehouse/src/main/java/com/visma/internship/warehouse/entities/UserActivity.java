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


}
