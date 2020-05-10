package com.theshop.controller;

import com.theshop.domain.dto.ItemDto;
import com.theshop.exception.NotFoundException;
import com.theshop.mapper.ItemMapper;
import com.theshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/theshop/items")
public class ItemController {
    @Autowired
    private ItemService service;

    @Autowired
    private ItemMapper mapper;

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    @GetMapping
    public List<ItemDto> getAllItems() {
        log.debug("REST request to get all items");
        return mapper.mapToItemDtoList(service.getItems());
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get item with id: ", id);
        return mapper.mapToItemDto(service.getItem(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        log.debug("REST request to add new item: ", itemDto);
        service.saveItem(mapper.mapToItem(itemDto));
        return itemDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto updateItemById(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        log.debug("REST request to update item with id: ", id);
        return mapper.mapToItemDto(service.saveItem(mapper.mapToItem(itemDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable("id") long id) {
        log.debug("REST request to delete item with id: ", id);
        service.deleteItem(id);
    }
}
