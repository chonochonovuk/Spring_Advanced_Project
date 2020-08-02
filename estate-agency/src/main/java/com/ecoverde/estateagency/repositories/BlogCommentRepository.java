package com.ecoverde.estateagency.repositories;

import com.ecoverde.estateagency.model.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment,String> {
    Optional<BlogComment> findByTitle(String title);
}
