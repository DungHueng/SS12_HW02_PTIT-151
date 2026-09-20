package com.example.bai_02.Model;

import java.util.List;

public class OrderCheckout {
    private String message;
    private List<Product> suggestions;

    public OrderCheckout(String message, List<Product> suggestions) {
        this.message = message;
        this.suggestions = suggestions;
    }

    public void OrderCheckoutResponse(
            String message,
            List<Product> suggestions
    ) {
        this.message = message;
        this.suggestions = suggestions;
    }

    public String getMessage() {
        return message;
    }

    public List<Product> getSuggestions() {
        return suggestions;
    }
}
