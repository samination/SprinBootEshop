package com.samination.orderservice.Controller;

import com.samination.orderservice.Model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping

    public void placeOrder(@RequestBody Order order)
    {

    }
}
