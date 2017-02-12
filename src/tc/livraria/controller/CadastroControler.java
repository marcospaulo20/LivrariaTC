package tc.livraria.controller;

import tc.livraria.app.LProdutoCadastro;
import tc.livraria.app.LProdutoLista;
import tc.livraria.dao.ProdutoDAO;
import tc.livraria.model.Produto;
import tc.livraria.model.Produto.ProdutoBuilder;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Toast;

public class CadastroControler extends Container {
	private ProdutoDAO produtoDAO;
	private LProdutoCadastro telaCadastro;
	
	public CadastroControler(LProdutoCadastro telaCadastro) {
		this.telaCadastro = telaCadastro;
		this.telaCadastro.swapToTopmostWindow();
	}

	public boolean salvar() {
		String nome = this.telaCadastro.getEditNome().getText();
		String anoStr = this.telaCadastro.getEditAno().getText();
		boolean disponivel = this.telaCadastro.getCheckDisponivel().isChecked();

		if (nome.length() == 0 || anoStr.length() == 0)
			showToast("Por favor preencha todos os campos!");
		else if(anoStr.length() != 0)
			if(!(Integer.parseInt(anoStr) >= 1880 && Integer.parseInt(anoStr) <= 2017))
				showToast("Por favor informe um ano válido!");
		else {
			int ano = Integer.parseInt(anoStr);
			Produto produto = new ProdutoBuilder()
						.nome(nome)
						.ano(ano)
						.disponivel(disponivel)
					.builder();

			produtoDAO = new ProdutoDAO();
			if (produtoDAO.novo(produto)) {
				showToast("Produto adicionado com sucesso!");
				return true;
			} else
				showToast("Ocorreu algum error ao tentar adicionar esse produto!");
		}
		return false;
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
