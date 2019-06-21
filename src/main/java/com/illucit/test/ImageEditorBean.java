package com.illucit.test;

import com.illucit.faces.component.imageeditor.ImageEditedEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@SessionScoped
@Named
public class ImageEditorBean implements Serializable {

	public StreamedContent getImage() throws IOException {
		URL imageUrl = getClass().getClassLoader().getResource("image.png");
		if (imageUrl == null) throw new IOException("Image not found");
		return new DefaultStreamedContent(imageUrl.openStream(), "image/png", getFilename());
	}


	public String getTitle() {
		return "Image Editor Example";
	}

	public String getFilename() {
		return "example.png";
	}

	public void onImageSaved(ImageEditedEvent event) {
		System.out.println("Saved " + event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        "Image Saved", "Your image has been saved."));
	}

}
