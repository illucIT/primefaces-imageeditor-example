package com.illucit.test;

import java.io.IOException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.illucit.faces.component.imageeditor.ImageEditedEvent;

@ViewScoped
@Named
public class ImageEditorBean {

	public StreamedContent getImage() throws IOException {
		URL imageUrl = getClass().getClassLoader().getResource("image.png");
		if (imageUrl == null) throw new IOException("Image not found");
		return new DefaultStreamedContent(imageUrl.openStream(), "image/png", getFilename());
	}

	public String getFilename() {
		return "example.png";
	}

	public void onImageSaved(ImageEditedEvent event) {
		System.out.println("Saved " + event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Image Saved", "Your image was saved."));
	}

}
