package com.eric.spring.boot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eric.spring.boot.collections.Items;

/**
 * 
 * @author Shahnawaz
 *
 */
@Repository
public interface ItemsRepository extends CrudRepository<Items, String> {

}
