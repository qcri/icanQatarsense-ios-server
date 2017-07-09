package com.qsense.util;

import com.qsense.security.LoggedInUser;




/**
 * 
 * @author Neeraj
 * 
 */
public class CurrentContext {

	private static ThreadLocal<InfoWrapper> currentContext = new ThreadLocal<InfoWrapper>();

	public static void set(Pagination pagination, LoggedInUser loggedInUser,
			String responseType) {
		if (currentContext.get() == null) {
			currentContext.set(new InfoWrapper(pagination, loggedInUser,responseType));
		}
	}

	private static class InfoWrapper {
		private Pagination pagination;
		private String responseType;
		private String userName;
		private long userId; 

		public InfoWrapper(Pagination pagination, LoggedInUser loggedInUser,
				String responseType) {
			this.pagination = pagination;
			this.userName = loggedInUser.getUsername();
			this.userId = loggedInUser.getId();
			this.responseType = responseType;
		}
	};

	/**
	 * @return the user
	 */
	public static String getUserName() {
		return currentContext.get()==null ? CommonConstants.ADMIN : currentContext.get().userName;
	}

	/**
	 * @return the user
	 */
	public static long getUserId() {
		return currentContext.get()==null ? new Long(1) : currentContext.get().userId;
	}

	/**
	 * @return the pagination
	 */
	public static Pagination getPagination() {
		return currentContext.get()==null ? null : currentContext.get().pagination;	
	}

	/**
	 * @return the responseType
	 */
	public static String getResponseType() {
		return currentContext.get()==null ? null : currentContext.get().responseType;
	}

	/**
	 *  remove the thread local to protect memory leak
	 */
	public static void remove() {
		currentContext.remove();
	}
}
