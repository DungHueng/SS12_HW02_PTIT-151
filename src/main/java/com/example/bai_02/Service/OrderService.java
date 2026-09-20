package com.example.bai_02.Service;

import com.example.bai_02.Model.OrderCheckout;
import com.example.bai_02.Model.OrderRequest;
import com.example.bai_02.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    public OrderCheckout checkout(OrderRequest request) {

        // Xử lý đơn hàng chính
        // ...

        String recommendUrl =
                "http://recommendation-service/api/suggest?userId="
                        + request.getUserId();

        List<Product> suggestions;

        try {

            suggestions = restTemplate.getForObject(
                    recommendUrl,
                    List.class
            );

            if (suggestions == null) {
                suggestions = Collections.emptyList();
            }

        } catch (RestClientException e) {

            // Recommendation-Service timeout / lỗi HTTP
            suggestions = Collections.emptyList();
        }

        // Không phụ thuộc Recommendation-Service
        return new OrderCheckout(
                "Thành công",
                suggestions
        );
    }
}