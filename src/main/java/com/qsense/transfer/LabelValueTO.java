package com.qsense.transfer;

public class LabelValueTO {

	private String name;

	private String value;

	public LabelValueTO() {

	}

	public LabelValueTO(String name,String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelValueTO [name=" + name + ", value=" + value + "]";
	}

}
