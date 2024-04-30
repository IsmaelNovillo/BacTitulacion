package com.dev.BackFenixc.JWT.repositories;

import com.dev.BackFenixc.JWT.models.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<RolEntity,Long> {

}
