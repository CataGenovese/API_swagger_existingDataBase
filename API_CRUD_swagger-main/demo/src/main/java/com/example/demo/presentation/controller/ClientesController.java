package com.example.demo.presentation.controller;

import com.example.demo.presentation.dto.ClienteDTO;
import com.example.demo.service.interfaces.IClienteService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private IClienteService clienteService;


    //Get All
    @GetMapping("/get")
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return new ResponseEntity<>(this.clienteService.getAll(), HttpStatus.OK);
    }

    //Get by Id
    @GetMapping("/get/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Short id) {
        return new ResponseEntity<>(this.clienteService.getById(id), HttpStatus.OK);
    }

    //Create user
    //para crear un usuario ->
    // frontend envia info a controller y convierte el json a un objeto (DTO)
    @PostMapping("/create")
    public ResponseEntity<ClienteDTO> createUser(@RequestBody ClienteDTO userDTO) {
        return new ResponseEntity<>(this.clienteService.create(userDTO), HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteDTO> updateUser(@RequestBody ClienteDTO userDTO, @PathVariable Short id){
        return new ResponseEntity<>(this.clienteService.update(userDTO, id), HttpStatus.OK);

    }

    //Delete User - User deleted
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Short id) {
        return new ResponseEntity<>(this.clienteService.delete(id), HttpStatus.NO_CONTENT);
    }
}
