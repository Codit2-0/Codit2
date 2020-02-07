package control;

import control.OntologyManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ErBean;

import org.json.simple.JSONObject;


@WebServlet("/JSONSchemaER")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50) 
public class ServletCaricaJsonSchemaEr extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ServletCaricaJsonSchemaEr() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    File copy;
    HttpSession sessione = request.getSession();
    String url = request.getParameter("presente");
    Part part = request.getPart("file");


    if (part == null) {
      copy = (File) sessione.getAttribute(url);
    } else {
      if (url.equals("Nframe1")) {
        response.setContentType("text/html");
        //sessione.removeAttribute("Nframe2");

        InputStream inputStream = part.getInputStream();
        String contentType = part.getContentType();
        String pathname = "copyFileDatabase1.xmi";
        copy = new File(pathname);
        OutputStream outputStream = new FileOutputStream(copy);
        try {
          byte[] buffer = new byte[1024];
          int length;
          while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
          }
        } finally {
          inputStream.close();
          outputStream.close();
        }
        sessione.setAttribute(url, copy);
        sessione.setAttribute("NomeFile1", part.getSubmittedFileName());

      } else {
        response.setContentType("text/html");
        //sessione.removeAttribute("Nframe1");

        InputStream inputStream = part.getInputStream();
        String contentType = part.getContentType();
        String pathname = "copyFileDatabase2.xmi";
        copy = new File(pathname);
        OutputStream outputStream = new FileOutputStream(copy);
        try {
          byte[] buffer = new byte[1024];
          int length;
          while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
          }
        } finally {
          inputStream.close();
          outputStream.close();
        }

        sessione.setAttribute(url, copy);
        sessione.setAttribute("NomeFile2", part.getSubmittedFileName());

      }
    }
    ServerFacade sF = new ServerFacade();
    ErBean er = sF.parserXMI(copy);
    
    if (part != null) {
      sF.saveOntology(er, part.getSubmittedFileName().substring(0, 
                              part.getSubmittedFileName().lastIndexOf(".")));
    }
    
    JSONObject obj = sF.parserBean(er);
    response.getWriter().append(obj.toJSONString());
    //copy.delete();

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
