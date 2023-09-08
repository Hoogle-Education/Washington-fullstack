package com.washington.ecomerce.resource.dto;

import com.washington.ecomerce.model.Produto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class ProdutoDto {
  // dto = data transfer object

  // se eu estiver tentando usar a API e enviar algum dado incorreto
  // os validadores abaixo irão atuar para informar o erro
  @NotEmpty(message = "o nome não deve estar vazio")
  private String nome;
  
  @NotEmpty(message = "o código não deve estar vazio")
  private String codigo;

  @NotEmpty(message = "o preco não deve estar vazio")
  private Double preco;

  public ProdutoDto(Produto produto){
    this.nome = produto.getNome();
    this.codigo = produto.getCodigo();
    this.preco = produto.getPreco();
  }

}
