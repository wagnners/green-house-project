package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.entidade.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 9584013
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class FiltroLogin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest requisicao = (HttpServletRequest) request;
        HttpSession session = requisicao.getSession();

//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//
//        System.out.println(usuario + " " + this.getClass().getSimpleName());
        if ((session.getAttribute("usuario") != null)
                || (requisicao.getRequestURI().endsWith("admin/login.jsf"))
                || (requisicao.getRequestURI().endsWith("house/"))
                || (requisicao.getRequestURI().endsWith("index.jsf"))
                || (requisicao.getRequestURI().contains("javax.faces.resource/"))) {

           if ((session.getAttribute("usuario") != null && (requisicao.getRequestURI().endsWith("admin/login.jsf")))){
               redireciona("home.jsf", response);
           }
            
            System.out.println("filtrando");
//            System.out.println(usuario + " " + this.getClass().getSimpleName());
            chain.doFilter(request, response);
        } else {
            System.out.println("redirecionando");
//            System.out.println(usuario + " " + this.getClass().getSimpleName());
            redireciona("login.jsf", response);
        }

    }

    private void redireciona(String url, ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
