package com.qsense.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.qsense.dao.CommonDAO;
import com.qsense.entity.BaseEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.util.CurrentContext;
import com.qsense.util.Pagination;
import com.qsense.util.SortOrderTypeEnum;
import com.qsense.util.logger.QSenseLogger;



/**
 * CommonDaoImpl is an abstract class and it perform common CRUD operation and
 * all DAO need to extend this class
 * 
 * @author Neeraj
 * 
 * @param <E>
 */
public abstract class CommonDAOImpl<E> implements CommonDAO<E>
{

    @PersistenceContext (type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
    protected QSenseLogger logger = QSenseLogger.getLogger(getClass());
    
    protected CriteriaBuilder builder;
    protected CriteriaQuery<E> criteria;
    protected CriteriaUpdate<E> criteriaUpdate;
    protected Root<E> root;
    protected Root<E> rootUpdate;
    private Class<E> modelClass; // use this with getModelClass() api only not direct
    
    /**
     * Initialize common attributes
     * This method must be called in every DAOImpl if that class is using the common attributes provided here in this class.
     */
    protected void initCommonAttributes(){
    	this.modelClass = getModelClass();
    	builder = entityManager.getCriteriaBuilder();
        criteria = builder.createQuery(getModelClass());
        criteriaUpdate = builder.createCriteriaUpdate(getModelClass());
        rootUpdate = criteriaUpdate.from(getModelClass());
        root = criteria.from(getModelClass());          
        criteria.select(root);        
    }
    
    /**
     * This method is used to provide the template type class used in the DAOImpl 
     * @return
     */
    protected abstract Class<E> getModelClass();
	 
    /*
     * Method to persist entity to database (non-Javadoc)
     * 
     */
    public E create(E entity) throws QSenseDAOException
    {
    	try{
    		entityManager.persist(entity);
    	}catch(PersistenceException pe){
    		logger.error("Unable to persist entity :"+entity+" in DB", pe);
    		throw new QSenseDAOException(pe);
    	}
        
        return entity;
    }
  
    /*
     * Update state of entity (non-Javadoc)
     * 
     */
    public E update(E entity) throws QSenseDAOException
    {
    	E create;
    	try{
    		create = create(entityManager.merge(entity));
    	}catch(PersistenceException pe){
    		logger.error("Unable to update entity :"+entity+" in DB", pe);
    		throw new QSenseDAOException(pe);
    	}
        return create;
    }

    /*
     * Delete entity from database (non-Javadoc)
     * 
     */
    public boolean remove(E entity) throws QSenseDAOException
    {
    	try{
        entityManager.remove(entity);
    	}catch(PersistenceException pe){
    		logger.error("Unable to remove entity :"+entity+" from DB", pe);
    		throw new QSenseDAOException(pe);
    	}
        return true;
    }

    /*
     * get entity from database(non-Javadoc)
     * 
     */
    public E getById(Long id) throws QSenseDAOException
    {
    	E entity;
    	try{
        entity = entityManager.find(getModelClass(), id);
    	}catch(IllegalArgumentException ile){
    		logger.error("Unable to get entity from id:"+id, ile);
    		throw new QSenseDAOException(ile);
    	}
        return entity;
    }
    
    public List<E> getAll() throws QSenseDAOException
    {
    	initCommonAttributes();
    	return getResults();
    }
    
    
    /*Get Paginated Entity List
	 * @return 
	 * */
    protected List<E> getPaginatedResults(){
		Pagination pagination = CurrentContext.getPagination();
		List<E> entities; 
		try{
		if(pagination != null){
			String sortColumn = pagination.getSortColumn();
			if(StringUtils.isNotBlank(sortColumn)){
				if(SortOrderTypeEnum.DESC == pagination.getSortOrder() )
					criteria.orderBy(builder.desc(root.get(sortColumn)));
				else
					criteria.orderBy(builder.asc(root.get(sortColumn)));
			}
				
			if(pagination.getOffset()!=null && pagination.getLimit() != null)
					return entityManager.createQuery(criteria).setFirstResult(pagination.getOffset().intValue()).setMaxResults(pagination.getLimit().intValue()).getResultList();
		}
		
		entities = entityManager.createQuery(criteria).getResultList();
		}catch(IllegalArgumentException ile){
    		logger.error("Unable to get paginated results ", ile);
    		throw new QSenseDAOException(ile);
    	}
		return entities;
	}
	
	 /*Get Single Result
		 * @return 
		 * */
    protected E getSingleResult(){
    	E entity = null;
    	try{
		entity = entityManager.createQuery(criteria).getSingleResult();
    	}catch(NoResultException e){
    		logger.error("There is no result for entity");    		
    	}
    	catch(Exception e){
    		logger.error("Unable to get Single result ", e);
    		throw new QSenseDAOException(e);
    	}
		return entity;
	}
		
	 /*Get Result List without paginated
	 * @return 
	 * */
	protected List<E> getResults(){
		List<E> resultList;
		try{
		resultList = entityManager.createQuery(criteria).getResultList();
		}catch(Exception ile){
    		logger.error("Unable to get Results ", ile.getMessage());
    		ile.printStackTrace();
    		throw new QSenseDAOException(ile);
    	}
		if(resultList == null) {
			return new ArrayList<E>();
		}
		return resultList;
	}
	
	/*
	 * Gives List of rows limited to no given as parameter.
	 */
	protected List<E> getResults(int noOfRows){
		List<E> resultList;
		try{
		resultList = entityManager.createQuery(criteria).setMaxResults(noOfRows).getResultList();
		}catch(Exception ile){
    		logger.error("Unable to get Results ", ile);
    		throw new QSenseDAOException(ile);
    	}
		if(resultList == null) {
			return new ArrayList<E>();
		}
		return resultList;
	}
	
	/*
	 * Gives first row from the result set.
	 */
	protected E getFirstResult() {
		List<E> results = getResults(1);
		if(CollectionUtils.isNotEmpty(results)) {
			return results.get(0);
		}
		return null;
	}
    
	public void setEntityManager(EntityManager entityManager) {  
	    this.entityManager = entityManager ;
	}
	
	protected void executeNativeUpdate(String query){
		entityManager.createNativeQuery(query).executeUpdate();
	}
	
	protected List<E> executeNativeFetch(String query){
		return entityManager.createNativeQuery(query, getModelClass()).getResultList();
	}	
	
	protected String getTableName(){
		return getModelClass().getAnnotation(Table.class).name();
	}
	
	/**
	 * Can be used when you want to fetch multiple rows based on some column
	 * @param criteriaColumn column on which you want to put IN query.
	 * @param values list of values you want to provide for IN query
	 * @param columns column names which you want to select. Actually it is property name of entity.
	 */
	protected List<E> executeINFetch(String criteriaColumn, Set<Long> values, String ... columns) {
		if(CollectionUtils.isEmpty(values)) {
			return Collections.EMPTY_LIST;
		}
		Expression<Long> expression = root.get(criteriaColumn);
		Predicate predicate = expression.in(values);
		for(String column: columns) {
			criteria.multiselect(root.get(column));
		}
		criteria.where(predicate);
		return getResults();
	}
	
	/**
	 * Can be used when you want to fetch multiple rows based on some column
	 * @param criteriaColumn column on which you want to put IN query.
	 * @param values list of values you want to provide for IN query
	 * @param updateColumn column name which you want to update.
	 * @param value updated value for the column.
	 */
	protected int executeINUpdate(String criteriaColumn, Collection<Long> values, String updateColumn, Object value) {
		if(CollectionUtils.isEmpty(values)) {
			return 0;
		}
		Expression<Long> expression = rootUpdate.get(criteriaColumn);
		Predicate predicate = expression.in(values);
		criteriaUpdate.set(rootUpdate.get(updateColumn), value);
		criteriaUpdate.where(predicate);
		return executeUpdate();		
	}
	
	protected int executeUpdate() {
		return entityManager.createQuery(criteriaUpdate).executeUpdate();
	}
	
	protected Set<Long> getIdsFromEntities(Collection<E> entities) {
		Set<Long> ids = new HashSet<>();
		for(E e: entities) {
			ids.add(((BaseEntity)e).getId());
		}
		return ids;
	}
	
	public void flush() {
		try {
			entityManager.flush();
		} catch (Exception e) {
			logger.error("Unable to flush the db at this time: %s", e.getMessage());
		}		
	}
	
	public void clear() {
		try {
			entityManager.clear();
		} catch (Exception e) {
			logger.error("Unable to clear the sesion at this time: %s", e.getMessage());
		}
	}
	
	public void hardFlush() {
		try {			
			flush();
			clear();
			//entityManager.getTransaction().commit();
			//entityManager.getTransaction().begin();
			
		} catch (Exception e) {
			logger.error("Unable to flush the db at this time: %s", e.getMessage());
		}	
	}
}
