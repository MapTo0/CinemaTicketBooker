package cinema.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.User;
import cinema.services.UserContext;

@WebFilter({"/rest/projection/*", "/rest/bookig/*", "/rest/viewtickets/*"})
public class ProtectedBuyFilter implements Filter {

	@Inject
	UserContext userContext;

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!isHttpCall(request, response)) {
			return;
		}
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		User currentUser = userContext.getCurrentUser();
		if (currentUser == null) {
			String loginUrl = httpServletRequest.getContextPath()
					+ "/notauthorized.html";
			httpServletResponse.sendRedirect(loginUrl);
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isHttpCall(ServletRequest request, ServletResponse response) {
		return (request instanceof HttpServletRequest)
				&& (response instanceof HttpServletResponse);
	}

	public void destroy() {
	}

}
