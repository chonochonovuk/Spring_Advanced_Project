package com.ecoverde.estateagency.web;

import com.ecoverde.estateagency.model.binding.AjaxResponseBody;
import com.ecoverde.estateagency.model.binding.PropertySearchModel;
import com.ecoverde.estateagency.model.view.PropertyViewModel;
import com.ecoverde.estateagency.service.AddressService;
import com.ecoverde.estateagency.service.PropertyService;
import com.ecoverde.estateagency.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final PropertyService propertyService;
    private final AddressService addressService;
    private final TownService townService;
    private final ModelMapper modelMapper;

    @Autowired
    public SearchController(PropertyService propertyService, AddressService addressService, TownService townService, ModelMapper modelMapper) {
        this.propertyService = propertyService;
        this.addressService = addressService;
        this.townService = townService;
        this.modelMapper = modelMapper;
    }

   @PostMapping
    public ResponseEntity<AjaxResponseBody> getSearchResultWithAjax(@RequestBody PropertySearchModel propertySearchModel){
        AjaxResponseBody result = new AjaxResponseBody();
        List<PropertyViewModel> findProperty = this.findAllProperties(propertySearchModel);

       if (findProperty.isEmpty()){
          result.setMessage("No matching results!!!");
       }else {
           result.setMessage("Success");
           result.setResult(findProperty);
       }
        return ResponseEntity.ok(result);
   }

    private List<PropertyViewModel> findAllProperties(PropertySearchModel propertySearchModel) {
        Set<PropertyViewModel> matchingProperties = new TreeSet<>(Comparator.comparing(PropertyViewModel::getPropertyName));
        if (!propertySearchModel.getKeyword().isEmpty()){
            matchingProperties.addAll(this.propertyService.findByKeyword(propertySearchModel.getKeyword()));
        }
        if (!propertySearchModel.getPropertyType().isEmpty()){
            matchingProperties.addAll(this.propertyService.findAllByPropertyType(propertySearchModel.getPropertyType()));
        }
        if (!propertySearchModel.getLocation().isEmpty()){
            matchingProperties.addAll(this.propertyService.findAllByTownOrAddress(propertySearchModel.getLocation()));
        }

        if (propertySearchModel.getPrice().compareTo(BigDecimal.ZERO) > 0){
            matchingProperties.addAll(this.propertyService.findAllByPrice(propertySearchModel.getPrice()));
        }

        List<PropertyViewModel> all = new ArrayList<>();
        all.addAll(matchingProperties);

        return all;
    }
}
