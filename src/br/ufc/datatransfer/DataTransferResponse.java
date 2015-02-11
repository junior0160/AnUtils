package br.ufc.datatransfer;

import br.ufc.datatransfer.exception.DataTransferException;



/**
 * Classe que representa a resposta recebida do servidor e tratada pela aplicação. Dados prontos
 * para serem utilizados.
 * @author Holanda Junior
 *
 * @param <T> Modelos criados etc.
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
	
	/**
	 * Dados recebidos pelo servidor como vetor
	 */
	private T[] mValueVector;
	
	
	/**
	 * Representa a exceção ocorrida, caso algo tenha acontecido
	 */
	private DataTransferException mTransferException;
		
	
	public DataTransferResponse(int statusCode){
		mStatusCode = statusCode;
	}
	
	public DataTransferResponse(int statusCode, T value) {
		this(statusCode);
		mValue = value;
	
	}
	
	public DataTransferResponse(int statusCode, T[] value) {
		this(statusCode);
		mValueVector = value;
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

	/**
	 * @return the mValueVector
	 */
	public T[] getmValueVector() {
		return mValueVector;
	}

	/**
	 * @param mValueVector the mValueVector to set
	 */
	public void setmValueVector(T[] mValueVector) {
		this.mValueVector = mValueVector;
	}

	/**
	 * @return the transferException
	 */
	public DataTransferException getmTransferException() {
		return mTransferException;
	}

	/**
	 * @param transferException the transferException to set
	 */
	public void setmTransferException(DataTransferException transferException) {
		this.mTransferException = transferException;
	}

	
	
}
