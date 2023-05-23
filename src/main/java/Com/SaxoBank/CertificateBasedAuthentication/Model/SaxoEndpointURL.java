package Com.SaxoBank.CertificateBasedAuthentication.Model;

public class SaxoEndpointURL {
	
	private static String clientURL="https://gateway.saxobank.com/sim/openapi/port/v1/clients/me";
	
	private static String accountURL="https://gateway.saxobank.com/sim/openapi/port/v1/accounts/me";
	
	private static String ordersURL="https://gateway.saxobank.com/sim/openapi/trade/v2/orders";
	private static String apiurl="https://gateway.saxobank.com/sim/openapi/port/v1/users/me";

	private String endpointurlSaxo;
	
	public String getEndpointurlSaxo() {
		return endpointurlSaxo;
	}

	public void setEndpointurlSaxo(String endpointurlSaxo) {
		this.endpointurlSaxo = endpointurlSaxo;
	}
	
	

	public static String getClientURL() {
		return clientURL;
	}

	public static void setClientURL(String clientURL) {
		SaxoEndpointURL.clientURL = clientURL;
	}

	public static String getAccountURL() {
		return accountURL;
	}

	public static void setAccountURL(String accountURL) {
		SaxoEndpointURL.accountURL = accountURL;
	}

	public static String getOrdersURL() {
		return ordersURL;
	}

	public static void setOrdersURL(String ordersURL) {
		SaxoEndpointURL.ordersURL = ordersURL;
	}


			
	//public static String https://gateway.saxobank.com/sim/openapi/port/v1/balances?AccountKey=UADDYLsgTaYuYF96aGwzbA==&ClientKey=wGBL2pqitfL3oe7kDrMP3A==
	
				

}
