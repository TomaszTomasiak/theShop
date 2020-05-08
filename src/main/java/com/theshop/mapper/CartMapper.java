package com.theshop.mapper;

import com.theshop.domain.Cart;
import com.theshop.domain.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

   public Cart mapToCart(final CartDto cartDto) {
       return new Cart(
               cartDto.getId(),
               cartDto.getItems()
       );
   }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getItems()
        );
    }

    public List<CartDto> mapToListCartDto(final List<Cart> cartList){
        return cartList.stream()
                .map(cart -> mapToCartDto(cart))
                .collect(Collectors.toList());
    }

    public List<Cart> mapToListCart(final List<CartDto> cartDtoList) {
       return cartDtoList.stream()
               .map(cartDto -> mapToCart(cartDto))
               .collect(Collectors.toList());
    }
}
