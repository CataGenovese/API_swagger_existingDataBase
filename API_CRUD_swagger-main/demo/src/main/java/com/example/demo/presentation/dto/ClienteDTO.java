package com.example.demo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
//creamos dto para poder mapear la info que nos envia el front-end
public class ClienteDTO {

    private Short numClie;
    private String empresa;
    private Short repClie;
    private BigDecimal limiteCredito;

}



