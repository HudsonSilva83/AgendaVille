package br.ce;

	import java.text.Normalizer;
	import java.util.regex.Pattern;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AcentacaoTest {

	
	public String trata (String passa){
		
		passa =passa.replaceAll("[ÂÀÁÄÃ]","A");  
		passa =passa.replaceAll("[âãàáä]","a");  
		passa =passa.replaceAll("[ÊÈÉË]","E");  
		passa =passa.replaceAll("[êèéë]","e");  
		passa =passa.replaceAll("ÎÍÌÏ","I");  
		passa =passa.replaceAll("îíìï","i");  
		passa =passa.replaceAll("[ÔÕÒÓÖ]","O");  
		passa =passa.replaceAll("[ôõòóö]","o");  
		passa =passa.replaceAll("[ÛÙÚÜ]","U");  
		passa =passa.replaceAll("[ûúùü]","u");  
		passa =passa.replaceAll("Ç","C");  
		passa =passa.replaceAll("ç","c");   
		passa =passa.replaceAll("[ýÿ]","y");  
		passa =passa.replaceAll("Ý","Y");  
		passa =passa.replaceAll("ñ","n");  
		passa =passa.replaceAll("Ñ","N");
		passa =passa.replaceAll("í","i");
		passa =passa.replaceAll("ã","a"); 
		return passa;
    }
	
}
	
	
	
	

