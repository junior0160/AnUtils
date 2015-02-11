package br.ufc.datatransfer.handler;

import org.apache.http.HttpResponse;

import android.location.Address;
import br.ufc.datatransfer.DataTransferResponse;
import br.ufc.datatransfer.exception.DataTransferResponseException;

/**
 * 
 * @author Holanda Junior
 *
 * @param <T> Modelo criado {@link Address}, etc
 */
public interface IHandlerResponse<T> {

	/**
	 * Callback chamado para manipular resposta do servidor
	 * @param httpResponse Resposta do servidor
	 * @return Resposta apropriada para a aplicação {@link DataTransferResponse}
	 * @throws DataTransferResponseException Exceção relativa à problemas de resposta do servidor {@link DataTransferResponseException}
	 */
	public DataTransferResponse<T> handlingResponse(HttpResponse httpResponse) throws DataTransferResponseException;
		
}
