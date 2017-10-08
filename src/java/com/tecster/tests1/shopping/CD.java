/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecster.tests1.shopping;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author javier
 */
public class CD implements Serializable
{

  public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
  private String sampleProperty;
  private PropertyChangeSupport propertySupport;

  public CD()
  {
    propertySupport = new PropertyChangeSupport(this);

    album = "";
    artist = "";
    country = "";
    price = 0;
    quantity = 0;

  }

  public String getSampleProperty()
  {
    return sampleProperty;
  }

  public void setSampleProperty(String value)
  {
    String oldValue = sampleProperty;
    sampleProperty = value;
    propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    propertySupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertySupport.removePropertyChangeListener(listener);
  }
  String album;
  String artist;
  String country;
  float price;
  int quantity;

  public void setAlbum(String title)
  {
    album = title;
  }

  public String getAlbum()
  {
    return album;
  }

  public void setArtist(String group)
  {
    artist = group;
  }

  public String getArtist()
  {
    return artist;
  }

  public void setCountry(String cty)
  {
    country = cty;
  }

  public String getCountry()
  {
    return country;
  }

  public void setPrice(float p)
  {
    price = p;
  }

  public float getPrice()
  {
    return price;
  }

  public void setQuantity(int q)
  {
    quantity = q;
  }

  public int getQuantity()
  {
    return quantity;
  }
}
