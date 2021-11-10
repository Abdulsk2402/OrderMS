package com.team9.OrderMS.Repository;

import org.springframework.data.repository.CrudRepository;

import com.team9.OrderMS.Entity.ProductOrderedEntity;
import com.team9.OrderMS.Utility.CompoundKey;

public interface ProductOrderedRepository extends CrudRepository<ProductOrderedEntity, CompoundKey> {

}
