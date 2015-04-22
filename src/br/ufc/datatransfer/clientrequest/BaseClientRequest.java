package br.ufc.datatransfer.clientrequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufc.datatransfer.clientrequest.RequestParams.REQUEST_METHOD;


public abstract class BaseClientRequest<T, K> implements IClientRequest<K>{

	
	protected static String COOKIES_HEADER = "Set-Cookie";
	protected final static String CHARSET = "UTF-8";
	
	protected String url;
	protected List<NameValuePair> params;
	protected T connection;
	
	protected int readTimeout;
	protected int connectTimeout;
	protected REQUEST_METHOD requestMethod;
	protected boolean sendDataAsJson;
	
	public BaseClientRequest(String url, List<NameValuePair> params, RequestParams requestParams) {
		this.url = url;
		this.params = params;
		this.readTimeout = requestParams.readTimeout;
		this.connectTimeout = requestParams.connectTimeout;
		this.requestMethod = requestParams.requestMethod;
		this.sendDataAsJson = requestParams.sendDataAsJson;

	
	}
	
	
	
	protected String getRequestQuery() throws JSONException, UnsupportedEncodingException
	{
		
		if(sendDataAsJson)
			return getJson();
		else
			return getURLEncoded();
	}
	
	private String getURLEncoded() throws UnsupportedEncodingException{
		
		StringBuilder result = new StringBuilder();
	    boolean first = true;
	    
	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), CHARSET));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), CHARSET));
	    }
	    
	    return result.toString();
	}
	
	private String getJson() throws JSONException{

		JSONObject content = new JSONObject();
		
	    for (NameValuePair pair : params)
	    {
	    	content.put(pair.getName(), pair.getValue());
	
	    }
	    
	    return content.toString();
		
	}
	
	protected static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuffer response = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response.toString();
	}


	
}
