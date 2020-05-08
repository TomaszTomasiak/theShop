package com.theshop.service;

import com.theshop.dao.ItemDao;
import com.theshop.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItems() {
        return itemDao.findAll();
    }

    public Item getItem(final long id) {
        return itemDao.findById(id).orElse(null);
    }

    public Item save(final Item item) {
       return itemDao.save(item);
    }

    public void delete(final long id) {
        itemDao.deleteById(id);
    }
}
