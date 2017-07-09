package com.qsense.service;

import java.util.List;

import com.qsense.transfer.RoleTO;

public interface RoleService {	

	List<RoleTO> getAll();

	List<RoleTO> getAllWithoutAdmin();	

}
