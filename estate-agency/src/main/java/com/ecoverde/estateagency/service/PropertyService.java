package com.ecoverde.estateagency.service;


import com.ecoverde.estateagency.model.binding.PropertyAddBindingModel;
import com.ecoverde.estateagency.model.binding.PropertySearchModel;
import com.ecoverde.estateagency.model.entity.Address;
import com.ecoverde.estateagency.model.entity.Property;
import com.ecoverde.estateagency.model.service.AddressServiceModel;
import com.ecoverde.estateagency.model.service.PropertyServiceModel;
import com.ecoverde.estateagency.model.view.PropertyViewModel;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface PropertyService {
    void propertiesInit();

    PropertyServiceModel findByPropertyName(String propertyName);

    PropertyServiceModel mapBindingModelToService(PropertyAddBindingModel propertyAddBindingModel) throws IOException;

    Set<PropertyViewModel> findByKeyword(String keyword);

    Set<PropertyViewModel> findAllByPropertyType(String propertyType);

    Set<PropertyViewModel> findAllByTownOrAddress(String townOrAddress);

    Set<PropertyViewModel> findAllByPrice(BigDecimal price);

    List<PropertyViewModel> findAllProperties();

    Set<PropertyViewModel> findAllProperties(PropertySearchModel propertySearchModel);

    PropertyServiceModel addProperty(PropertyServiceModel propertyServiceModel);
}
