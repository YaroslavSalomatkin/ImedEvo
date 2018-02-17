package imedevo.httpStatuses;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BlogStatus {
    NOT_FOUND(808, "Blog not found"),
    EDIT_BLOG_SUCCESS(809, " Edit success"),
    REGISTRATION_ERROR_EMPTY_PICTURE(810, " Empty picture"),
    REGISTRATION_ERROR_EMPTY_FIRSTNAME(811, "Empty field first name"),
    REGISTRATION_ERROR_EMPTY_LASTNAME(812, "Empty field last name"),
    REGISTRATION_ERROR_EMPTY_ARTICLENAME(813, "Empty article name"),
    EDIT_BLOG_ERROR(814," EDIT BLOG ERROR")
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


