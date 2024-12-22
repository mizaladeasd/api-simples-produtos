package com.produtos.userprod;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private Long idCounter = 1L;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        produto.setId(idCounter++);
        produtos.add(produto);
        return produto;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Long id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null; 
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                produto.setQuantidade(produtoAtualizado.getQuantidade());
                return produto;
            }
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                produtos.remove(produto);
                return "Produto deletado com sucesso";
            }
        }
        return "Produto não encontrado"; 
    }
}
