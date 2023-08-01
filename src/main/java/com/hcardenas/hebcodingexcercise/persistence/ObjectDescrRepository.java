package com.hcardenas.hebcodingexcercise.persistence;

import com.hcardenas.hebcodingexcercise.model.persistence.ObjectDescr;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ObjectDescrRepository extends CrudRepository<ObjectDescr, Long> {

}
