package com.uaic.lab2.model;

public class Device {

    private CategoryType category;
    private String key;
    private String value;

    public Device(CategoryType category, String key, String value) {
        this.category = category;
        this.key = key;
        this.value = value;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
