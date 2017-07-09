package com.qsense.service;

import java.util.List;

import com.qsense.transfer.SubCategoryTO;

public interface SubCategoryService {	

	List<SubCategoryTO> getAllAdminDashboardVisible();

	List<SubCategoryTO> getAllIsManuallyAllowed();	

}
