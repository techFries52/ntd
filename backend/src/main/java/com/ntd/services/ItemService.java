package com.ntd.services;

import com.ntd.models.Bill;
import com.ntd.models.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {
/*Need to make a create method for the interface and the implementation. This will require some saved
json to make sure they do get created but I feel it is important to test due to the nature of this
Lots of creating and deleting
after I implement the category service I will work on creating a create method for items and categories

I do realize that there is a save but that requires a bill already existing

testing backend will become easier when we have a front end
 */
    boolean ifItemExists(int itemId);

    Item saveItem(Item item);

    Item updateItem(Item item);

    void deleteItem(Item item);

    Item getItemById(int itemId);

    List<Item> getAllItems();

    Set<Item> getAllUncompletedItemsByUser(int user_id);

    Set<Item> getAllCompletedItemsByUser(int user_id);
}
