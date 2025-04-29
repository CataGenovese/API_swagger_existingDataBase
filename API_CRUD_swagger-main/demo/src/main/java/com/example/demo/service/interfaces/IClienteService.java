package com.example.demo.service.interfaces;

import com.example.demo.presentation.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> getAll();
    ClienteDTO getById(Short id);
    ClienteDTO create(ClienteDTO clienteDTO);
    ClienteDTO update(ClienteDTO clienteDTO, Short id);
    String delete(Short id);
}
