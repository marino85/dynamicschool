/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.filter;

import com.ccit.web.util.WebUtil;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;   
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author marino
 */
public class FiltroSeguridad implements Filter {

    private static final boolean debug = false;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private String loginPage = "";

    public FiltroSeguridad() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
           // log("FiltroSeguridad:doFilter()");
        

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        if (!WebUtil.esValidoMetodo(req.getMethod())) {
            res.sendError(ResponseWrapper.SC_FORBIDDEN);
            return;
        }

            String uriSolicitada = req.getRequestURI();  
            
            if(WebUtil.esExtensionPermitidaPorGet(uriSolicitada)){
                chain.doFilter(request, response);
                return;
            }
            log(uriSolicitada);
          
            boolean autenticado = false;
            HttpSession sesssion = req.getSession(false);
            if (sesssion != null) {
                
                if(sesssion.getAttribute("userLogged")!=null){
                    autenticado=true;
                     //log("auntenticado");
                }
               
            }
            if(autenticado){
                
                chain.doFilter(request, response);
             
            }
            else{
                if(uriSolicitada.contains("pagosonline")){
                    chain.doFilter(request, response);
                    return;
                }
                if(uriSolicitada.contains("lostpasswd")){
                    log("accede a recuperar contraseña");
                    chain.doFilter(request, response);
                    return;
                }
                if(uriSolicitada.contains("nuevosEstudiantes")){
                    log("accede nuevos estudiantes");
                    chain.doFilter(request, response);
                   
                }
                else{
                 redirectLogin(req, res);
                }
            }
        





    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltroSeguridad: Initializing filter");
            }
            loginPage = filterConfig.getInitParameter("loginPage");
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroSeguridad()");
        }
        StringBuffer sb = new StringBuffer("FiltroSeguridad(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());

    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) {

        try {
            RequestDispatcher fw = request.getRequestDispatcher(loginPage);
            fw.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(FiltroSeguridad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FiltroSeguridad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This request wrapper class extends the support class
     * HttpServletRequestWrapper, which implements all the methods in the
     * HttpServletRequest interface, as delegations to the wrapped request. You
     * only need to override the methods that you need to change. You can get
     * access to the wrapped request using the method getRequest()
     */
    class RequestWrapper extends HttpServletRequestWrapper {

        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }
        // You might, for example, wish to add a setParameter() method. To do this
        // you must also override the getParameter, getParameterValues, getParameterMap,
        // and getParameterNames methods.
        protected Hashtable localParams = null;

        public void setParameter(String name, String[] values) {
            if (debug) {
                System.out.println("FiltroSeguridad::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
            }

            if (localParams == null) {
                localParams = new Hashtable();
                // Copy the parameters from the underlying request.
                Map wrappedParams = getRequest().getParameterMap();
                Set keySet = wrappedParams.keySet();
                for (Iterator it = keySet.iterator(); it.hasNext();) {
                    Object key = it.next();
                    Object value = wrappedParams.get(key);
                    localParams.put(key, value);
                }
            }
            localParams.put(name, values);
        }

        @Override
        public String getParameter(String name) {
            if (debug) {
                System.out.println("FiltroSeguridad::getParameter(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameter(name);
            }
            Object val = localParams.get(name);
            if (val instanceof String) {
                return (String) val;
            }
            if (val instanceof String[]) {
                String[] values = (String[]) val;
                return values[0];
            }
            return (val == null ? null : val.toString());
        }

        @Override
        public String[] getParameterValues(String name) {
            if (debug) {
                System.out.println("FiltroSeguridad::getParameterValues(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterValues(name);
            }
            return (String[]) localParams.get(name);
        }

        @Override
        public Enumeration getParameterNames() {
            if (debug) {
                System.out.println("FiltroSeguridad::getParameterNames() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterNames();
            }
            return localParams.keys();
        }

        @Override
        public Map getParameterMap() {
            if (debug) {
                System.out.println("FiltroSeguridad::getParameterMap() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterMap();
            }
            return localParams;
        }
    }

    /**
     * This response wrapper class extends the support class
     * HttpServletResponseWrapper, which implements all the methods in the
     * HttpServletResponse interface, as delegations to the wrapped response.
     * You only need to override the methods that you need to change. You can
     * get access to the wrapped response using the method getResponse()
     */
    class ResponseWrapper extends HttpServletResponseWrapper {

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }
        // You might, for example, wish to know what cookies were set on the response
        // as it went throught the filter chain. Since HttpServletRequest doesn't
        // have a get cookies method, we will need to store them locally as they
        // are being set.
	/*
         * protected Vector cookies = null;
         *
         * // Create a new method that doesn't exist in HttpServletResponse
         * public Enumeration getCookies() { if (cookies == null) cookies = new
         * Vector(); return cookies.elements(); }
         *
         * // Override this method from HttpServletResponse to keep track // of
         * cookies locally as well as in the wrapped response. public void
         * addCookie (Cookie cookie) { if (cookies == null) cookies = new
         * Vector(); cookies.add(cookie);
         * ((HttpServletResponse)getResponse()).addCookie(cookie); }
         */
    }
}
