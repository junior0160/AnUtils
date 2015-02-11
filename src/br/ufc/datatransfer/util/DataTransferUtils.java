package br.ufc.datatransfer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * Classe com metodos de funções adicionais para o tratamento com trasferência de dados
 * @author Holanda
 *
 */
public class DataTransferUtils {
	
	private static Gson mGson;
	
	/**
	 * Converte uma stream de dados em string 
	 * @param stream Stream de dados
	 * @return String transformada
	 * @throws IOException
	 */
	public static String toJSONString(InputStream stream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	public static Gson getGsonInstance(){
		if(mGson == null)
			mGson = new Gson();
		
		return mGson;
	}

}
