package br.ufc.datatransfer.handler;

import java.io.InputStream;

import org.apache.http.HttpResponse;

import br.ufc.datatransfer.DataTransferResponse;
import br.ufc.datatransfer.exception.DataTransferResponseException;
import br.ufc.datatransfer.util.DataTransferUtils;

import com.google.gson.Gson;

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
	 * Tipo de dados a ser manipulado pelo GSON, como valor único a ser transformado
	 */
	protected Class<T> mDataType;
	
	/**
	 * Tipo de dados a ser manipulador, sendo retornado um vetor desses dados pelo GSON
	 */
	protected Class<T[]> mDataTypeVector;

	/**
	 * Chamado como callback para manipular a resposta do servidor
	 */
	public DataTransferResponse<T> handlingResponse(HttpResponse httpResponse) throws DataTransferResponseException{

		try {

			
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			InputStream contentResponse = httpResponse.getEntity().getContent();
			String jsonContent = DataTransferUtils.toJSONString(contentResponse);
			
			Gson gson = DataTransferUtils.getGsonInstance();
			
			
			if(mDataType != null){
				T result = gson.fromJson(jsonContent, mDataType);
				return new DataTransferResponse<T>(statusCode, result);
			}else{
				T[] result = gson.fromJson(jsonContent, mDataTypeVector);
				return new DataTransferResponse<T>(statusCode, result);
			}
			
			
		} catch (Exception e) {
			
			DataTransferResponseException dataTransferException = new DataTransferResponseException(e.getMessage());
			dataTransferException.setStackTrace(e.getStackTrace());
			
			throw dataTransferException;	
		}

	}


}
