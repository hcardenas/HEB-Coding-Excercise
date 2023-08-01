package com.hcardenas.hebcodingexcercise.persistence;

import com.hcardenas.hebcodingexcercise.model.persistence.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends CrudRepository<Image, UUID> {

    List<Image> findAll();

}
