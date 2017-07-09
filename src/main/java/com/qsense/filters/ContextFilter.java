
package com.qsense.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qsense.security.LoggedInUser;
import com.qsense.util.CommonConstants;
import com.qsense.util.CurrentContext;
import com.qsense.util.Pagination;
import com.qsense.util.SortOrderTypeEnum;
import com.qsense.util.Utils;




/**
 * Filter responsible for validating security for the REST API calls
 * 
 * @author Neeraj
 */

public class ContextFilter extends OncePerRequestFilter {

	private Logger logger = LogManager.getLogger(getClass());


	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{
			Pagination pagination = getPagination(request); 
			LoggedInUser loggedInUser = Utils.getCurrentLoggedInUser();
			String responseType = (String)request.getParameter(CommonConstants.PARAM_ALT_STRING);
			CurrentContext.set(pagination, loggedInUser, responseType);
			
			filterChain.doFilter(request, response);
		}
		finally{
			CurrentContext.remove();
		}
	}
	
	/**
	 * Get the pagination information from the request and returns the pagination object.
	 * Pagiantion is null if parameter offset or limit and sortColumn is not passed in request   
	 * @param request
	 * @return
	 */
	private Pagination getPagination(HttpServletRequest request) {
		String paramOffSet = request.getParameter(CommonConstants.PARAM_OFFSET_STRING);
		String paramLimit = request.getParameter(CommonConstants.PARAM_LIMIT_STRING);
		String paramSortColumn = request.getParameter(CommonConstants.PARAM_SORT_COLUMN_STRING);
		String paramSortOrder =  request.getParameter(CommonConstants.PARAM_SORT_ORDER_STRING);
		
		if((paramLimit == null || paramOffSet == null) && StringUtils.isBlank(paramSortColumn))
			return null;
			
		Long offset = (StringUtils.isNotBlank(paramOffSet) && Long.parseLong(paramOffSet) >0 ) ? Long.parseLong(paramOffSet) : null ; 
		Long limit =  (StringUtils.isNotBlank(paramLimit) && Long.parseLong(paramLimit) >0) ? Long.parseLong(paramLimit) : null ;
		Enum sortOrderEnum = SortOrderTypeEnum.ASC;
		if(StringUtils.equalsIgnoreCase(paramSortOrder,"DESC"))
			sortOrderEnum = SortOrderTypeEnum.DESC;

		Pagination pagination = new Pagination(offset,limit,sortOrderEnum,paramSortColumn);
		return pagination;
	}
	
}