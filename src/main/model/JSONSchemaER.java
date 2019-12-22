package model;

import control.JsonParser;
import control.Parser;

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

import org.json.simple.JSONObject;


@WebServlet("/JSONSchemaER")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50) 
public class JSONSchemaER extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public JSONSchemaER() {
    super();
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    Part part = request.getPart("file");
    // String url = "../util/test.xmi";
    response.setContentType("text/html");
    
    InputStream inputStream = part.getInputStream();
    String contentType = part.getContentType();
    String pathname = "copyFileDatabase.xmi";
    File copy = new File(pathname);
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
    
    Parser p = new Parser();
    ERBean er = p.parser(copy);

    JSONObject obj = JsonParser.parseToJson(er);
    response.getWriter().append(obj.toJSONString());
    
    copy.delete();
    
    /*
  response.getWriter().append("[{");
    if (er.getEntity() != null) {
      for (int i = 0; i < er.getEntity().size(); i++) {
        response.getWriter().append("\"entita\": \"" + er.getEntity().get(i).getName() + "\",");
        if (i != er.getEntity().size() - 1) {
          for (int j = 0; j < er.getEntity().get(i).getAttribute().size(); j++) {
            response.getWriter().append("\"attributo\": \""
            + er.getEntity().get(i).getAttribute().get(j) + "\",");
          }
        } else {
          for (int j = 0; j < er.getEntity().get(i).getAttribute().size(); j++) {
            if (i != er.getEntity().get(i).getAttribute().size() - 1) {
              response.getWriter().append("\"attributo\": \""
              + er.getEntity().get(i).getAttribute().get(j) + "\",");
            } else {
              response.getWriter().append("\"attributo\": \""
              + er.getEntity().get(i).getAttribute().get(j) + "\"}");
            }
          } 

        }
      }
    }
    response.getWriter().append("]");
*/
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}