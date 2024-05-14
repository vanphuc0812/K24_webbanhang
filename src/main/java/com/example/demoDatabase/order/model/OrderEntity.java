package com.example.demoDatabase.order.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderEntity {
    public static final String TABLE_NAME = "WBH_ORDER";

    //    public static final String ID = "id";
//    public static final String ID = "id";
//    public static final String ID = "id";
    public class OrderUser {
        public static final String TABLE_NAME = "WBH_ORDER_USER";
        public static final String USER_ID = "USER_ID";
        public static final String ORDER_ID = "ORDER_ID";
        public static final String ORDER_MAPPED_USER = "user";
    }

    public class OrderProduct {
        public static final String TABLE_NAME = "WBH_ORDER_PRODUCT";
        public static final String ORDER_MAPPED_ORDERPRODUCT = "order";
        public static final String PRODUCT_MAPPED_ORDERPRODUCT = "product";
    }
}
