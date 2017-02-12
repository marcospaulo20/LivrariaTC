package tc.livraria.app;

import tc.livraria.controller.CadastroControler;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class LProdutoCadastro extends Container implements LConstantes {

	private Edit editNome, editAno;
	private Check checkDisponivel;

	private Button btSalvar, btVoltar;

	private final int COMPONENT_H = fmH * 2;
	private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight, Settings.screenWidth) * 0.10);
	
	private CadastroControler controller;

	public void initUI() {
		setBackColor(CNT_BACKCOLOR);
		setInsets(0, 0, 2, 2);

		Label lb = new Label("Cadastro de produto");
		lb.setFont(lb.getFont().asBold());
		add(lb, CENTER, AFTER);

		add(new Label("Nome: "), LEFT + FLAT_EDGE_MARGIN, AFTER + 100, FILL - FLAT_EDGE_MARGIN, COMPONENT_H);
		add(editNome = new Edit(), SAME, AFTER, SAME, SAME);
		editNome.setMaxLength(99);

		add(new Label("Ano lançamento: "), SAME, AFTER, PREFERRED, SAME);
		add(editAno = new Edit(), SAME, AFTER, FILL - FLAT_EDGE_MARGIN, SAME);
		editAno.setMaxLength(4);
		editAno.setMode(Edit.DATE);

		add(checkDisponivel = new Check("Disponível"), SAME, AFTER, FILL - FLAT_EDGE_MARGIN, SAME);

		Spacer sp = new Spacer(0, 0);
		add(sp, CENTER, BOTTOM - 300, PARENTSIZE + 10, COMPONENT_H);
		
		btSalvar = new Button("CADASTRAR");
		btSalvar.setBorder(Button.BORDER_ROUND);
		btSalvar.roundBorderFactor = 6;
        add(btSalvar, LEFT, SAME, PARENTSIZE + 30, COMPONENT_H, sp);
        
        btVoltar = new Button("VOLTAR");
        btVoltar.setBorder(Button.BORDER_ROUND);
        btVoltar.roundBorderFactor = 3;
        add(btVoltar, RIGHT, SAME, PARENTSIZE + 20, COMPONENT_H, sp);
	}

	public void onEvent(Event e) {		
		switch (e.type) {
			case ControlEvent.PRESSED:
				if (e.target == btSalvar) {
					controller = new CadastroControler(this);
					if(controller.salvar()) clear();
				} else if (e.target == btVoltar) {
					controller = new CadastroControler(this);
					controller.voltar();
				}
			break;
		}
	}

	public Edit getEditNome() {
		return editNome;
	}
	
	public Edit getEditAno() {
		return editAno;
	}
	
	public Check getCheckDisponivel() {
		return checkDisponivel;
	}
	
}
