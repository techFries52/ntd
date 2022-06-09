package com.ntd.services;


import com.ntd.models.Item;
import com.ntd.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepo;

    @Override
    public boolean ifItemExists(int itemId) {
        Optional<Item> item = itemRepo.findById(itemId);
        return item.isPresent();
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepo.delete(item);
    }

    @Override
    public Item getItemById(int itemId) {
        Optional<Item> item = itemRepo.findById(itemId);
        Item checkedItem = new Item();

        if(item.isPresent()){
            checkedItem = item.get();
        }

        return checkedItem;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }


    @Override
    public Set<Item> getAllUncompletedItemsByUser(int user_id) {
        return itemRepo.findAll().stream()
                .filter( item -> !item.getCompleted() && item.getUser().getUser_id() == user_id)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Item> getAllCompletedItemsByUser(int user_id) {
        return itemRepo.findAll().stream()
                .filter( item -> item.getCompleted() && item.getUser().getUser_id() == user_id)
                .collect(Collectors.toSet());
    }
}
