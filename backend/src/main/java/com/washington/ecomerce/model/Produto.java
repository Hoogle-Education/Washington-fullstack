package com.washington.ecomerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Document // é um documento do mongodb
@Getter // escreve os getters para todos os atributos
@Setter // escreve setters
@AllArgsConstructor // faz um construtor com todos os argumentos
@NoArgsConstructor // faz um construtor sem argumentos
@Builder // forma alternativa de criar produtos
public class Produto { // camada 01 - POJO (Plain Old Java Objetcs)
// modela a entidade que irá atuar por todo o meu programa

  @Id // indica que o código é uma chave primária para Produtos
  @JsonProperty("codigo") // especifica o nome do campo para sair no json
  private String codigo;

  @JsonProperty("nome")
  private String nome;

  @JsonProperty("preco")
  private Double preco;

}