package tc.livraria.controller;

import java.util.List;

import tc.livraria.app.LProdutoCadastro;
import tc.livraria.app.LProdutoEdicao;
import tc.livraria.dao.ProdutoDAO;
import tc.livraria.model.Produto;
import tc.livraria.model.Produto.ProdutoBuilder;

public class ListaControler {
	ProdutoDAO produtoDAO;
	
	public String[][] obterProdutos() { 
		produtoDAO = new ProdutoDAO();
		List<Produto> listaProdutos = produtoDAO.lista();
	
		String produtos[][] = new String[listaProdutos.size()][4];
		
		for(int i = 0; i < listaProdutos.size(); i++) {
			produtos[i][0] = String.valueOf(listaProdutos.get(i).getId());
			produtos[i][1] = listaProdutos.get(i).getNome();
			produtos[i][2] = String.valueOf(listaProdutos.get(i).getAno());
			produtos[i][3] = String.valueOf((listaProdutos.get(i).getDisponivel() ? "OK" : ""));
		}
		return produtos;
	}
	
	public void atualizar(String[] produtoSelecionado) {
		if(produtoSelecionado.length > 0) {
			Integer id = Integer.parseInt(produtoSelecionado[0]);
			String nome = produtoSelecionado[1];
			int ano = Integer.parseInt(produtoSelecionado[2]);
			boolean disponivel = (produtoSelecionado[3] == "OK" ?  true : false );
			
			Produto produto = new ProdutoBuilder()
						.id(id)
						.nome(nome)
						.ano(ano)
						.disponivel(disponivel)
					.builder();
			
			LProdutoEdicao lProdutoEdicao = new LProdutoEdicao();
			new EdicaoControler(produto, lProdutoEdicao);
		}
	}
	
	public void novo() {
		LProdutoCadastro lProdutoCadastro = new LProdutoCadastro();
		new CadastroControler(lProdutoCadastro);		
	}
}
