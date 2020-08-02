package com.ecoverde.estateagency.repositories;

import com.ecoverde.estateagency.model.entity.Address;
import com.ecoverde.estateagency.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PropertyRepository extends JpaRepository<Property,String> {

    Optional<Property> findByAddress(Address address);

    @Query("SELECT p FROM Property p WHERE p.description LIKE CONCAT('%',:keyword,'%') ")
    List<Property> findAllByDescriptionContaining(@Param("keyword") String keyword);
}
