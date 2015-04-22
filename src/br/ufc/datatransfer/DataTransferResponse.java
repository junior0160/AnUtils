package br.ufc.datatransfer;

import java.util.List;
import java.util.Map;



/**
 * Classe que representa a resposta recebida do servidor e tratada pela aplicação. Dados prontos
 * para serem utilizados.
 * @author Holanda Junior
 *
 */
public class DataTransferResponse<T> {

	/**
	 * Código de status do retorno do servidor
	 */
	public int mStatusCode;
	
	/**
	 * Dado recebidos pelo servidor
	 */
	public T mValue;	
	
	
	public DataTransferResponse() {
	}
	
	public DataTransferResponse(int statusCode){
		mStatusCode = statusCode;
	}
	
	public DataTransferResponse(int statusCode, T value) {
		this(statusCode);
		mValue = value;
	
	}

	public DataTransferResponse(int mStatusCode, T mValue,
			Map<String, List<String>> mHeadersFields) {
		this(mStatusCode, mValue);
	}
	
	

	



	
	
}
