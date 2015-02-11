package br.ufc.datatransfer;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import br.ufc.datatransfer.exception.DataTransferException;
import br.ufc.datatransfer.exception.DataTransferResponseException;
import br.ufc.datatransfer.handler.HandlerResponse;
import br.ufc.datatransfer.handler.IHandlerResponse;

/**
 * AsyncTask responsável por enviar os dados para o servidor
 * @author holanda
 *
 * @param <T>
 */
public class SendDataTask<T> extends
		AsyncTask<HttpRequestBase, Void, DataTransferResponse<T>> {

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

		
	private DataTransferException mException;
	
	
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
	protected DataTransferResponse<T> doInBackground(HttpRequestBase... params){

		try {
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 10000;
			HttpConnectionParams
					.setSoTimeout(httpParameters, timeoutConnection);
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);

			HttpClient client = new DefaultHttpClient(httpParameters);
			
			return mHandlerResponse.handlingResponse(client.execute(params[0]));
			
		

		}catch(DataTransferResponseException e){
			
			e.printStackTrace();
			
			
			
			mException = new DataTransferException(e.getMessage(), e);
			DataTransferResponse<T> dataTransferResponse = new DataTransferResponse<T>(HandlerResponse.STATUS_CODE_EXCEPTION);
			dataTransferResponse.setmTransferException(mException);	
			
			return dataTransferResponse;
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			
			
			mException = new DataTransferException(e.getMessage());
			mException.setStackTrace(e.getStackTrace());
			DataTransferResponse<T> dataTransferResponse = new DataTransferResponse<T>(HandlerResponse.STATUS_CODE_EXCEPTION);
			dataTransferResponse.setmTransferException(mException);
			
			return dataTransferResponse;
		}

		
	}

	protected void onPostExecute(DataTransferResponse<T> result) {
		mCallback.onReceiveResponse(result);
	}
}
