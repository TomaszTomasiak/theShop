package com.theshop.service;

import com.theshop.dao.ItemDao;
import com.theshop.domain.Item;
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
        return itemDao.findAll();
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
}
