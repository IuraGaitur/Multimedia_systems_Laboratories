package com.app.lab.models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import com.app.lab.interfaces.IProcessingMethods;

public class ImageModel implements IProcessingMethods {
	
	private String name;
	private String locationString;
	private Image image ;
	private Image editImage;
	
	public ImageModel(Image image) {
		super();
		this.image = image;
		this.editImage = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationString() {
		return locationString;
	}
	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	@Override
	public void rotate90Degree() {
		int angle = 90;
		double sin = Math.abs(Math.sin(Math.toRadians(angle))),
				cos = Math.abs(Math.cos(Math.toRadians(angle)));
		
		int w = this.editImage.getWidth(null), h = this.image.getHeight(null);
		int newWidth = (int)Math.floor(w*cos + h*sin),
				newHeight = (int)Math.floor(h*cos + w*sin);
		BufferedImage bImage = toBufferedImage(image);
		Graphics2D graphics2d = bImage.createGraphics();
		graphics2d.translate((newWidth - w) /2, (newHeight - h)/2);
		graphics2d.rotate(Math.toRadians(angle),w/2,h/2);
		graphics2d.drawRenderedImage(toBufferedImage(bImage), null);
		graphics2d.dispose();
		
		this.editImage = bufferedToImage(bImage);
		
		return;
	}
	@Override
	public void flip() {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -editImage.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter((BufferedImage) editImage, null);
		
		return;
	}
	@Override
	public void changeBrightness(int value) {
		float brightFactor = value;
		BufferedImage image = toBufferedImage(this.editImage);
		RescaleOp op = new RescaleOp(brightFactor, 0, null);
		image = op.filter(image, image);
		
		this.editImage = bufferedToImage(image);
	}
	
	@Override
	public void changeContrast(int value) {
		float contrastFactor = value;
		BufferedImage image = toBufferedImage(this.editImage);
		RescaleOp op = new RescaleOp(1.0f, contrastFactor, null);
		image = op.filter(image, image);
		
		this.editImage = bufferedToImage(image);
		
	}
	
	@Override
	public void save() {
		this.image = editImage;
		
	}
	
	
	private BufferedImage toBufferedImage(Image image) {
		if(image instanceof BufferedImage) {
			return (BufferedImage)image;
		}
		
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D bGraphics2d = bufferedImage.createGraphics();
		bGraphics2d.drawImage(image, 0, 0, null);
		bGraphics2d.dispose();
		
		return bufferedImage;
		
	}
	
	private Image bufferedToImage(BufferedImage bufferedImage) {
		Image image = (Image) bufferedImage;
		return image;
	}
	
	

}
