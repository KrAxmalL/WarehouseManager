package org.example.Models;

public class Product {

    private int id;
    private String name;
    private String producer;
    private int categoryId;
    private int amount;
    private int price;

    public Product() {}

    public Product(String name, String producer, int categoryId, int amount, int price) {
        this.name = name;
        this.producer = producer;
        this.categoryId = categoryId;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }

    public String toJson() {
        return "\"product\"{" +
                "\"id\": " + id + ',' +
                "\"name\": " + '"' + name + '"' + ',' +
                "\"producer\": " + '"' + producer + '"' +  ',' +
                "\"categoryId\": " + categoryId + ',' +
                "\"amount\": " + amount + ',' +
                "\"price\": " + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(this == o) {
            return true;
        }

        if(o instanceof Product) {
            Product other = (Product) o;
            return  this.getId() == other.getId() &&
                    this.getName().equals(other.getName()) &&
                    this.getProducer().equals(other.getProducer()) &&
                    this.getPrice() == other.getPrice() &&
                    this.getAmount() == other.getAmount();
        }

        return false;
    }
}
