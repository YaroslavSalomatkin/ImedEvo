package imedevo.model;

import org.springframework.stereotype.Component;

@Component
public class Geoposition {

  private Double lat;
  private Double lng;
  private String placeId;
  private String address;

  public Geoposition() {
  }

  public Geoposition(Double lat, Double lng, String placeId, String address) {
    this.lat = lat;
    this.lng = lng;
    this.placeId = placeId;
    this.address = address;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
