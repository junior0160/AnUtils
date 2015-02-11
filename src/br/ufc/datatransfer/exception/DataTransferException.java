package br.ufc.datatransfer.exception;

/**
 * Exceção que representa problemas na transferência de dados em geral
 * @author Holanda Junior
 *
 */
public class DataTransferException extends Exception implements IException{

	private DataTransferResponseException mResponseException;
	
	public DataTransferException(String message) {
		super(message);
		
	}
	
	public DataTransferException(String message, DataTransferResponseException responseException) {
		this(message);
		this.mResponseException = responseException;
	}
	
	public DataTransferResponseException getResponseException(){
		return mResponseException;
	}
}
