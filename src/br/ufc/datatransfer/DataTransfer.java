package br.ufc.datatransfer;

import android.os.AsyncTask;
import android.util.Log;
import br.ufc.datatransfer.clientrequest.IClientRequest;
import br.ufc.datatransfer.handler.HandlerResponse;
import br.ufc.datatransfer.handler.IHandlerResponse;

/**
 * AsyncTask responsável por enviar os dados para o servidor
 * @author holanda
 *
 * @param <T>
 */
public class DataTransfer<T> extends
		AsyncTask<Void, Void, DataTransferResponse<T>> implements IDataTransfer<T>{

	private final static String TAG = "SendDataTask";
	
	private static final String MESSAGE_EXCEPTION = "Ocorreu um erro na transferência dos dados. Por favor, verifique sua conexão de internet ou contacte o suporte.";
	
	/**
	 * Handler para manipular os dados recebidos do servidor. Extensão de {@link HandlerResponse}		
	 */
	private IHandlerResponse<T> mHandlerResponse;
	
	/**
	 * Implementação de {@link DataTransferCallback} para recebimento dos dados tratados
	 */
	private DataTransferCallback<T> mCallback;
		
	private IClientRequest<T> mRequest;
	
	public DataTransfer(IClientRequest<T> request, DataTransferCallback<T> callback, IHandlerResponse<T> handlerResponse) {
		mRequest = request;
		mCallback = callback;
		mHandlerResponse = handlerResponse;
	}
	
	/**
	 * @param mHandlerResponse the mHandlerResponse to set
	 */
	public void setmHandlerResponse(IHandlerResponse<T> mHandlerResponse) {
		this.mHandlerResponse = mHandlerResponse;
	}
	
	
	/**
	 * @param callback
	 *            the callback to set
	 */
	public void setCallback(DataTransferCallback<T> callback) {
		mCallback = callback;
	}
	
	
	@Override
	protected void onPreExecute() {

		super.onPreExecute();
			
	}

	@Override
	protected DataTransferResponse<T> doInBackground(Void... params){

		Log.e("Request", "Datatransfer DoInBackground");
		DataTransferResponse<T> response = mRequest.doConnection();
		
		return mHandlerResponse.handlingResponse(response);
		
	}

	protected void onPostExecute(DataTransferResponse<T> result) {
		mCallback.onReceiveResponse(result);
	}
	
	public void execute() {
		super.execute();
	}
}
