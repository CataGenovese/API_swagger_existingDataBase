package com.example.demo.service.implementation;


import com.example.demo.persistence.dao.interfaces.IClienteDAO;
import com.example.demo.persistence.entity.ClienteEntity;
import com.example.demo.presentation.dto.ClienteDTO;
import com.example.demo.service.interfaces.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ClienteServiceImpl  implements IClienteService {

    @Autowired
    private IClienteDAO clienteDAO;


    @Override
    public List<ClienteDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return this.clienteDAO.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getById(Short id) {
        Optional<ClienteEntity> clienteEntity = this.clienteDAO.getById(id);
        if (clienteEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(clienteEntity.get(), ClienteDTO.class);
        } else {
            return new ClienteDTO();
        }
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ClienteEntity clienteEntity = modelMapper.map(clienteDTO, ClienteEntity.class);
            this.clienteDAO.save(clienteEntity);
            return modelMapper.map(clienteEntity, ClienteDTO.class); // <- más fiable

        } catch (Exception e) {
            // Loguear la excepción para obtener más detalles
            e.printStackTrace(); // Aquí podrías usar un logger para registrar la excepción.
            throw new UnsupportedOperationException("Error al guardar el cliente: " + e.getMessage());
        }
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO, Short id) {
        Optional<ClienteEntity> clienteEntity = this.clienteDAO.getById(id);
        if (clienteEntity.isPresent()) {
            ClienteEntity currentCliente = clienteEntity.get();
            currentCliente.setEmpresa(clienteDTO.getEmpresa());
            currentCliente.setRepClie(clienteDTO.getRepClie());
            currentCliente.setLimiteCredito(clienteDTO.getLimiteCredito());

            this.clienteDAO.update(currentCliente);

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentCliente, ClienteDTO.class);
        } else {
            throw new IllegalArgumentException("Error al actualizar el cliente porque no existe");
        }    }

    @Override
    public String delete(Short id) {
        Optional<ClienteEntity> clienteEntity = this.clienteDAO.getById(id);
        if (clienteEntity.isPresent()) {
            this.clienteDAO.delete(clienteEntity.get());
            return "Cliente con ID " + id + " ha sido eliminado correctamente";
        } else {
            return "Cliente con ID " + id + " no existe";
        }
    }
}


