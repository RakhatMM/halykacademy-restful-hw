package kz.halykacademy.restfulhw.web;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.halykacademy.restfulhw.model.Organization;
import kz.halykacademy.restfulhw.service.LocalDateSerializer;

@WebServlet(urlPatterns={"/organizations", "/organizations/*"})
public class OrganizationServlet extends HttpServlet {
    private HashMap<Long, Organization> organizations = new HashMap<>();

    public void init() {

        Organization org1 = new Organization("Halyk Group", "Nur-Sultan, Kazakhstan", LocalDate.of(1923, 8, 15));
        Organization org2 = new Organization("BTS Digital", "Nur-Sultan, Kazakhstan", LocalDate.of(2018, 1, 1));
        Organization org3 = new Organization("Oracle Corporation", "Austin, USA", LocalDate.of(1977, 06, 16));

        organizations.put(org1.getId(), org1);
        organizations.put(org2.getId(), org2);
        organizations.put(org3.getId(), org3);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String organizationJsonString = gson.toJson(organizations);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(organizationJsonString);
        out.flush();

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long id = Long.valueOf(request.getPathInfo().substring(1));

        if (organizations.containsKey(id)) {
            organizations.remove(id);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(Map.of("message", "Organization is deleted!")));
        } else {
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(Map.of("error", "No such organization!")));
        }

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String title = request.getParameter("title");
        String address = request.getParameter("address");
        LocalDate creationDate = LocalDate.parse(request.getParameter("creationDate"));

        Organization newOrg = new Organization(title, address, creationDate);

        organizations.put(newOrg.getId(), newOrg);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        response.getWriter().write(gson.toJson(newOrg));

    }

    public void destroy() {
    }
}