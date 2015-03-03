/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labs.models;

import com.labs.interfaces.IProcessing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 *
 * @author iura
 */
public class ImageModel implements IProcessing {

    private String name;
    private String locationString;
    private Image image;
    private Image editImage;

    public ImageModel(Image image) {
        super();
        this.image = image;
        this.editImage = clone(image);
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
    
    public Image getEditImage() {
        return editImage;
    }

    public void setEditImage(Image image) {
        this.editImage = image;
    }

    @Override
    public void rotate90DegreeLeft() {
        BufferedImage inputImage = toBufferedImage(editImage);
        
	int width = inputImage.getWidth();
	int height = inputImage.getHeight();
	BufferedImage returnImage = new BufferedImage( height, width , inputImage.getType());
        //We have to change the width and height because when you rotate the image by 90 degree
	for( int x = 0; x < width; x++ ) {
		for( int y = 0; y < height; y++ ) {
			returnImage.setRGB(y, width - x - 1, inputImage.getRGB( x, y  ));
		}
	}
        this.editImage = bufferedToImage(returnImage);
        return;
    }

    public void rotate90DegreeRight() {
        BufferedImage inputImage = toBufferedImage(editImage);
        
	int width = inputImage.getWidth();
	int height = inputImage.getHeight();
	BufferedImage returnImage = new BufferedImage( height, width , inputImage.getType());
        //We have to change the width and height because when you rotate the image by 90 degree
	for( int x = 0; x < width; x++ ) {
		for( int y = 0; y < height; y++ ) {
			returnImage.setRGB(height - y -1, x, inputImage.getRGB( x, y ));
		}
	}
        this.editImage = bufferedToImage(returnImage);
    }
    
    

    @Override

    public void flip() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-editImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        this.editImage = op.filter((BufferedImage) editImage, null);
        return;
    }

    @Override

    public void changeBrightness(int value) {
        //for each pixed add a value for red,green and blue
        float brightFactor = value*5 +1;
        this.editImage = clone(image);
        BufferedImage imageBuff = toBufferedImage(editImage);
        RescaleOp op = new RescaleOp(1.0f, brightFactor, null);
        editImage = op.filter(imageBuff, imageBuff);
        
    }

    @Override

    public void changeContrast(int value) {
        float contrastFactor = ((float)value +50) / 50;
        this.editImage = clone(image);
        BufferedImage imageBuff = toBufferedImage(editImage);
        RescaleOp op = new RescaleOp(contrastFactor, 1.0f, null);
        editImage = op.filter(imageBuff, imageBuff);
    }

    @Override

    public void save() {
        this.image = editImage;
    }

    private BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
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
    
    private Image clone(Image image) {
        BufferedImage source = toBufferedImage(image);
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return bufferedToImage(b);
    }
}
