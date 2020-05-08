package com.theshop.mapper;

import com.theshop.dao.ProductDao;
import com.theshop.domain.Item;
import com.theshop.domain.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    @Autowired
    ProductDao productDao;

    public Item mapToItem(final ItemDto itemDto) {
        Item itemBean = new Item();
        itemBean.setId(itemDto.getId());
        itemBean.setProduct(productDao.findById(itemDto.getId()).orElse(null));
        itemBean.setQuantity(itemDto.getQuantity());
        return itemBean;
    }

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getId(),
                item.getProduct().getProductId(),
                item.getQuantity()
        );
    }

    public List<Item> mapToItemList(final List<ItemDto> itemDtoList) {
        return itemDtoList.stream()
                .map(this::mapToItem)
                .collect(Collectors.toList());
    }

    public List<ItemDto> mapToItemDtoList(final List<Item> itemList) {
        return itemList.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }
}
