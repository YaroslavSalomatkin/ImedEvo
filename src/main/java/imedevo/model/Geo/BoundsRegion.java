package imedevo.model.Geo;

import org.springframework.stereotype.Component;

@Component
public class BoundsRegion {

  private Coordinate northeast;
  private Coordinate southwest;

  public BoundsRegion() {
  }

  public BoundsRegion(Coordinate northeast, Coordinate southwest) {
    this.northeast = northeast;
    this.southwest = southwest;
  }

  public Coordinate getNortheast() {
    return northeast;
  }

  public void setNortheast(Coordinate northeast) {
    this.northeast = northeast;
  }

  public Coordinate getSouthwest() {
    return southwest;
  }

  public void setSouthwest(Coordinate southwest) {
    this.southwest = southwest;
  }
}
