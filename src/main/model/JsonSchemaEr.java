package model;

import control.JsonParser;
import control.Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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
public class JsonSchemaEr extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public JsonSchemaEr() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

	File copy;
	HttpSession sessione = request.getSession();
	String url = request.getParameter("presente");
	Part part = request.getPart("file");

	
	if(part == null) {	
		copy = (File) sessione.getAttribute(url);
		System.out.println(copy);
	} else {
		if(url.equals("Nframe1")) {
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
			sessione.setAttribute(url , copy);
			sessione.setAttribute("NomeFile1" , part.getSubmittedFileName());

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
				System.out.println(copy);

				sessione.setAttribute(url , copy);
				sessione.setAttribute("NomeFile2" , part.getSubmittedFileName());

		}
	}
	Parser p = new Parser();
	ErBean er = p.parser(copy);

	JSONObject obj = JsonParser.parseToJson(er);
	response.getWriter().append(obj.toJSONString());
    //copy.delete();
    
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

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
