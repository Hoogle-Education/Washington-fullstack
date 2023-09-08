package com.washington.ecomerce.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.washington.ecomerce.model.Produto;
import com.washington.ecomerce.resource.dto.ProdutoDto;
import com.washington.ecomerce.service.ProdutoService;

import lombok.AllArgsConstructor;

@RestController // declaro que aqui existirão endpoints
@RequestMapping("api/produtos") // prefixo da rota
@CrossOrigin(origins = "http://localhost:4200", maxAge = 360000,
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor // mais injeções de dependencia, agora para os serviços
public class ProdutoController {

  private ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<List<ProdutoDto>> pegarTodos() {

    // gerando uma lista de produtos dto a partir do serviço
    List<ProdutoDto> produtos = produtoService
      .getAll() // pego a lista
      .stream() // crio um fluxo de dados
      .map(ProdutoDto::new) // troco para uma lista do tipo produtoDto
      .toList(); // retorno ao modelo de lista

    // monto uma resposta baseada na lista de produtos
    return ResponseEntity.ok(produtos);
  }

  @GetMapping("/pesquisar")
  public ResponseEntity<List<ProdutoDto>> pegarTodosPorNome(@RequestParam("nome") String nome) {

    List<ProdutoDto> produtos = produtoService
        .getByNome(nome).stream()
        .map(ProdutoDto::new).toList();

    // monto uma resposta baseada na lista de produtos
    return ResponseEntity.ok(produtos);
  }

  @PostMapping("/cadastrar") // sufixo para este método POST
  public ResponseEntity<ProdutoDto> cadastrarNovo(@RequestBody ProdutoDto produtoDto){
    // no corpo da solicitação vem um ProdutoDto

    // salvo o produto através do produto service
    Produto produto = produtoService.save(produtoDto);

    // caso não seja possível salvar, retorno uma bad request
    if(produto == null) return ResponseEntity.badRequest().build();

    // retorno um status ok com o produto salvo
    return ResponseEntity.ok(new ProdutoDto(produto) );
  }

  @DeleteMapping("/remover") // deleta em /remover
  public ResponseEntity<String> deletarProdutoPorCodigo(@RequestParam String codigo) {
    // deleto através do serviço informando o código
    boolean deleted = produtoService.delete(codigo);

    if(!deleted) // se não deletou
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST) // request com erro
        .body("Falhou para deletar o produto"); // falhou para deletar

    // produto deletado com sucesso
    return ResponseEntity.ok("Produto deletado com sucesso!");    
  }

  @PutMapping("/atualizar") // verbo PUT para atualizar
  public ResponseEntity<ProdutoDto> atualizarProduto(@RequestParam String codigo, @RequestBody ProdutoDto produtoDto) {

    // atualizo o produto através do código e dor formulário de produto
    Produto produtoAtualizado = produtoService.update(codigo, produtoDto);

    // se o produto atualizado for nulo, não atualizou, então bad request
    if(produtoAtualizado == null) return ResponseEntity.badRequest().build();

    // se atualizou retorno ok
    return ResponseEntity.ok(new ProdutoDto(produtoAtualizado));
  }

}
