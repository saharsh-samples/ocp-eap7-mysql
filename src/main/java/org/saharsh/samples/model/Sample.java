package org.saharsh.samples.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sample")
public class Sample {

	@Id
	private String id;

	@Column(name = "value", length = 1024, nullable = false)
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", value=" + value + "]";
	}

}
