package imedevo.model.Geo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseResults {

  private List<Results> results;
  private String status;

  public ResponseResults() {
  }

  public ResponseResults(List<Results> results, String status) {
    this.results = results;
    this.status = status;
  }

  public List<Results> getResults() {
    return results;
  }

  public void setResults(List<Results> results) {
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
