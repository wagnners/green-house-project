/*
 UC007 Formulários de mensagem da página inicialo.
 https://www.google.com/settings/u/1/security/lesssecureapps
 Para este formulario funcionar deve ser acessado o link acima para ativar as lesssecureapps;
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.ConfiguracaoDAO;
import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Configuracao;
import com.sun.faces.config.WebConfiguration;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sila
 */
public class FormularioMensagemUC {
    
    private Configuracao configuracao;
    private ConfiguracaoDAO cdao;

    public FormularioMensagemUC() {
        cdao = FactoryDAO.getFactoryDAO().getConfiguracaoDAO();
        configuracao = cdao.listar().get(0);
    }
    

    public void enviarEmail(String assunto, String corpo, String nome, String emailOrigem) throws MessagingException {
        System.out.println("Email sending method");
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("greenhouseproject192@gmail.com", "greenhouse123");
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailOrigem));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(configuracao.getEmail().trim()));
        message.setSubject("[via web] " + assunto);
        message.setText("Remetente:\n" + nome + "\n\nE-mail:\n" + emailOrigem + "\n\nConteúdo: \n" + corpo);

        Transport.send(message);

        System.out.println("Done");

    }
}

