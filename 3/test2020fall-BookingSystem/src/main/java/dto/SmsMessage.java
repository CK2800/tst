package dto;

import java.util.Objects;

public class SmsMessage {
    /**
     * The recipient number including country code
     */
    private final String recipient;
    private final String message;
    private String reason = "";
    private SmsMessageStatus status = SmsMessageStatus.NONE;

    public SmsMessage(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public SmsMessageStatus getStatus() {
        return status;
    }

    public void setStatus(SmsMessageStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsMessage that = (SmsMessage) o;
        return Objects.equals(recipient, that.recipient) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, message);
    }
}
