package com.ecoverde.estateagency.service;


import com.ecoverde.estateagency.model.entity.Address;
import com.ecoverde.estateagency.model.entity.Property;
import com.ecoverde.estateagency.model.service.AddressServiceModel;
import com.ecoverde.estateagency.model.service.PropertyServiceModel;
import com.ecoverde.estateagency.model.view.PropertyViewModel;

import java.util.List;
import java.util.Set;

public interface PropertyService {
    void propertiesInit();

    PropertyServiceModel findByAddress(AddressServiceModel address);

    List<PropertyViewModel> findByKeyword(String keyword);

    List<PropertyViewModel> findAllProperties();

    PropertyServiceModel addProperty(PropertyServiceModel propertyServiceModel);
}
