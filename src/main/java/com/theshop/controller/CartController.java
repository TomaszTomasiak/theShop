package com.theshop.controller;

import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.dto.CartDto;
import com.theshop.exception.*;
import com.theshop.mapper.CartMapper;
import com.theshop.service.CartService;
import com.theshop.service.TheShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {
    @Autowired
    private CartService service;

    @Autowired
    private TheShopService theShopService;

    @Autowired
    private CartMapper mapper;


    private final Logger log = LoggerFactory.getLogger(CartController.class);

    @GetMapping
    public List<CartDto> getAllCarts() {
        log.debug("REST request to get all carts");
        return mapper.mapToCartDtoList(service.getCarts());
    }

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get cart with id: {}", id);
        return mapper.mapToCartDto(service.getCart(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public CartDto createNewCart(@RequestBody CartDto cartDto) throws CartExceptionBadRequest, UserException, NullArgumentException {
            if(cartDto == null) {
                throw new IllegalArgumentException("Some passed arguments are equal null");
            }
            log.debug("REST request to create new cart: {}", cartDto);
            mapper.mapToCartDto(theShopService.saveNewCart(mapper.mapToCart(cartDto)));
            return cartDto;
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCartById(@PathVariable("id") Long cartId, @RequestBody CartDto cartDto){
        log.debug("REST request to update cart with id: {}", cartId);
        return mapper.mapToCartDto(service.saveCart(mapper.mapToCart(cartDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable("id") long id) {
        log.debug("REST request to delete cart with id: {}", id);
        service.deleteCart(id);
    }

    @PutMapping(value = "/{id}/item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto addItemToCart(@PathVariable("id") long cartId, @RequestBody Item item) throws CartExceptionNotFound {
        return mapper.mapToCartDto(theShopService.addItemToCard(cartId, item));
    }

    @PutMapping(value = "/{cartId}&{itemId}")
    public CartDto removeItemFromCart(@PathVariable("cartId") long cartId, @PathVariable("itemId") long itemId) throws CartExceptionNotFound {
        log.debug("REST request to delete item with id: {} from the cart with id: {}", itemId, cartId);
        return mapper.mapToCartDto(theShopService.removeItemFromTheCard(cartId,itemId));
    }

    @PutMapping(value = "/{cartId}&{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateItemFromCart(@PathVariable("cartId") long cartId, @PathVariable("itemId") long itemId, @RequestBody Item item) throws CartExceptionNotFound {
        return mapper.mapToCartDto(theShopService.updateItemFromTheCart(cartId, itemId, item));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto addUpdateDeleteItemsFromCart(@PathVariable("id") Long cartId, @RequestBody Cart cart) throws CartExceptionNotFound, UserException, NullArgumentException, CartExceptionBadRequest {
        if(cart == null ^ cartId == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        if(cart.getId() != cartId) {
            throw new CartExceptionBadRequest(CartExceptionBadRequest.ERR_CART_PATH);
        }

        if(!service.exists(cartId)) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }
//        cart.setId(cartId);
//        Cart cartUpdated = theShopService.addItemsToExistingCart(cart);
        return mapper.mapToCartDto(theShopService.updateItemsOnExistingCart(cart));
    }
}

