package br.ufc.datatransfer;

import br.ufc.datatransfer.exception.DataTransferException;



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
	private int mStatusCode;
	
	/**
	 * Dado recebidos pelo servidor
	 */
	private T mValue;	
	
	public DataTransferResponse() {
	}
	
	public DataTransferResponse(int statusCode){
		mStatusCode = statusCode;
	}
	
	public DataTransferResponse(int statusCode, T value) {
		this(statusCode);
		mValue = value;
	
	}

	/**
	 * @return the mStatusCode
	 */
	public int getmStatusCode() {
		return mStatusCode;
	}

	/**
	 * @return the mValue
	 */
	public T getmValue() {
		return mValue;
	}

	public void setmStatusCode(int mStatusCode) {
		this.mStatusCode = mStatusCode;
	}

	public void setmValue(T mValue) {
		this.mValue = mValue;
	}




	
	
}
