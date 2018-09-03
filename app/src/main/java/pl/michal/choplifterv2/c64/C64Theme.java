package pl.michal.choplifterv2.c64;


import pl.michal.choplifterv2.ChopLifterActivity;

public final class C64Theme {
//	ChopLifterActivity chopLifterActivity;


	/* Screen */
	public final static int SCREEN_WIDTH = ChopLifterActivity.getWidth(ChopLifterActivity.getContext());
	public final static int SCREEN_HEIGHT = ChopLifterActivity.getHeight(ChopLifterActivity.getContext());
	public final static int SPRITE_SCALE = SCREEN_WIDTH/300;

	/* Colors*/
	public final static int BLACK = C64Color.C64COLOR_0 ;
	public final static int WHITE = C64Color.C64COLOR_1 ;
	public final static int RED = C64Color.C64COLOR_2 ;
	public final static int CYAN = C64Color.C64COLOR_3 ;
	public final static int PURPLE = C64Color.C64COLOR_4 ;
	public final static int GREEN = C64Color.C64COLOR_5 ;
	public final static int BLUE = C64Color.C64COLOR_6 ;
	public final static int YELLOW = C64Color.C64COLOR_7 ;
	public final static int ORANGE = C64Color.C64COLOR_8 ;
	public final static int BROWN = C64Color.C64COLOR_9 ;
	public final static int SKIN = C64Color.C64COLOR_10 ;
	public final static int DARK_GRAY = C64Color.C64COLOR_11 ;
	public final static int GRAY = C64Color.C64COLOR_12 ;
	public final static int LIGHT_GREEN = C64Color.C64COLOR_13 ;
	public final static int LIGHT_PURPLE = C64Color.C64COLOR_14 ;
	public final static int DARK_WHITE = C64Color.C64COLOR_15 ;

	/* Font */
	public final static int C64FONT = C64Font.PETSCII_UNSHIFTED ;
	
	// ------------------------------------------------------------------------
	
	/**
	 * getText wrapper to font
	 * @param text
	 * @return
	 */

	public final static int[][] getText(String text) {
		return C64Font.getText(text, C64FONT) ;
	}
}








