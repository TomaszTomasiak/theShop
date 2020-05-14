package com.theshop.service;

import com.theshop.dao.CartDao;
import com.theshop.dao.ItemDao;
import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.Product;
import com.theshop.exception.CartExceptionNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartService {

    private final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    ProductService productService;

    @Autowired
    ItemService itemService;

    public List<Cart> getCarts() {
        log.debug("Request to get all carts");
        return cartDao.findAll();
    }

    public Optional<Cart> getCart(long id) {
        log.debug("Request to get cart with id {} = ", id);
        return cartDao.findById(id);
    }

    public Cart saveCart(Cart cart) {
        log.debug("Request to create new cart: {}", cart);
        return cartDao.save(cart);
    }

    public void deleteCart(long id) {
        log.debug("Request to delete cart with id :{}", id);
        cartDao.deleteById(id);
    }

    public Cart addItemToCard(long cartId, long itemId) throws CartExceptionNotFound {
        log.debug("Request to add item with id {} to cart with id: {} ", itemId, cartId);
        Cart cart = cartDao.findById(cartId).orElse(null);
        //validateCartItems(cart.getItems());
        cart.getItems().add(itemDao.findById(itemId).orElse(null));
        saveCart(cart);
        return cart;
    }

    public Cart removeItemFromCard( long cartId, long itemId) throws CartExceptionNotFound{
        log.debug("Request to delete item with id {} from cart with id: {} ", itemId, cartId);
        Cart cart = cartDao.findById(cartId).orElse(null);
        validateCartItems(cart.getItems());
        Optional<Item> itemOptional = itemDao.findById(itemId);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.getItems().remove(item);
        }

        saveCart(cart);
        return cart;
    }

    public Cart updateQuantityOfItemOnCard( long cartId, long itemId, int quantity) throws CartExceptionNotFound{
        log.debug("Request to update item with id: {} from cart with id: {} ", itemId, cartId);
        Cart cart = cartDao.findById(cartId).orElse(null);
        validateCartItems(cart.getItems());
        Optional<Item> itemOptional = itemDao.findById(itemId);
        if (itemOptional.isPresent()) {
            Item itemToUpdate = itemOptional.get();

        }

        cartDao.save(cart);
        return cart;
    }

    public boolean exists(Long id) {
        if(!cartDao.findById(id).isPresent()) {
            return false;
        }
        return true;
    }

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
