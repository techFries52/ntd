package com.ntd.repositories;

import com.ntd.models.Item;
import com.ntd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
