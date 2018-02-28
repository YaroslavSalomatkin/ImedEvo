package imedevo.model.Geo;

import org.springframework.stereotype.Component;

@Component
public class Geometry {

  private BoundsRegion bounds;
  private Coordinate location;
  private String location_type;
  private BoundsRegion viewport;

  public Geometry() {
  }

  public Geometry(BoundsRegion bounds, Coordinate location, String location_type,
      BoundsRegion viewport) {
    this.bounds = bounds;
    this.location = location;
    this.location_type = location_type;
    this.viewport = viewport;
  }

  public BoundsRegion getBounds() {
    return bounds;
  }

  public void setBounds(BoundsRegion bounds) {
    this.bounds = bounds;
  }

  public Coordinate getLocation() {
    return location;
  }

  public void setLocation(Coordinate location) {
    this.location = location;
  }

  public String getLocation_type() {
    return location_type;
  }

  public void setLocation_type(String location_type) {
    this.location_type = location_type;
  }

  public BoundsRegion getViewport() {
    return viewport;
  }

  public void setViewport(BoundsRegion viewport) {
    this.viewport = viewport;
  }
}
