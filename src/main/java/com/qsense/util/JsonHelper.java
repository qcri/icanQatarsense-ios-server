package com.qsense.util;

public class JsonHelper {
	private String message;
	private String status;
	private String detail;
	private String error;
	private transient boolean ok;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public static JsonHelper getJsonError(String detail, Throwable exception) {
        JsonHelper json = new JsonHelper();
        json.setStatus("400");
        json.setMessage("Failed");
        json.setDetail(detail);
        //json.setOk(false);
        if (exception != null) {
            try {
                json.setError(exception.getMessage());
            } catch (NullPointerException npe) {
                // do nothing
            }
        }
        return json;
    }
}
