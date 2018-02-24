package imedevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imedevo.model.Geoposition;
import imedevo.service.GeocodingService;

@RestController
@RequestMapping("/geocoding")
public class GeocodingController {

  @Autowired
  private GeocodingService geocodingService;

  @GetMapping("/toposition")
  public Geoposition getGeopositionByAddress(@RequestParam("address") String address) {
    return geocodingService.getGeopositionByAddress(address);
  }

  @GetMapping("/toaddress")
  public String getAddressByCoordinates(@RequestParam("lat") double lat,
      @RequestParam("lng") double lng) {
    return geocodingService.getAddressByCoordinates(lat, lng);
  }

}
