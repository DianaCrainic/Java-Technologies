package com.uaic.lab2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ResponseDecorator", urlPatterns = {"/*"})
public class ResponseDecorator implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        SimpleResponseWrapper wrapper
                = new SimpleResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);
        String content = wrapper.toString();
        content = getHeader() + content + getFooter();
        PrintWriter out = response.getWriter();
        out.write(content);
    }

    private String getFooter() {
        return "</br></br><footer>This is the Footer</footer></br>";
    }

    private String getHeader() {
        return "<header>This is the Header</header></br>";
    }

}
