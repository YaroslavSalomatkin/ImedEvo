package imedevo.httpStatuses;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BlogStatus {

    ADD_OK(607, "Add new article OK"),
    NOT_FOUND(608, "Blog not found"),
    EDIT_BLOG_SUCCESS(609, "Edit success"),
    REGISTRATION_ERROR_EMPTY_PICTURE(610, "Empty picture"),
    REGISTRATION_ERROR_EMPTY_FIRSTNAME(611, "Empty field first name"),
    REGISTRATION_ERROR_EMPTY_LASTNAME(612, "Empty field last name"),
    REGISTRATION_ERROR_EMPTY_ARTICLENAME(613, "Empty article name"),
    EDIT_BLOG_ERROR(614, "EDIT BLOG ERROR"),
    DELETE_SUCCESS(615, "Delete blog success"),
    DELETE_BLOG_ERROR(616, "Delete blog error")
;

    private int code;
    private String message;


    BlogStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}


