package com.washington.ecomerce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.washington.ecomerce.model.Produto;

@Repository // faz a ponte entre o java e o banco
public interface ProdutoRepository extends MongoRepository<Produto, String>{
  // habilita métodos relacionados a base dados (save, findById, delete, etc)
  // extendendo um modelo padrão de repositório do mongodb

  // ORM -> Object Relational Mapper

  // produz um método capaz de encontrar um Produto baseado em um trecho do nome
  List<Produto> findByNomeContainingIgnoreCase(String text);
  
}
