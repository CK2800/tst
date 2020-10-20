package servicelayer.notifications;

import dto.SmsMessage;
import dto.SmsMessageStatus;

public class SmsServiceImpl implements SmsService {
    @Override
    public boolean sendSms(SmsMessage message) throws SmsServiceException {
        if(message.getRecipient() != null && !message.getRecipient().trim().equals("")) {
            message.setStatus(SmsMessageStatus.PENDING);
            //Send
            message.setStatus(SmsMessageStatus.SENT);
            return true;
        }
        else {
            message.setStatus(SmsMessageStatus.FAIL);
            //String reason = sent.reason();
            message.setReason("BURN!!");
        }
        return false;
    }
}
