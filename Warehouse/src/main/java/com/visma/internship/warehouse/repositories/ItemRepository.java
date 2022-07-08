package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("UPDATE Item i SET i.quantity=i.quantity-1 WHERE i.id =:id")
    void removeOneQnt(@Param("id") long id);
}
