package com.qsense.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsense.dao.UserDAO;
import com.qsense.entity.UserEntity;
import com.qsense.util.ApplicationContextProvider;
import com.qsense.util.RoleEnum;

@Transactional(readOnly = true)
@Service("UserSecurityService")
public class UserSecurityService implements UserDetailsService {
	
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = null;
		userDAO = ApplicationContextProvider.getApplicationContext().getBean(
				"userDAO", UserDAO.class);
		UserEntity userEntity = userDAO.findUserByUserName(userName);
		if (userEntity == null) {
			throw new UsernameNotFoundException(
					"Unable to find user with the given username:" + userName);
		}else if(userEntity.getRole().getName() != RoleEnum.ADMIN){
			throw new PermissionDeniedDataAccessException("Only admin can access the application"+ userName,new Exception().getCause());
		}
		userDetails = adaptUserDetailsFromUser(userEntity);

		return userDetails;
	}

	public static UserDetails adaptUserDetailsFromUser(UserEntity user) {
		LoggedInUser userDetails = new LoggedInUser();

		if (user != null) {
			userDetails.setId(user.getId());
			userDetails.setPassword(user.getPassword());
			userDetails.setAccountNonExpired(true);
			userDetails.setAccountNonLocked(true);
			userDetails.setCredentialsNonExpired(true);
			userDetails.setEnabled(true);
			userDetails.setUsername(user.getUserName()); 
			Set<GrantedAuthorityImpl> userPermissions = new HashSet<GrantedAuthorityImpl>();

			
			userDetails.setAuthorities(new ArrayList(userPermissions));
		}
		

		return userDetails;
	}
}
