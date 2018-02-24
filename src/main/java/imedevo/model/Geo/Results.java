package imedevo.model.Geo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Results {

  private List<AddressComponent> address_components;
  private String formatted_address;
  private Geometry geometry;
  private Boolean partial_match;
  private String place_id;
  private List<String> types;

  public Results() {
  }

  public Results(List<AddressComponent> address_components, String formatted_address,
      Geometry geometry, Boolean partial_match, String place_id,
      List<String> types) {
    this.address_components = address_components;
    this.formatted_address = formatted_address;
    this.geometry = geometry;
    this.partial_match = partial_match;
    this.place_id = place_id;
    this.types = types;
  }

  public List<AddressComponent> getAddress_components() {
    return address_components;
  }

  public void setAddress_components(List<AddressComponent> address_components) {
    this.address_components = address_components;
  }

  public String getFormatted_address() {
    return formatted_address;
  }

  public void setFormatted_address(String formatted_address) {
    this.formatted_address = formatted_address;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public Boolean getPartial_match() {
    return partial_match;
  }

  public void setPartial_match(Boolean partial_match) {
    this.partial_match = partial_match;
  }

  public String getPlace_id() {
    return place_id;
  }

  public void setPlace_id(String place_id) {
    this.place_id = place_id;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

}
