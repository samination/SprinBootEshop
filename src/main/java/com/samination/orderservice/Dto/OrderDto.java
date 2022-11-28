package com.samination.orderservice.Dto;


import com.samination.orderservice.Model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private List<OrderLineItems> items;
}
