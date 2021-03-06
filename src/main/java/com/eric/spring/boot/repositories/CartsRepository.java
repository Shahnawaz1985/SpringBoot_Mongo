package com.eric.spring.boot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eric.spring.boot.collections.Carts;





/**
 * 
 * @author Shahnawaz
 *
 */
@Repository
public interface CartsRepository extends CrudRepository<Carts, String> {

}
