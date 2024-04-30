package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Plan;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    Plan save (Plan plan) throws DataException;

    List<Plan> getAll();

    Optional<Plan> getById(int id);


    void delete (Plan plan);
}
