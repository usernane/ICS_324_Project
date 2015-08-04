package Frameworks;

/**
 * @author Ibrahim
 * @version 1.0
 * @created 04-Aug-2015 2:34:02 AM
 */
public class OperationResult {

	private String message;
	private boolean result;



        public OperationResult(boolean b, String message) {
            this.message = message+"";
            this.result = b;
        }
	/**
	 * 
	 * @param message
	 * @param result
	 */
	public OperationResult(String message, boolean result){
            this(result,message);
	}

	public String getMessage(){
		return this.message;
	}

	public boolean getResult(){
		return this.result;
	}

}