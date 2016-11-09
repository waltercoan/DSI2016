package control;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name="crtupload")
public class CrtUpload {
   
   public void handleFileUpload(FileUploadEvent event) {
	   try {
		   	InputStream in = event.getFile().getInputstream();
	        OutputStream out = new FileOutputStream("c:/temp/" + event.getFile().getFileName());
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
       FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
       FacesContext.getCurrentInstance().addMessage(null, message);
       
   }

}