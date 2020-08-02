package com.ecoverde.estateagency.web;

import com.ecoverde.estateagency.service.AddressService;
import com.ecoverde.estateagency.service.PropertyService;
import com.ecoverde.estateagency.service.PropertyTypeService;
import com.ecoverde.estateagency.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyTypeService propertyTypeService;
    private final AddressService addressService;
    private final TownService townService;
    private final ModelMapper modelMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyTypeService propertyTypeService, AddressService addressService, TownService townService, ModelMapper modelMapper) {
        this.propertyService = propertyService;
        this.propertyTypeService = propertyTypeService;
        this.addressService = addressService;
        this.townService = townService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/property")
    public String properties(Model model){

        model.addAttribute("allPropertyTypes",this.propertyTypeService.findAllTypes());
        return "properties";
    }

    @GetMapping("/single")
    public String propertySingle(Model model){

        return "properties-single";
    }
}
