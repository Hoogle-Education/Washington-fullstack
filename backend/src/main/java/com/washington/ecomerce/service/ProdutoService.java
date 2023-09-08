package com.washington.ecomerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.washington.ecomerce.model.Produto;
import com.washington.ecomerce.repository.ProdutoRepository;
import com.washington.ecomerce.resource.dto.ProdutoDto;

import lombok.AllArgsConstructor;

@Service // está fortemente atrelado às regras de negócio do projeto
@AllArgsConstructor // cria o construtor e realiza a injeção de dependência
public class ProdutoService {

  private ProdutoRepository produtoRepository;

  // retorna todos os produtos do banco de dados
  public List<Produto> getAll() {
    return produtoRepository.findAll();
  }

  // pega na base dados por uma parte do nome
  public List<Produto> getByNome(String nome){
    return produtoRepository
    .findByNomeContainingIgnoreCase(nome);
  }

  public Produto save(ProdutoDto produtoForm) {
    Produto paraSalvar = toProduto(produtoForm);

    return produtoRepository.save(paraSalvar);
  }

  public boolean delete(String codigo) {
    // retorna-se um optional = serve para evitar NullPointerException
    Optional<Produto> produto = produtoRepository.findById(codigo);

    if(produto.isEmpty()) return false;

    // .get() pega a entidade Produto do Optional<Produto>
    produtoRepository.delete(produto.get()); // deleta
    return true; // retorna com sucesso
  }

  public Produto update(String codigo, ProdutoDto produtoForm){
    // gero o produto de origem a partir do formulário
    Produto origem = toProduto(produtoForm);

    // gero a entidade que tem que receber as atualizações
    Optional<Produto> destinoOptional = produtoRepository.findById(codigo);

    // checo se o produto de origem existe
    if(destinoOptional.isEmpty()) return null;

    // gero o destino a ser alterado
    Produto destino = destinoOptional.get();

    update(origem, destino);
    produtoRepository.save(destino);
    return produtoRepository.findById(codigo).get();
  }

  private void update(Produto origem, Produto destino) {
    // atualizo atributo por atributo da origem para o destino
    destino.setNome(origem.getNome());
    destino.setPreco(origem.getPreco());
  }

  private Produto toProduto(ProdutoDto produtoForm) {
    return Produto.builder()
      .nome(produtoForm.getNome())
      .codigo(produtoForm.getCodigo())
      .preco(produtoForm.getPreco())
      .build();
  }

}
