package com.theshop.validator;

import com.theshop.domain.Item;
import com.theshop.domain.Product;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.service.CartService;
import com.theshop.service.OrderService;
import com.theshop.service.ProductService;
import com.theshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Validator {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private void validateCartItems(List<Item> itemList) throws CartExceptionNotFound {
        if(itemList == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        for (int i = 0; i < itemList.size(); i++) {
            Optional<Product> product = productService.getProduct(itemList.get(i).getProduct().getId());
            if(!product.isPresent()) {
                throw new CartExceptionNotFound(CartExceptionNotFound.ERR_PRODUCT_NOT_FOUND);
            }
        }
    }


}
