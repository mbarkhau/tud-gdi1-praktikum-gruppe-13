package mis.gdi1lab07.student.gameData;


public interface GameMessages {

	public static String PASS_REQUEST = "NimmAn"; //Fordert Passannahmen an
	
	public static String PASS_RESPONSE = "NehmeAn"; //Nimmt Pass an
	
	public static String PASS_ACK = "PassKommt"; //K�ndigt Pass an

	public static String[] ALL = new String[]{PASS_REQUEST, PASS_RESPONSE, PASS_ACK};
	
}
