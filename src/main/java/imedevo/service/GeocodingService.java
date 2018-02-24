package imedevo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import imedevo.model.Geo.ResponseResults;
import imedevo.model.Geoposition;

@Service
public class GeocodingService {

  @Value("${googlemaps.authentication.key}")
  private String authentificationKey;
  @Value("${googlemaps.apiUrl}")
  private String apiUrl;

  private String region = "Одесса";

  private RestTemplate restTemplate = new RestTemplate();

  public Geoposition getGeopositionByAddress(String address) {

    String url =
        apiUrl + "?language=ru&address=" + address + "+" + region + "&key=" + authentificationKey;

    ResponseResults response = restTemplate.getForObject(url, ResponseResults.class);

    return new Geoposition(
        response.getResults().get(0).getGeometry().getLocation().getLat(),
        response.getResults().get(0).getGeometry().getLocation().getLng(),
        response.getResults().get(0).getPlace_id(),
        response.getResults().get(0).getFormatted_address());
  }

  public String getAddressByCoordinates(double lat, double lng) {

    String url = apiUrl + "?language=ru&latlng=" + lat + "," + lng + "&key=" + authentificationKey;
    ResponseResults response = restTemplate.getForObject(url, ResponseResults.class);

    return response.getResults().get(0).getFormatted_address();
  }
}
