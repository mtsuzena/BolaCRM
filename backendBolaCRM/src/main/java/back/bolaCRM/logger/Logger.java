package back.bolaCRM.logger;

public class Logger {
	
	private String headerLog;
	private String log;
	
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

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	public void gerarLog(String log) {
		System.out.println(this.headerLog + " | " + this.log);
	}
	
	

}
