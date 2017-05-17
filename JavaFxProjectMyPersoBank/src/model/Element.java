package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Element 
{
	private final StringProperty name;
	
	public Element()
	{
		this(null);
	}
	
	public Element(String name) 
	{
		this.name = new SimpleStringProperty(name);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
}
