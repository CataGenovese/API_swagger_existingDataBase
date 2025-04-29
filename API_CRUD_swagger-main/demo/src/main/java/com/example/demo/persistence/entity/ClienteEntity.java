package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "clientes")
public class ClienteEntity {

    @Id
    @Column(name = "num_clie")
    private Short numClie;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "rep_clie")
    private Short repClie;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;


}
