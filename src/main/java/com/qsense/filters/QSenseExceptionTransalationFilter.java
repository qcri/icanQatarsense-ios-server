
package com.qsense.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.qsense.common.CommonReturnCodes;
import com.qsense.common.Result;
import com.qsense.exception.QSenseException;
import com.qsense.util.CommonConstants;
import com.qsense.util.logger.QSenseLogger;




public class QSenseExceptionTransalationFilter extends GenericFilterBean
{
    QSenseLogger logger = QSenseLogger
                          .getLogger(QSenseExceptionTransalationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        try
        {
            chain.doFilter(request, response);
        }
        catch (QSenseException ex)
        {
            logger.error(
                    "QSenseExceptionTransalationFilter : In QSenseException ",
                    ex);
            renderErrorResults(request, response, ex.getErrorCode(),
                    ex.getType());
        }
        catch (Exception ex)
        {
            logger.error(
                    "QSenseExceptionTransalationFilter: In Exception ", ex);
            if (ex.getCause() instanceof QSenseException) {
            	QSenseException rex = (QSenseException)ex.getCause();
                renderErrorResults(request, response,
                		rex.getErrorCode(), rex.getType());
            } else {
	            renderErrorResults(request, response,
	                    CommonReturnCodes.ERROR_EXCEPTION, ex.getMessage());
            }
        }
    }

    private void renderErrorResults(final ServletRequest request,
            final ServletResponse response, final String errId,
            final String errType)
    {
        logger.info("Begin : QSenseExceptionTransalationFilter.renderErrorResults");
        Result result = new Result();
        result.setErrorSet(true);
       // Error error = new Error(errId, errType);
        //result.addError(error);
        String alt = request.getParameter(CommonConstants.PARAM_ALT_STRING);
        /*if(CommonConstants.XML_STRING.equalsIgnoreCase(alt)){
			Utils.renderResponse( (HttpServletResponse)response,  result, ResponseTypeEnum.XML);
		}else if(CommonConstants.SCHEMA_STRING.equalsIgnoreCase(alt)){
			Utils.renderResponse((HttpServletResponse) response,  result, ResponseTypeEnum.SCHEMA);
		}
		else{
			Utils.renderResponse((HttpServletResponse) response,  result, ResponseTypeEnum.JSON);
		}*/
        logger.info("End : QSenseExceptionTransalationFilter.renderErrorResults");
    }

}
