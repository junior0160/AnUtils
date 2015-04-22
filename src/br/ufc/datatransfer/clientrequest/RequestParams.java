package br.ufc.datatransfer.clientrequest;

public class RequestParams{
     
	public static final RequestParams DefaultRequestParams = new RequestParams();
	
	static{
		DefaultRequestParams.connectTimeout = 15000;
		DefaultRequestParams.readTimeout = 10000;
		DefaultRequestParams.requestMethod = REQUEST_METHOD.POST;
		DefaultRequestParams.sendDataAsJson = true;
	}
	
	public enum REQUEST_METHOD {
		POST("POST"), GET("GET");
		
		private String type;
		
		private REQUEST_METHOD(String type) {
			this.type = type;
		}
		
		public String getMethod(){
			return type;
		}
	}
	
	public int readTimeout;
	public int connectTimeout;
	public REQUEST_METHOD requestMethod;
	public boolean sendDataAsJson;
	
	
}
