package com.ecoverde.estateagency.model.view;

import com.ecoverde.estateagency.model.entity.Address;
import com.ecoverde.estateagency.model.entity.PropertyType;

import java.math.BigDecimal;

public class PropertyViewModel {
    private PropertyType propertyType;
    private Address address;
    private String description;
    private BigDecimal price;

    public PropertyViewModel() {
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
