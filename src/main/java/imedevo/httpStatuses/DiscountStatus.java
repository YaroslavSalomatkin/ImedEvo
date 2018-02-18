package imedevo.httpStatuses;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DiscountStatus {
  NOT_FOUND(650, "Blog not found"),
  EDIT_DISCOUNT_SUCCESS(651, "Edit success"),
  ERROR_EMPTY_NAME(652, "Empty name"),
  ERROR_EMPTY_DESCRIPTION(653, "Empty description"),
  EDIT_DISCOUNT_ERROR(654, "Discount editing error"),
  ADD_DISCOUNT_OK(655, "Adding discount success");

  private int code;
  private String message;


  DiscountStatus(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
