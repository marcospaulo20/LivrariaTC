package tc.livraria.app;

import totalcross.sys.Settings;
import totalcross.ui.MainWindow;

public class TCProduto extends MainWindow implements LConstantes {

	public TCProduto() {
		setUIStyle(Settings.Android);
		setBackColor(MW_BACKCOLOR);
		Settings.uiAdjustmentsBasedOnFontHeight = true;
		Settings.scrollDistanceOnMouseWheelMove = fmH * 10;
	}

	public void initUI() {
		new LProdutoLista().swapToTopmostWindow();
	}

}
