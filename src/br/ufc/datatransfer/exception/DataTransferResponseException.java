package br.ufc.datatransfer.exception;

/**
 * Exceção que representa problemas ocorridos com a resposta do servidor
 * @author Holanda Junior
 *
 */
public class DataTransferResponseException extends Exception implements IException{

	public DataTransferResponseException(String message) {
		super(message);
	}
	
}
