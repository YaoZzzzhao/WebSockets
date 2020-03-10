package org.demo.model;

public class UnionResult {
    private String name;
    private Long orders;

    public UnionResult(String name, Long orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "UnionResult{" +
                "name='" + name + '\'' +
                ", orders='" + orders + '\'' +
                '}';
    }
}
