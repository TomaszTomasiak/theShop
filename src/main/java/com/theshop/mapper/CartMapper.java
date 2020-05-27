package com.theshop.mapper;

import com.theshop.dao.OrderDao;
import com.theshop.dao.UserDao;
import com.theshop.domain.Cart;
import com.theshop.domain.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

   public Cart mapToCart(final CartDto cartDto) {
       Cart cartBean = new Cart();
       cartBean.setId(cartDto.getId());
       cartBean.setItems(cartDto.getItems());
       cartBean.setOrder(orderDao.findById(cartDto.getOrderId()).orElse(null));
       cartBean.setUser(userDao.findById(cartDto.getUserId()).orElse(null));
       return cartBean;
   }

    public CartDto mapToCartDto(final Cart cart) {
        CartDto cartBean = new CartDto();
        cartBean.setId(cart.getId());
        cartBean.setItems(cart.getItems());
        cartBean.setOrderId(cart.getOrder().getId());
        cartBean.setOrderId(cart.getUser().getId());
        return cartBean;
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(cart -> mapToCartDto(cart))
                .collect(Collectors.toList());
    }

    public List<Cart> mapToCartList(final List<CartDto> cartDtoList) {
       return cartDtoList.stream()
               .map(cartDto -> mapToCart(cartDto))
               .collect(Collectors.toList());
    }
}
