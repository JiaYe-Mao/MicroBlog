package model;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class QQMailCarrier implements MailCarrier {
    private Properties props;

    public QQMailCarrier(Properties props){
        this.props = props;
    }

    @Override
    public void sendTo(Account account, String subject, String content) {
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(props.getProperty("com.mao.username"),
                                    props.getProperty("com.mao.password"));
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("cc.mao.address")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(account.getEmail()));
            message.setSubject(subject);
            message.setSentDate(new Date());

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html;charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
