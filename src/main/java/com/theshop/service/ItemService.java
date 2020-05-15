package com.theshop.service;

import com.theshop.dao.ItemDao;
import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.Product;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ItemService {

    private final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemDao itemDao;


    public List<Item> getItems() {
        log.debug("Request to get all items");
        return
                itemDao.findAll();
    }

    public Optional<Item> getItem(long id) {
        log.debug("Request to get item with id: {}", id);
        return itemDao.findById(id);
    }

    public Item saveItem(Item item) {
        log.debug("Request to create item: {}", item);
       return itemDao.save(item);
    }

    public void deleteItem(long id) {
        log.debug("Request to delete item with id: {}", id);
        itemDao.deleteById(id);
    }

    public Item addUpdateRemoveProductItem(Item item) throws CartExceptionNotFound {
        log.debug("Request to add, delete or update product item: {} ", item);
        saveItem(item);
        return item;
    }


}
