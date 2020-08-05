package com.ecoverde.estateagency.service.impl;

import com.ecoverde.estateagency.model.binding.PropertyAddBindingModel;
import com.ecoverde.estateagency.model.entity.Address;
import com.ecoverde.estateagency.model.entity.Image;
import com.ecoverde.estateagency.model.entity.Property;
import com.ecoverde.estateagency.model.service.*;
import com.ecoverde.estateagency.model.view.PropertyViewModel;
import com.ecoverde.estateagency.repositories.PropertyRepository;
import com.ecoverde.estateagency.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTypeService propertyTypeService;
    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;
    private final AddressService addressService;
    private final TownService townService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyTypeService propertyTypeService, ImageService imageService, CloudinaryService cloudinaryService, AddressService addressService, TownService townService, UserService userService, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyTypeService = propertyTypeService;
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
        this.addressService = addressService;
        this.townService = townService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void propertiesInit() {

        if (this.propertyRepository.count() == 0){

            TownServiceModel t2 = new TownServiceModel();
            t2.setName("Burgas");
            AddressServiceModel addr2 = new AddressServiceModel();
            addr2.setArea("Sveti Vlas");
            addr2.setFullAddress("13 Pirin street");
            PropertyTypeServiceModel pt2 = new PropertyTypeServiceModel();
            pt2.setTypeName("House");
            PropertyServiceModel prop2 = new PropertyServiceModel();
            prop2.setPropertyName("Sveti Vlas Pirin");
            prop2.setPropertyTypeServiceModel(pt2);
            prop2.setTownServiceModel(t2);
            prop2.setAddressServiceModel(addr2);
            prop2.setDescription("This is a beautiful and spacious three bedrooms house with spectacular " +
                    "sea views across the bay to the ancient town of Nessebar." +
                    " It is situated in the beautiful and salubrious 'Garden of Eden'," +
                    " in the upmarket town of St Vlas.");
            prop2.setPrice(new BigDecimal( 125000));
            prop2.setBathrooms(2);
            prop2.setRooms(5);
            prop2.setDate(LocalDate.parse("2020-07-17", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            prop2.setYear(2006);
            prop2.setSize(242);
            Image img3 = new Image();
            img3.setUrl("/images/burgas/stVlas/house1.jpg");
            prop2.setPhotos(img3);
            prop2.setOwner(this.userService.findByUsername("jana66"));
            this.addProperty(prop2);




            TownServiceModel town1 = new TownServiceModel();
            town1.setName("Sofia");
            AddressServiceModel addr1 = new AddressServiceModel();
            addr1.setArea("Center");
            addr1.setFullAddress("97 Knyaginya Maria Luiza boulevard, third floor, apt. number 12");
            PropertyTypeServiceModel ptsm = new PropertyTypeServiceModel();
            ptsm.setTypeName("Apartment");
            PropertyServiceModel prop1 = new PropertyServiceModel();
            prop1.setPropertyName("sofia center ml");
            prop1.setPropertyTypeServiceModel(ptsm);
            prop1.setTownServiceModel(town1);
            prop1.setAddressServiceModel(addr1);
            prop1.setDescription("Apartment in the heart of Sofia with two bedrooms and a balcony!");
            prop1.setPrice(new BigDecimal(65000));
            prop1.setBathrooms(1);
            prop1.setRooms(2);
            prop1.setDate(LocalDate.parse("2020-06-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            prop1.setYear(2002);
            prop1.setSize(116);
            Image img = new Image();
            img.setUrl("/images/sofia/center/marialuiza/255197956.jpg");
            prop1.setPhotos(img);
            prop1.setOwner(this.userService.findByUsername("ivan56"));
           this.addProperty(prop1);


        }




    }

    @Override
    public PropertyServiceModel findByPropertyName(String propertyName) {
        return this.propertyRepository.findByPropertyName(propertyName)
                .map(property -> this.modelMapper.map(property,PropertyServiceModel.class))
                .orElse(null);
    }

    @Override
    public PropertyServiceModel mapBindingModelToService(PropertyAddBindingModel propertyAddBindingModel) throws IOException {
        PropertyServiceModel psm = new PropertyServiceModel();
        if (propertyAddBindingModel != null){
            this.userService.userSetRole(propertyAddBindingModel.getUsername(),"ROLE_OWNER");
            psm.setOwner(this.userService.findByUsername(propertyAddBindingModel.getUsername()));
            TownServiceModel tsm1 = new TownServiceModel();
            tsm1.setName(propertyAddBindingModel.getTown());
            psm.setTownServiceModel(tsm1);
            AddressServiceModel asm1 = new AddressServiceModel();
            asm1.setArea(propertyAddBindingModel.getArea());
            asm1.setFullAddress(propertyAddBindingModel.getFullAddress());
            psm.setAddressServiceModel(asm1);
            PropertyTypeServiceModel ptsm1 = new PropertyTypeServiceModel();
            ptsm1.setTypeName(propertyAddBindingModel.getPropertyType());
            psm.setPropertyTypeServiceModel(ptsm1);
            psm.setPropertyName(propertyAddBindingModel.getPropertyName());
            psm.setDescription(propertyAddBindingModel.getDescription());
            psm.setPrice(propertyAddBindingModel.getPrice());
            psm.setBathrooms(propertyAddBindingModel.getBathrooms());
            psm.setRooms(propertyAddBindingModel.getRooms());
            psm.setDate(LocalDate.now());
            psm.setYear(propertyAddBindingModel.getYear());
            psm.setSize(propertyAddBindingModel.getSize());
            Image propImage = new Image();
            propImage.setUrl(this.cloudinaryService.uploadImage(propertyAddBindingModel.getImageUrl()));
            psm.setPhotos(propImage);
        }

        return psm;
    }

    @Override
    public List<PropertyViewModel> findByKeyword(String keyword) {
        return this.propertyRepository.findAllByDescriptionContaining(keyword).stream()
                .map(property -> this.modelMapper.map(property,PropertyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyViewModel> findAllByPropertyType(String propertyType) {
        return this.propertyRepository.findAllByPropertyType(propertyType).stream()
                .map(property -> this.modelMapper.map(property,PropertyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyViewModel> findAllByTownOrAddress(String townOrAddress) {
        return this.propertyRepository.findAllByTownOrAddress(townOrAddress).stream()
                .map(property -> this.modelMapper.map(property,PropertyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyViewModel> findAllByPrice(BigDecimal price) {
        return this.propertyRepository.findAllByPrice(price).stream()
                .map(property -> this.modelMapper.map(property,PropertyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyViewModel> findAllProperties() {
        return this.propertyRepository.findAll().stream()
                .map(property -> this.modelMapper.map(property,PropertyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyServiceModel addProperty(PropertyServiceModel propertyServiceModel) {

            this.propertyTypeService.addPropertyType(propertyServiceModel.getPropertyTypeServiceModel());
            propertyServiceModel.setPropertyTypeServiceModel(this.propertyTypeService
                    .findByTypename(propertyServiceModel
                            .getPropertyTypeServiceModel().getTypeName()));
            this.townService.addTown(propertyServiceModel.getTownServiceModel());
            propertyServiceModel.setTownServiceModel(this.townService
                    .findByName(propertyServiceModel
                            .getTownServiceModel().getName()));
            this.addressService.addAddress(propertyServiceModel.getAddressServiceModel());
            propertyServiceModel.setAddressServiceModel(this.addressService.findByFullAddress(
                    propertyServiceModel.getAddressServiceModel().getFullAddress()));

            if (propertyServiceModel.getPhotos() != null){
                this.imageService.addImage(propertyServiceModel.getPhotos());
                propertyServiceModel.setPhotos(this.imageService.findByUrl(propertyServiceModel.getPhotos().getUrl()));
            }else {
                propertyServiceModel.setPhotos(this.imageService.findByUrl("/images/burgas/stVlas/house1.jpg"));
            }

        propertyServiceModel.setOwner(this.userService.findByUsername(propertyServiceModel.getOwner().getUsername()));
        Property property = this.modelMapper.map(propertyServiceModel,Property.class);
        if (this.findByPropertyName(propertyServiceModel.getPropertyName()) == null){
            this.propertyRepository.saveAndFlush(property);
        }

        return this.modelMapper.map(property,PropertyServiceModel.class);
    }
}
