package com.qsense.adapter;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EntityTOMapper<E,T> {
	public E toEntity(T dto) throws ParseException;
	public T toDTO(E entity);
	public List<E> toEntity(Collection<T> dtos) throws ParseException;
	public List<T> toDTO(Collection<E> entities);
}
