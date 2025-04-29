package com.example.demo.persistence.dao.interfaces;


import com.example.demo.persistence.entity.ClienteEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IClienteDAO {
    List<ClienteEntity> getAll();

    @Transactional(readOnly = true)
    Optional<ClienteEntity> getById(Short id);

    void save(ClienteEntity clienteEntity);

    void update(ClienteEntity clienteEntity);

    void delete(ClienteEntity clienteEntity);

}
