package br.ufc.datatransfer.handler;

import br.ufc.datatransfer.DataTransferResponse;
import br.ufc.datatransfer.exception.DataTransferResponseException;

/**
 * Implementação de {@link IHandlerResponse}
 * Classe geral para handlers específicos 
 * @author Holanda Junior
 *
 */
public abstract class HandlerResponse<T> implements IHandlerResponse<T>{

	private final static String TAG = "HandlerResponse";
	
	public static final int REQUEST_OK = 200;
	public static final int REQUEST_ACCEPTED = 202;
	public static final int REQUEST_UNAUTHORIZED = 401;
	public static final int REQUEST_NOT_FOUND = 404;
	public static final int REQUEST_INTERNAL_SERVER_ERROR = 500;
	public static final int REQUEST_NO_CONTENT = 204;
	
	public static final int STATUS_CODE_EXCEPTION = -1;

	/**
	 * Chamado como callback para manipular a resposta do servidor
	 */
	public abstract DataTransferResponse<T> handlingResponse(DataTransferResponse<T> response);
	

}
