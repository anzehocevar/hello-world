package Servlet;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // implementacija

        System.out.println();
        resp.getWriter().println("pozdravljeni");
        final String ver = ConfigurationUtil.getInstance().get("kumuluzee.version").get();
        final String name = ConfigurationUtil.getInstance().get("kumuluzee.name").get();
        final String env = ConfigurationUtil.getInstance().get("kumuluzee.env.name").get();

        resp.getWriter().println(ver);
        resp.getWriter().println(name);
        resp.getWriter().println(env);

    }
}
