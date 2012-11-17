package servicios;
/*
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public class UploadResource extends DBResource{
	
	@Post
	public String upload(Representation entity){
		System.out.println(entity.toString());
		System.out.println(entity.getMediaType());
		if(entity != null){
			if(MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(),true)){
				File storeDirectory = new File("/uploads/");
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List items = upload.parseRequest((HttpServletRequest) getRequest());
					Iterator iterator = items.iterator();
					while (iterator.hasNext()) {
						FileItem item = (FileItem) iterator.next();

						if (!item.isFormField()){
							String fileName = item.getName();

							String root = ((ServletRequest) getContext()).getRealPath("/");
							File path = new File(root + "/uploads");
							if (!path.exists()) {
								boolean status = path.mkdirs();
							}

							File uploadedFile = new File(path + "/" + fileName);
							System.out.println(uploadedFile.getAbsolutePath());
							item.write(uploadedFile);
						}
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}*/
