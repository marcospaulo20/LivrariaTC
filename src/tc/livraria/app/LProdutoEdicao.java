package tc.livraria.app;

import tc.livraria.controller.EdicaoControler;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class LProdutoEdicao extends Container implements LConstantes {

	private Edit editId;
	private Edit editNome;
	private Edit editAno;	
	private Check checkDisponivel;
	private String[] botoes;
	private MessageBox mb;
	
	private Button btSalvar, btVoltar, btRemover;
	
	private EdicaoControler controler;

	private final int COMPONENT_H = fmH * 2;
	private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight, Settings.screenWidth) * 0.10);
	
	public LProdutoEdicao() {
		editId = new Edit();
		editNome = new Edit();
		editAno = new Edit();	
		checkDisponivel = new Check("Disponível");
		
		botoes = new String[] {"Sim", "Não"};
		mb = new MessageBox("Confirmação","Deseja excluir o produto?", botoes);
	}
	
	public void initUI() {
		setBackColor(CNT_BACKCOLOR);
		setInsets(0, 0, 2, 2);

		Label lb = new Label("Edição de produto");
		lb.setFont(lb.getFont().asBold());
		add(lb, CENTER, AFTER);
		
		add(new Label("Nome: "), LEFT + FLAT_EDGE_MARGIN, AFTER + 100, FILL - FLAT_EDGE_MARGIN, COMPONENT_H);
		add(editNome, SAME, AFTER, SAME, SAME);
		editNome.setMaxLength(99);

		add(new Label("Ano lançamento: "), SAME, AFTER, PREFERRED, SAME);
		add(editAno, SAME, AFTER, FILL - FLAT_EDGE_MARGIN, SAME);
		editAno.setMaxLength(4);
		editAno.setMode(Edit.DATE);

		add(checkDisponivel, SAME, AFTER, FILL - FLAT_EDGE_MARGIN, SAME);

		Spacer sp = new Spacer(0, 0);
		add(sp, CENTER, BOTTOM - 300, PARENTSIZE + 10, COMPONENT_H);

		btRemover = new Button("REMOVER");
		btRemover.setBorder(Button.BORDER_ROUND);
        btRemover.roundBorderFactor = 6;
        add(btRemover, LEFT, SAME, PARENTSIZE + 30, COMPONENT_H, sp);
		
		btSalvar = new Button("ALTERAR");
		btSalvar.setBorder(Button.BORDER_ROUND);
		btSalvar.roundBorderFactor = 6;
        add(btSalvar, CENTER, SAME, PARENTSIZE + 30, COMPONENT_H, sp);
        
        btVoltar = new Button("CANCELAR");
        btVoltar.setBorder(Button.BORDER_ROUND);
        btVoltar.roundBorderFactor = 6;
        add(btVoltar, RIGHT, SAME, PARENTSIZE + 30, COMPONENT_H, sp);
	}

	public void onEvent(Event e) {		
		switch (e.type) {
			case ControlEvent.PRESSED:
				if (e.target == btSalvar) {
					controler = new EdicaoControler(this);
					if(controler.salvar()) 
						controler.voltar();
				} else if (e.target == btVoltar) {
					controler = new EdicaoControler(this);
					controler.voltar();
				} else if (e.target == btRemover)
					mb.popup();
				
				if(mb.getPressedButtonIndex() == 0) {
					controler = new EdicaoControler(this);
					controler.remover();
				}
			break;
		}		
	}
	
	public Edit getEditId() {
		return editId;
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
