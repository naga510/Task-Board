package com.src.board.resources;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="output")
public class CustomResponse<T> {

	private T output;
	
	public CustomResponse(T output) {
		this.output = output;
	}

	public T getOutput() {
		return output;
	}

	public void setOutput(T output) {
		this.output = output;
	}

	
	
	
}
