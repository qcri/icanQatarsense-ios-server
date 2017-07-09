package com.qsense.dao;

import java.util.List;

import com.qsense.exception.QSenseDAOException;

/**
 * 
 * @author Neeraj
 *
 * @param <E>
 */
public interface CommonDAO<E> {

	/**
	 * Method to persist entity to database
	 * 
	 * @param entity
	 * @throws QSenseDAOException
	 * @return
	 */
	public E create(E entity) throws QSenseDAOException;

	/**
	 * Update state of entity
	 * 
	 * @param entity
	 * @throws QSenseDAOException
	 * @return
	 */
	public E update(E entity) throws QSenseDAOException;

	/**
	 * Delete entity from database
	 * 
	 * @param entity
	 * @throws QSenseDAOException
	 * @return
	 */
	public boolean remove(E entity) throws QSenseDAOException;

	/**
	 * Get entity<E> from database by primaryKey *
	 * 
	 * @param id
	 * @return
	 */
	public E getById(Long id) throws QSenseDAOException;
	
	/**
	 * @return All Entities
	 * 
	 * @throws QSenseDAOException
	 */
	public List<E> getAll() throws QSenseDAOException;

	

}
