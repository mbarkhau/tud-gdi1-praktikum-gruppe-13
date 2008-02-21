package mis.gdi1lab07.student.gameData;

public class AssocNumberPosition implements FlagConstants {

	//Weiﬂt jedem Spieler anhand seiner ID eine Position zu, zu der er 
	//geht, wenn seine Defensiv-KI zur Startposition geht.
	
	private static int[] positions = {O_G_C, O_P_L, O_P_C, O_P_R, O_P_L, O_P_C, O_P_R, O_G_L, O_G_R, O_P_L, O_P_R};
	
	public static int getPositionFlag(int pos) {
		try {
			return positions[pos-1];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return INVALD;
		}
	}
	
}
