package com.theshop.controller;

public class OrderController {
}
/*
@PostMapping(value = "orderProcessor")
    public Order orderProcessor(long cartId) throws CartNotFoundException,OrderNotFoundException {
        Cart userCart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        User user = userCart.getUser();
        LOGGER.info("We proceesing order for:" + user.getUsername() + " with userKey:" + user.getUserKey());
        int productCount = 0;
        BigDecimal priceOfProducts = new BigDecimal(0);
        List<ProductItem> productsItems = new ArrayList<>();
        for (ProductItem productItem : userCart.getProductItems()) {
            productCount += productItem.getQuantity();
            priceOfProducts.add(productItem.getAmmount());
            productsItems.add(productItem);
        }
        LOGGER.info(user.getUsername() + " have " + productCount + "product in cart");
        Order resultOrder = new Order(LocalDate.now(), true, user, priceOfProducts);
        resultOrder.getProductItems().addAll(productsItems);
        resultOrder.setTrelloCardId(trelloClient.addOrderToList(resultOrder.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
        orderService.saveOrder(resultOrder);
        LOGGER.info("Amound to pay: " + priceOfProducts.toString());
        userCart.getProductItems().clear();
        return resultOrder;
    }

    @GetMapping("{id}/confirmation")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, Model model) throws OrderNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        OrderDto orderDto = orderMapper.mapToOrderDto(orderService.getOrder(id));
        Optional<User> user = userService.getUser(orderDto.getUserId());
        if(user.isPresent()){
            model.addAttribute("address", user.get().getAddress());
            model.addAttribute("email", user.get().getEmail());
            model.addAttribute("username", user.get().getUsername());
        }
        model.addAttribute("date", orderDto.getDate());
        model.addAttribute("orderList", orderDto.getProductList());
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
    @RequestMapping(value = "{id}/confirmation/download", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity downloadFile(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrder(id);
        InputStreamResource resource = documentGeneratorService.html2PdfGenerator(order);
        if (resource != null) {
            return ResponseEntity
                    .ok()
                    .body(resource);
        } else {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
 */