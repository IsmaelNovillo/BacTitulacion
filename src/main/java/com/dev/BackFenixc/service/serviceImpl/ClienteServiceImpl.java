package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Cliente;
import com.dev.BackFenixc.repository.ClienteRepository;
import com.dev.BackFenixc.service.ClienteService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) throws DataException {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getById(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
