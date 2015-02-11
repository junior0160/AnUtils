package br.ufc.datatransfer;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import android.location.Address;
import android.util.Log;
import br.ufc.datatransfer.config.AppConfig;
import br.ufc.datatransfer.handler.IHandlerResponse;
import br.ufc.datatransfer.handler.StringHandlerResponse;
import br.ufc.datatransfer.util.URLConfig;

/**
 * Núcleo de transferência de dados da aplicação para o servidor
 * @author Holanda Junior
 *
 * @param <T> Modelo criado {@link Collector}, {@link Address}, etc.
 */
public class DataTransfer<T> implements IDataTransfer<T> {

	private final static String TAG = "DataTransfer";
	
	/**
	 * AsyncTask para envio dos dados
	 */
	private SendDataTask<T> mSendDataTask;
	
	/**
	 * Callback para manipulação da resposta do servidor
	 */
	private DataTransferCallback<T> mCallback;
	
	
	/**
	 * 		
	 * @param callback {@link DataTransferCallback}
	 */
	public DataTransfer(DataTransferCallback<T> callback) {
		mSendDataTask = new SendDataTask<T>();
		mCallback = callback;
	}

	/**
	 * Cria {@link HttpPost} para ser enviado
	 * @param url URL do servidor
	 * @param jsonContent Dados a serem enviados em formato JSON
	 * @return {@link HttpPost} construido
	 */
	private HttpPost createPost(String url, String jsonContent) {

		HttpPost post = new HttpPost(url);

		try {
			post.setEntity(new StringEntity(jsonContent, "UTF-8"));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");

			

			return post;

		
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Cria {@link HttpPost} para ser enviado
	 * @param url URL do servidor
	 * @param contentEntity Dados a serem enviados e construidos conforme {@link MultipartEntityBuilder}
	 * @return {@link HttpPost} construido
	 */
	private HttpPost createPost(String url, MultipartEntityBuilder contentEntity) {

		HttpPost post = new HttpPost(url);
		post.setEntity(contentEntity.build());
		post.addHeader("token", AppConfig.SERVER_TOKEN);
		
		
		
		return post;

	}

	/**
	 * 
	 * @param url
	 * 		  Url de requisição	
	 * @param data
	 * 		  Key: representa o parametro a ser passado pelo Post
	 * 		  Caso deseje enviar json, use a key "json"	
	 * 		  Value: representa o dado em si que será enviado	
	 * @param handlerResponse
	 * 		  Callback de retorno para a resposta do servidor 	
	 */
	public void sendDataPost(Map<String, Object> data) {

		HttpPost post;
		
				
		if (data.containsKey("json"))
			post = createPost(URLConfig.URL_ADD_DATA, (String) data.get("json"));
		else {
			MultipartEntityBuilder entity = MultipartEntityBuilder.create();

			for (Map.Entry<String, Object> dataMap : data.entrySet()) {

				ContentBody body = null;
				Object value = dataMap.getValue();
				

				if (value instanceof File)
					body = new FileBody((File) value, ContentType.MULTIPART_FORM_DATA);
				else if (value instanceof String)
					body = new StringBody((String) value,ContentType.DEFAULT_TEXT);
				
				entity.addPart(dataMap.getKey(), body);
			}

			post = createPost(URLConfig.URL_ADD_DATA, entity);

		}
		Log.e(TAG, "DataTransfer");
		mSendDataTask.setCallback(mCallback);
		mSendDataTask.setmHandlerResponse((IHandlerResponse<T>) new StringHandlerResponse());
		mSendDataTask.execute(post);

	}
	
	public void sendDataGet(Map<String, Object> data){
		String url = URLConfig.URL_ADD_DATA;
		url += "?";
						
		for(Map.Entry<String, Object> values : data.entrySet())
			url += values.getKey() + "=" + values.getValue() + "&";
		
		url = url.substring(0, url.length()-1);
		
		Log.e("GET URL", url);
		
		HttpGet get = new HttpGet(url);
		
		mSendDataTask.setCallback(mCallback);
		mSendDataTask.setmHandlerResponse((IHandlerResponse<T>) new StringHandlerResponse());
		mSendDataTask.execute(get);
	}

}
