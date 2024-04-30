package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Plan;
import com.dev.BackFenixc.repository.PlanRepository;
import com.dev.BackFenixc.service.PlanService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan save(Plan plan) throws DataException {
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> getAll() {
        return (List<Plan>) planRepository.findAll();
    }

    @Override
    public Optional<Plan> getById(int id) {
        return planRepository.findById(id);
    }

    @Override
    public void delete(Plan plan) {
        planRepository.delete(plan);
    }
}
