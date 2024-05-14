package com.example.demoDatabase.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class OrderDTOForSave {
    private String username;
    private HashMap<UUID, Integer> productQuatityList;
}
