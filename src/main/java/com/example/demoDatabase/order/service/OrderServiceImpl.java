package com.example.demoDatabase.order.service;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.order.dto.OrderDTO;
import com.example.demoDatabase.order.dto.OrderDTOForSave;
import com.example.demoDatabase.order.model.Order;
import com.example.demoDatabase.order.model.OrderProduct;
import com.example.demoDatabase.order.repository.OrderProductRepository;
import com.example.demoDatabase.order.repository.OrderRepository;
import com.example.demoDatabase.product.model.Product;
import com.example.demoDatabase.product.repository.ProductRepository;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    WBHMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository, WBHMapper mapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDTO> findByUsername(String username) {
        return orderRepository.findAllByUsername(username)
                .stream().map(order -> mapper.map(order, OrderDTO.class))
                .toList();
    }

    @Override
    public OrderDTO save(OrderDTOForSave orderDTOForSave) {
        User user = userRepository.findByUsername(orderDTOForSave.getUsername())
                .orElseThrow(() -> new WBHBussinessExeption("User not found"));
        Set<OrderProduct> orderProductSet = new LinkedHashSet<>();
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        Order newOrder = new Order();
        HashMap<UUID, Integer> productQuantityMap = orderDTOForSave.getProductQuatityList();
        Set<UUID> productIdSet = productQuantityMap.keySet();
        productIdSet.forEach(
                productID -> {
                    Optional<Product> productOptional = productRepository.findById(productID);
                    if (productOptional.isEmpty()) return;
                    Product product = productOptional.get();
                    int quantity = productQuantityMap.get(productID);
                    OrderProduct orderProduct = OrderProduct.builder()
                            .product(product)
                            .order(newOrder)
                            .quantity(quantity).build();
                    orderProductSet.add(orderProduct);
                    product.getOrderProducts().add(orderProduct);
                    totalPrice[0] = totalPrice[0].add(
                            BigDecimal.valueOf(product.getPrice())
                                    .multiply(BigDecimal.valueOf(quantity))
                    );
                }
        );
        if (orderProductSet.isEmpty()) throw new WBHBussinessExeption("All inputed products is not found");
        newOrder.setUser(user);
        newOrder.setTotalPrice(totalPrice[0]);
        newOrder.setOrderProducts(orderProductSet);
        return mapper.map(orderRepository.save(newOrder), OrderDTO.class);
    }

    @Override
    public OrderDTO addProducts(UUID orderID, Map<UUID, Integer> productQuantitys) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessExeption("Order not found"));
        productQuantitys.keySet().forEach((productID) -> {
            Optional<Product> productOptional = productRepository.findById(productID);
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            int quantity = productQuantitys.get(productID);
            Optional<OrderProduct> orderProductOptional = orderProductRepository.findByProductIDAndOrderID(productID, orderID);
            OrderProduct orderProduct;
            if (orderProductOptional.isEmpty()) {
                orderProduct = OrderProduct.builder()
                        .quantity(quantity)
                        .product(product)
                        .order(order)
                        .build();
            } else {
                orderProduct = orderProductOptional.get();
                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
            }
            product.getOrderProducts().add(orderProduct);
            order.getOrderProducts().add(orderProduct);
            order.setTotalPrice(order.getTotalPrice()
                    .add(BigDecimal.valueOf(product.getPrice())
                            .multiply(BigDecimal.valueOf(quantity)))
            );
        });
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public OrderDTO removeProducts(UUID orderID, Map<UUID, Integer> productQuantitys) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessExeption("Order not found"));
        productQuantitys.keySet().forEach((productID) -> {
            Optional<Product> productOptional = productRepository.findById(productID);
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            int quantity = productQuantitys.get(productID);
            Optional<OrderProduct> orderProductOptional = orderProductRepository.findByProductIDAndOrderID(productID, orderID);
            if (orderProductOptional.isEmpty()) return;
            OrderProduct orderProduct = orderProductOptional.get();
            if (orderProduct.getQuantity() > quantity) {
                orderProduct.setQuantity(orderProduct.getQuantity() - quantity);
                order.setTotalPrice(order.getTotalPrice()
                        .subtract(BigDecimal.valueOf(product.getPrice())
                                .multiply(BigDecimal.valueOf(quantity)))
                );
            } else {
                product.getOrderProducts().remove(orderProduct);
                order.getOrderProducts().remove(orderProduct);
                order.setTotalPrice(order.getTotalPrice()
                        .subtract(BigDecimal.valueOf(product.getPrice())
                                .multiply(BigDecimal.valueOf(orderProduct.getQuantity())))
                );
            }

        });
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public JpaRepository<Order, UUID> getRepository() {
        return orderRepository;
    }

    @Override
    public WBHMapper getMapper() {
        return mapper;
    }

}
