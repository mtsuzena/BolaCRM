package back.bolaCRM.logger;

public class Logger {
	
	private String headerLog;
	
	public Logger(){
		
	}
	
	public Logger (String headerLog) {
		this.headerLog = headerLog;
	}

	public String getHeaderLog() {
		return headerLog;
	}

	public void setHeaderLog(String headerLog) {
		this.headerLog = headerLog;
	}

	
	public void gerarLog(String log) {
		System.out.println(this.headerLog + " | " + log);
	}
	
	

}
