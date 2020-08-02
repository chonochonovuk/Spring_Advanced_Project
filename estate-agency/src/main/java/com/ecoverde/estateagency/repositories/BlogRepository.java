package com.ecoverde.estateagency.repositories;

import com.ecoverde.estateagency.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,String> {
    Optional<Blog> findByTitle(String title);
}