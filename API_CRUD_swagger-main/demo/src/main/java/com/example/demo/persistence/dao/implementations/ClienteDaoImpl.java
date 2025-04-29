package com.example.demo.persistence.dao.implementations;

import com.example.demo.persistence.dao.interfaces.IClienteDAO;
import com.example.demo.persistence.entity.ClienteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDaoImpl implements IClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<ClienteEntity> getAll() {
        return this.em.createQuery("SELECT c FROM ClienteEntity c", ClienteEntity.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ClienteEntity> getById(Short id) {
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, id);
        return Optional.ofNullable(clienteEntity);    }

    @Override
    @Transactional
    public void save(ClienteEntity clienteEntity) {
        if (clienteEntity.getNumClie() == null) {
            em.persist(clienteEntity); // Si es nuevo
        } else {
            em.merge(clienteEntity); // Si existe, se actualiza
        }
    }

    @Override
    @Transactional
    public void update(ClienteEntity clienteEntity) {
        this.em.merge(clienteEntity);
    }
    @Override
    @Transactional
    public void delete(ClienteEntity clienteEntity) {
        ClienteEntity managedEntity = em.merge(clienteEntity); // ¡Necesario!
        this.em.remove(managedEntity); // remove() solo funciona con entidades "gestionadas"
    }
}
