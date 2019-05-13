package pl.edu.agh.mwo;

import java.awt.List;
import java.util.LinkedList;

public class Trip {
	private String name;
	private String description;
	private LinkedList<Photo> photos;
	private double price;
	
	public Trip(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		photos = new LinkedList<>();
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addPhoto(Photo photo) {
		photos.add(photo);
	}
	
	public LinkedList<Photo> getPhotos() {
		return this.photos;
	}
}
