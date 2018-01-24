package imedevo.httpStatuses;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DocStatus {

    // registration status
    REGISTRATION_OK(800, "Registration success. Check your e-mail."),
    REGISTRATION_ERROR_INCORRECT_PASSWORD(801, "You entered an incorrect password"),
    REGISTRATION_ERROR_DUPLICATE_USERS(802, "User with such e-mail already exist"),
    REGISTRATION_ERROR_EMPTY_EMAIL(803, "Empty e-mail"),
    REGISTRATION_ERROR_INCORRECT_EMAIL(804, "You enter incorrect e-mail"),
    REGISTRATION_ERROR_EMPTY_PHONE(805, "Error. Empty phone."),
    REGISTRATION_ERROR_EMPTY_BIRTHADAY(806, "Error. Empty Birthday."),
    REGISTRATION_ERROR_EMPTY_NAME(807, "Error. Empty name."),
    REGISTRATION_ERROR_DUPLICATE_EMAILS(815, "Clinic with such e-mail already exist"),



    // login status
    LOGIN_OK(811, "Login success."),
    LOGIN_USER_NOT_FOUND(812, "User with your credentials not found."),
    LOGIN_BAD_LOGIN(813, "Login success."),
    LOGIN_BAD_PASSWORD(814, "Login success."),


    //logout status
    LOGOUT_OK(820, "Login success."),



    //edit profile status
    EDIT_PROFILE_SUCCESS(830, "Your profile changed successfully."),
    EDIT_PROFILE_ERROR(831, "An error occurred while editing your profile."),

    //comments
    ADD_COMMENT_SUCCESS(841, "Your comment was added."),
    EDIT_COMMENT_SUCCESS(843, "Your comment was edited successfully."),

     //see time
    SEE_TIME_SUCCESS(850, "You was apply your patient time."),
    SEE_TIME_REFUSE(851, "You was refuse your patient time."),

    // delete profile
    DELETE_PROFILE_SUCCESS(860, "Your profile was deleted.")
    ;

    private int code;
    private String message;


    DocStatus(int code, String message) {
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
