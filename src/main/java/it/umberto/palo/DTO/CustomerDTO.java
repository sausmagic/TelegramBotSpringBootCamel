package it.umberto.palo.DTO;

import it.umberto.palo.datamongodb.model.Customer;

public class CustomerDTO extends Customer {

	public CustomerDTO(String name, int age) {
		super(name, age);
	}
	
	private boolean isInserted;
	private String Status;
	private String ErrorCode;
	private String ErrorMessage;
	public boolean isInserted() {
		return isInserted;
	}
	public void setInserted(boolean isInserted) {
		this.isInserted = isInserted;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [isInserted=" + isInserted + ", Status=" + Status + ", ErrorCode=" + ErrorCode
				+ ", ErrorMessage=" + ErrorMessage + "]";
	}
	
	

}
