package com.theshop.validator;

import com.theshop.domain.Item;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.exception.NullArgumentException;
import com.theshop.exception.UserException;
import com.theshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class Validator {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    public void validateCartItems(List<Item> itemList) throws CartExceptionNotFound {
        if(itemList == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        for (int i = 0; i < itemList.size(); i++) {
            Optional<Item> item = itemService.getItem(itemList.get(i).getId());
            if(!item.isPresent()) {
                throw new CartExceptionNotFound(CartExceptionNotFound.ERR_ITEM_NOT_FOUND);
            }
        }
    }

    public void validateUser(Long userId) throws UserException, NullArgumentException {
        if (userId == null) {
            throw new NullArgumentException(NullArgumentException.ERR_ARGUMENTS_NULL);
        }
        if (!userService.getUser(userId).isPresent()) {
            throw new UserException(UserException.ERR_USER_NOT_FOUND);
        }
    }
}
