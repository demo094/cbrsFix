
package com.dataart.citybikerentalservicespring.constants;

/**
 * Class used to contain and return a simple messages for the user about the events occurring in application.
 *
 * @author mkrasowski
 */
public enum UserMessage {
    NO_BIKE_BOUND(3, MessageType.ERROR, "Sorry, You have no bike bound to your account!"),
    BIKE_BOUND(4, MessageType.ERROR, "Sorry, You have a bike already bound to your account!"),
    INVALID_USER_CREDENTIALS(5, MessageType.ERROR, "Sorry, you have provided either invalid e-mail or password. Please try again."),
    REGISTRATION_FAIL(6, MessageType.ERROR, "Sorry, there was a problem with the registration. If you see this message several times, check your registration credentials and contact administration."),
    REGISTRATION_SUCCESS(7, MessageType.MESSAGE, "You are now registered. To be able to use the service, you have to activate your account using the link sent to you on the registration e-mail."),
    DUPLICATE_EMAIL(8, MessageType.ERROR, "E-mail is already in the database! Please try to register again."),
    SYSTEM_ERROR(9, MessageType.ERROR, "Sorry, some severe internal system error occurred, if you see this page several times, please contact administration."),
    USER_LOG_OUT(10, MessageType.MESSAGE, "You are now logged out."),
    NO_BIKE_FOUND(11, MessageType.ERROR, "Sorry, the bike you've requested is not existing or could be already rented by another user. Please try again on another slot."),
    INVALID_ACTIVATION_TOKEN(12, MessageType.ERROR, "Sorry, the link you used either contains invalid token or your token has expired."),
    EXPIRED_ACTIVATION_TOKEN(13, MessageType.ERROR, "Sorry, the activation token in the link has expired."),
    ACCOUNT_ACTIVATED(14, MessageType.MESSAGE, "Your account has been activated. You can now log in."),
    ACCOUNT_NOT_ACTIVATED(15, MessageType.ERROR, "Sorry, your account is not activated yet. Please use the link sent to you in registration e-mail."),
    BALANCE_UPDATE_SUCCESS(16, MessageType.MESSAGE, "Your payment was successful!"),
    USER_BALANCE_ERROR(17, MessageType.ERROR, "Sorry, You cannot rent a bike without sufficient money."),
    INVALID_CARD_CREDENTIALS(18, MessageType.ERROR, "Sorry, you've input invalid card credentials."),
    INVALID_AMOUNT_INPUT(19, MessageType.ERROR, "Sorry, you have provided invalid amount for payment."),
    TOKEN_RESENT(20, MessageType.MESSAGE, "The resending of activation link was successful. Please check your e-mail box."),
    ENTITY_BINDING(21, MessageType.ERROR, "It appears that you tried to delete something that is bound with another object."),
    RESET_PASSWORD_MAIL_SENT(22, MessageType.MESSAGE, "The instructions about password reset were sent to the e-mail address you provided."),
    USER_NOT_FOUND(23, MessageType.ERROR , "Sorry, there was no matching e-mail addres to one you provided."),
    PASSWORD_RESET_FAIL(24, MessageType.ERROR , "Sorry, you have provided too weak password or there is a mismatch between password and retyped password."),
    PASSWORD_RESET_SUCCESS(25, MessageType.MESSAGE, "Password reset successful! You can now log in."),
    STATION_NOT_FOUND(26, MessageType.ERROR, "Sorry, the station you requested was not found."),
    NO_PRICE_INTERVAL(27, MessageType.ERROR, "Sorry, the price interval you requested was not found."),
    SEVERE_SYSTEM_ERROR(28, MessageType.ERROR, "Sorry, some unexpected system error occurred. Please contact administration if you see this page again."),
    DOUBLE_NULL_PRICE_INTERVAL(29, MessageType.ERROR , "You cannot create second empty interval!"),
    DOUBLE_PRICE_INTERVAL(30, MessageType.ERROR, "You cannot create two price intervals with the same end!"),
    DOUBLE_PRICING(31, MessageType.ERROR, "Price is already in the set!"),
    PRICE_HIGHER_THAN_IN_NEXT(31, MessageType.ERROR, "Price cannot be higher than in the next price interval!");


    private final String message;
    private final int id;
    private final MessageType messageType;

    UserMessage(int id, MessageType messageType, String message) {
        this.message = message;
        this.id = id;
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public static UserMessage getById(int id) {
        for (UserMessage value : UserMessage.values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Id not found.");
    }
}
