package controleur;

import javax.xml.bind.DatatypeConverter;

public class Crypto {
	
	
	public static String encodeChaine(String str) {
		String encoded = DatatypeConverter.printBase64Binary(str.getBytes());		
		return encoded;
	}
	
	public static String decodeChaine(String str) {
		//System.out.println("CHAINE DECODE : " + str);
		String decoded = "";
		try 
		{
			decoded = new String(DatatypeConverter.parseBase64Binary(str));	
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Chaine vide");
		}		
		return decoded;
	}
}
