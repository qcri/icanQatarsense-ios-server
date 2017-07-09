package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.ObservationBlockDAO;
import com.qsense.entity.ObservationBlockEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * ObservationBlock Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("ObservationBlockDAO")
public class ObservationBlockDAOImpl extends CommonDAOImpl<ObservationBlockEntity> implements ObservationBlockDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<ObservationBlockEntity> getModelClass() {
		return ObservationBlockEntity.class;
	}
	
	
	
}
