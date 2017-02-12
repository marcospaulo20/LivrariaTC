package tc.livraria.app;

import tc.livraria.controller.ListaControler;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.Spacer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.GridEvent;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Rect;

public class LProdutoLista extends Container implements LConstantes {

	private Bar bar;
	private Grid grid;
	private Button btNovo;
	private ListaControler controler;

	private final int COMPONENT_H = fmH * 2;

	public void initUI() {
		setBackColor(CNT_BACKCOLOR);
		
		Font f = Font.getFont(true,Font.NORMAL_SIZE+2);
		bar = new Bar("Lista de produtos da livraria");
		bar.titleAlign = CENTER;
		bar.setForeColor(BAR_COLOR);
		bar.ignoreInsets = true;
		bar.setFont(f);
		bar.setBackForeColors(0x8592B5,Color.WHITE);
		add(bar, LEFT, TOP - 25, FILL, PREFERRED);
		
		grid();

		Spacer sp = new Spacer(0, 0);
		add(sp, RIGHT - 5, BOTTOM, PARENTSIZE + 10, COMPONENT_H);
		btNovo = new Button("NOVO");
		add(btNovo, CENTER,PARENTSIZE + 93,PARENTSIZE+100,PARENTSIZEMIN+20, sp);
		btNovo.setBorder(Button.BORDER_3D);
	}

	public void grid() {
		Rect r = getClientRect();

		String[] subtitulos = { "Cod.", "Nome", "Ano", "Disponível" };
		int gridWidths[] = { 0, 150, 50, 6};
		int gridAligns[] = { CENTER, LEFT, CENTER, CENTER };

		grid = new Grid(subtitulos, gridWidths, gridAligns, false);
		grid.isChecked(3);
		add(grid, LEFT, TOP + 150, r.width, r.height-100);
		grid.secondStripeColor = Color.getRGB(235, 235, 235);

		controler = new ListaControler(); 
		String produtos[][] = controler.obterProdutos();
				
		grid.setItems(produtos);
	}

	public void onEvent(Event e) {
		if (e.type == GridEvent.SELECTED_EVENT) {
			if(grid.getSelectedItem() != null) {
				controler.atualizar(grid.getSelectedItem());
			}
		}
		
		if (e.type == ControlEvent.PRESSED) {
			if (e.target == btNovo) {
				controler.novo();
			}	
		}
	}
}