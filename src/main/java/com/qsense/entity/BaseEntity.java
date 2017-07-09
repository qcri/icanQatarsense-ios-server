package com.qsense.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 7585020535929309863L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "Id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
