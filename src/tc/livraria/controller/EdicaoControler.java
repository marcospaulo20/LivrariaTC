package tc.livraria.controller;

import tc.livraria.app.LProdutoEdicao;
import tc.livraria.app.LProdutoLista;
import tc.livraria.dao.ProdutoDAO;
import tc.livraria.model.Produto;
import tc.livraria.model.Produto.ProdutoBuilder;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Toast;

public class EdicaoControler extends Container {

	private ProdutoDAO produtoDAO;
	private LProdutoEdicao telaEdicao;
	
	private Produto produto;
	
	public EdicaoControler(LProdutoEdicao lProdutoEdicao) {
		this.telaEdicao = lProdutoEdicao;
	}
	
	public EdicaoControler(Produto produto, LProdutoEdicao lProdutoEdicao) {		
		this.produto = produto;
		this.telaEdicao = lProdutoEdicao;
		popularCamposPorTipo();
		this.telaEdicao.swapToTopmostWindow();
	}
	
	public void popularCamposPorTipo() {
		popularCampos(this.produto);
	}
	
	public void popularCampos(Produto produto) {		
		String id = String.valueOf(produto.getId());
		this.telaEdicao.getEditId().setText(id);

		String nome = String.valueOf(produto.getNome());
		this.telaEdicao.getEditNome().setText(nome);
		
		String ano = String.valueOf(produto.getAno());
		this.telaEdicao.getEditAno().setText(ano);
		
		boolean disponivel = produto.getDisponivel();
		this.telaEdicao.getCheckDisponivel().setChecked(disponivel);
	}
	
	public boolean salvar() {
		Integer id = Integer.parseInt(this.telaEdicao.getEditId().getText());
		String nome = this.telaEdicao.getEditNome().getText();
		int ano = Integer.parseInt(this.telaEdicao.getEditAno().getText());		
		boolean disponivel = this.telaEdicao.getCheckDisponivel().isChecked();
		
		if (nome.length() == 0 || ano == 0)
			showToast("Por favor preencha todos os campos!");
		if(!(ano >= 1880 && ano <= 2017))
			showToast("Por favor informe um ano válido!");
		else {
			Produto produto = new ProdutoBuilder()
					.id(id)
					.nome(nome)
					.ano(ano)
					.disponivel(disponivel)
					.builder();
	
			produtoDAO = new ProdutoDAO();
			if(produtoDAO.atualizar(produto))
				return true;
			else
				showToast("Ocorreu algum error ao tentar atualizar esse produto!");
		}
		return false;
	}
	
	public void remover() {
		Integer id = Integer.parseInt(this.telaEdicao.getEditId().getText());
		produtoDAO = new ProdutoDAO();		
		produtoDAO.remover(id);
		
		voltar();
	}
	
	public void voltar() {
		new LProdutoLista().swapToTopmostWindow();
	}
	
	private void showToast(String message) {
		Toast.height = fmH * 3;
		Toast.posY = (int) (Control.BOTTOM - 600);
		Toast.show(message, 2000);
	}
}
