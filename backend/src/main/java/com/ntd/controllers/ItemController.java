package com.ntd.controllers;

import com.ntd.models.Item;
import com.ntd.models.Item;
import com.ntd.services.ItemService;
import com.ntd.services.ItemService;
import com.ntd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    /*
    Need to add a path for getting the items by user id not just the completed or uncompleted
    ids are the same so I would need a different path in the front
     */

    @Autowired
    ItemService itemServ;

    @Autowired
    UserService uServ;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        ResponseEntity<List<Item>> response;
        List<Item> items = itemServ.getAllItems();

        if (items.size() > 0) {
            response = new ResponseEntity<>(items, HttpStatus.OK); // 200
        } else if (items.size() == 0) {
            response = new ResponseEntity<>(items, HttpStatus.NO_CONTENT); // 204
        } else {
            response = new ResponseEntity<>(items, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getSingleItem(@PathVariable("id") int id) {
        ResponseEntity<Item> response;
        Item item;

        if (!itemServ.ifItemExists(id)) {
            // item does not exist
            item = new Item();
            response = new ResponseEntity<>(item, HttpStatus.NOT_FOUND); // 404
        } else {
            // item does exist
            item = itemServ.getItemById(id);
            response = new ResponseEntity<>(item, HttpStatus.OK); // 200
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Item> saveNewItem(@RequestBody Item newItem) {
        ResponseEntity<Item> response;

        if (itemServ.ifItemExists(newItem.getItemId())) {
            // item already exists
            response = new ResponseEntity<>(newItem, HttpStatus.CONFLICT); // 409
        } else {
            // item does not already exist
            itemServ.saveItem(newItem);
            response = new ResponseEntity<>(newItem, HttpStatus.CREATED); // 201
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item updatedItem, @PathVariable("id") int id) {
        ResponseEntity<Item> response;

        if (itemServ.ifItemExists(updatedItem.getItemId()) && updatedItem.getItemId() == id) {
            // item exists
            itemServ.updateItem(updatedItem);
            response = new ResponseEntity<>(updatedItem, HttpStatus.OK); // 200
        } else {
            // item does not exist
            response = new ResponseEntity<>(updatedItem, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") int id) {
        ResponseEntity<Item> response;
        Item item;

        if (itemServ.ifItemExists(id)) {
            // item does exist
            item = itemServ.getItemById(id);
            itemServ.deleteItem(item);
            response = new ResponseEntity<>(item, HttpStatus.OK); // 200
        } else {
            // item does not exist
            item = new Item();
            response = new ResponseEntity<>(item, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("uncompleted/{user_id}")
    public ResponseEntity<Set<Item>> getAllUncompletedItemsByUser(@PathVariable("user_id") int user_id) {
        ResponseEntity<Set<Item>> response;
        Set<Item> unpaidItems;

        if (uServ.ifUserExists(user_id)) {
            // user does exist
            unpaidItems = itemServ.getAllUncompletedItemsByUser(user_id);

            if (unpaidItems.size() > 0) {
                response = new ResponseEntity<>(unpaidItems, HttpStatus.OK); //200
            } else {
                response = new ResponseEntity<>(unpaidItems, HttpStatus.NO_CONTENT); // 204
            }

        } else {
            // user does not exist
            unpaidItems = new HashSet<>();
            response = new ResponseEntity<>(unpaidItems, HttpStatus.NOT_FOUND); // 404
        }

        return response;
    }

    @GetMapping("completed/{user_id}")
    public ResponseEntity<Set<Item>> getAllCompletedItemsByUser(@PathVariable("user_id") int user_id) {
        ResponseEntity<Set<Item>> response;
        Set<Item> paidItems;

        if (uServ.ifUserExists(user_id)) {
            // user does exist
            paidItems = itemServ.getAllCompletedItemsByUser(user_id);

            if (paidItems.size() > 0) {
                response = new ResponseEntity<>(paidItems, HttpStatus.OK); // 200
            } else {
                response = new ResponseEntity<>(paidItems, HttpStatus.NO_CONTENT); // 204
            }

        } else {
            // user does not exist
            paidItems = new HashSet<>();
            response = new ResponseEntity<>(paidItems, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }
}