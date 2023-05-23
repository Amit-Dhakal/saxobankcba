package Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS;

public enum Constants {
	
	client_id("7cb253c757354704a83762627dc885a6"),
	 client_secrets("d83a57f9ff3c481185176eaaae596c8c"),
	 redirect_url("https://staging.koizai.com/"),
     username("koizai-qa"),
	 userid("17695456"),
	 password("Saxo123!"),
	 certificatePath("C:\\Users\\Amit-PC\\Desktop\\saxokey\\6B00000DC6E45843E023524F14000200000DC6.p12"),
	 certficatePassword("jJEnMVpp"),
	 tokenURL("https://sim.logonvalidation.net/token"),
	 serviceProviderUrl("koizai-qa_C76B25B9-5D99-451F-8144-9D45690DEF8D"),
	 clientURL("https://gateway.saxobank.com/sim/openapi/port/v1/clients/me"),
	 accountURL("https://gateway.saxobank.com/sim/openapi/port/v1/accounts/me"),
	 ordersURL("https://gateway.saxobank.com/sim/openapi/trade/v2/orders"),
	 apiurl("https://gateway.saxobank.com/sim/openapi/port/v1/users/me");

	private String value;
	Constants(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
