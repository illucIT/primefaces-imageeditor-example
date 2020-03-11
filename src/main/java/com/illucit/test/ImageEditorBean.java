package com.illucit.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

import com.illucit.faces.component.imageeditor.ImageEditedEvent;

@SessionScoped
@Named
public class ImageEditorBean implements Serializable {

    public StreamedContent getImage() throws IOException {
        URL imageUrl = getClass().getClassLoader().getResource("image.png");
        if (imageUrl == null) throw new IOException("Image not found");
        SerializableSupplier<InputStream> streamSupplier = () -> {
            try {
                return imageUrl.openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return DefaultStreamedContent.builder().stream(streamSupplier)
                .contentType("image/png")
                .name(getFilename())
                .build();
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
