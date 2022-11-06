package Servlet;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.Entiteta;
import si.fri.prpo.Uporabnik;
import si.fri.prpo.UporabnikDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // implementacija


        UporabnikDaoImpl vmesnik = UporabnikDaoImpl.getInstance();

        resp.getWriter().println("Vstavljam uporabnika Janko Marjanko");
        Uporabnik vstavi = new Uporabnik("Janko", "Marjanko", "janko_marjanko");
        resp.getWriter().println("Uporabnik vstavljen");
        vmesnik.vstavi(vstavi);

        Uporabnik izpisi = (Uporabnik)vmesnik.vrni(1);
        resp.getWriter().println("Uporabniku z id 1 je ime " + izpisi.getIme());

        List<Entiteta> vsi = vmesnik.vrniVse();
        resp.getWriter().println("Izpisujem vse uporabnike");

        for(Entiteta e : vsi){
            Uporabnik pom = (Uporabnik)e;
            resp.getWriter().println(pom.getUporabnisko() + " " + pom.getIme() + " " + pom.getPriimek());
        }

        resp.getWriter().println("Brisem uporabnika z ID = 2");
        vmesnik.odstrani(2);

        resp.getWriter().println("Posodabljam uporabnika z ID = 1");
        Uporabnik posodobi = new Uporabnik("Posodobljen", "Lump", "posodobljen_lumpek");
        posodobi.setId(1);
        vmesnik.posodobi(posodobi);




    }
}
