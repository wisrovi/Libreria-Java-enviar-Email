/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SenInt2.fcv.org.EmailService.linkedin.com.in.wisrovi.rodriguez.EmailService.Lib.Email;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author williamrodriguez
 */
public class UtilCorreo implements Serializable{
    
    public static void Exception(Exception ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Retorna String - Properties.
     *
     * @param properties
     * @param llave
     * @return
     * @throws java.lang.Exception
     */// <editor-fold defaultstate="collapsed" desc="getProperties">
    public static String getProperties(Properties properties, String llave) throws Exception {
        String key;
        if (properties != null) {
            for (Enumeration ruta2 = properties.keys(); ruta2.hasMoreElements();) {
                key = (String) ruta2.nextElement();
                if (key.equals(llave)) {
                    return properties.getProperty(key);
                }
            }

        }
        return null;
    }// </editor-fold>
    
    /**
     *
     * @param nombre
     * @return
     * @throws java.lang.Exception
     */// <editor-fold defaultstate="collapsed" desc="getProperties">
    public static Properties getProperties(String nombre) throws Exception {
        Properties properties = new Properties();
        InputStream fis = UtilCorreo.class.getResourceAsStream("/SenInt2/fcv/org/Email/" + nombre);
        if (fis != null) {
            properties.load(fis);
            fis.close();
        }
        return properties;
    }// </editor-fold>

   public static void getCorreoElectronico(CorreoDto correoDto) throws Exception {
        Session session = Session.getDefaultInstance(correoDto.getSessionInstance());
        MimeMessage message = new MimeMessage(session);
        message.setFrom(correoDto.getFrom());
        if (correoDto.getTo().toArray().length > 0) {
            message.addRecipients(Message.RecipientType.TO, Arrays.copyOf(correoDto.getTo().toArray(), correoDto.getTo().toArray().length, Address[].class));
        }
        if (correoDto.getCc().toArray().length > 0) {
            message.addRecipients(Message.RecipientType.CC, (Address[]) correoDto.getCc().toArray());
        }
        if (correoDto.getBcc().toArray().length > 0) {
            message.addRecipients(Message.RecipientType.BCC, (Address[]) correoDto.getBcc().toArray());
        }
        message.setSubject(correoDto.getMessageSubject());
        message.setText(correoDto.getMessageText(), correoDto.getMessageCharset(), correoDto.getMessageSubtype());
        Transport transport = session.getTransport(correoDto.getSessionTransport());
        transport.connect(correoDto.getTransportUser(), correoDto.getTransportPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    } 
    
    public static void CorreoPersonalizado(String destinatario, String asunto, String Titulo, String NumeroAlerta,  String msgInicial, String lineaNegrilla1, String lineaNegrilla2, String lineaNegrilla3) throws AddressException, java.lang.Exception{
        CorreoDto correoDto = new CorreoDto();
        correoDto.setFrom(new InternetAddress(UtilCorreo.getProperties(UtilCorreo.getProperties("Correo.properties"), "transportUser")));    
        correoDto.setMessageSubject(asunto);

        List<Address> destinatarios = new ArrayList<>();
        destinatarios.add(new InternetAddress(destinatario));
        correoDto.setTo(destinatarios);

        String plantilla = "<html style=\"border-top: 20px solid rgb(102, 102, 102);\">\n" +
                "<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
                "\n\n\n\n\n<title>Dispositivo en alerta</title>\n" +
                "\n\n\n\n</head>\n<body bgcolor=\"#F4F6F7\" style=\"font-family: arial, sans-serif; font-size: 100%; line-height: 160%; background-color: #F4F6F7; margin: 0; padding: 0; border: 0;\">        \n" +
                " <table bgcolor=\"#F4F6F7\" style=\"width: 100%; background-color: #F4F6F7; font-size: 14px;\" align=\"center\" cellpadding=\"20\" cellspacing=\"0\" border=\"0\">             \n" +
                " <tr>\n<td style=\"padding-top: 0px;\">\n<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 100%; max-width: 700px;\">                         \n" +
                " <tr>\n<td><img src=\"http://www.fcv.org/site/images/FCV2.JPG\" alt=\"FCV\" style=\"padding: 6px 20px; width: 114px; background-color: rgb(255, 255, 255);margin-top: -20px;\" />\n" +
                " </td>\n</tr>\n</table>\n<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\" style=\"width: 100%; max-width: 700px; background-color: #ffffff; -webkit-border-radius: 4px; border-radius: 4px;\">                         \n" +
                " <tr>\n<td>\n<div style=\"position: relative; padding: 25px;\">                                     \n" +
                " <h1 style=\"font-size: 175%; line-height: 125%; margin-top: 0; margin-bottom: 20px; margin-top: -20px;\">" + Titulo + "</h1>                                     \n" +
                " \n\n\n\n<p style=\"margin-bottom: 20px;\">                                         " + msgInicial + " 	\n" +
                "\n\n\n\n\n\n<br>\n</br> 										" + NumeroAlerta +"\n" +
                "\n\n\n\n\n\n\n\n\n</p>\n" +
                " <h2 style=\"font-size: 125%; line-height: 125%; margin-top: 0; margin-bottom: 20px;\">" + lineaNegrilla1 +"</h2>\n" +
                " <h2 style=\"font-size: 125%; line-height: 125%; margin-top: 0; margin-bottom: 20px;\">" + lineaNegrilla2 + "</h2>\n" +
                " <h2 style=\"font-size: 125%; line-height: 125%; margin-top: 0; margin-bottom: 20px;\">" + lineaNegrilla3 +"</h2>\n" +
                " </div>\n<div>\n<a href=\"http://www.fcv.org\" style=\"display: block; background-color: #104A6A; color: #ffffff; font-weight: bold; padding: 20px; text-decoration: none; text-align: center;-webkit-border-bottom-right-radius: 4px; -webkit-border-bottom-left-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px;\"><span style=\"color: #ffffff;\">2016 &copy; Fundaci&oacute;n Cardiovascular de Colombia - Todos los derechos reservados</span>                                     \n" +
                " </a>\n</div>\n</td>\n</tr>\n</table>\n<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 100%; text-align: center; font-size: 80%; padding-top: 20px; color: #bdc3c7; max-width: 800px; line-height: normal;\">\n" +
                " <tr>\n<td>\n<strong>AVISO LEGAL:</strong> La Privacidad y la Confidencialidad de la informaci&oacute;n aqu&iacute; suministrada contienen datos personales los cuales est&aacute;n protegidos por la Constituci&oacute;n Nacional art?lo 15, Ley 23 de 1981 art&iacute;culo 34, Resoluci&oacute;n 1995 de 1999 art&iacute;culo 14, Ley 1581 de 2012 y el decreto 1377 de 2013. Esta informaci&oacute;n se suministra por previa autorizaci&oacute;n de su titular por lo que se advierte las consecuencias jur&iacute;dicas al uso y/o tratamiento diferente que se le d&eacute;.  Este mensaje propiene de un proyecto creado por:  wisrovi.rodriguez @ gmail.com. Al recibir la informaci&oacute;n adquiere la obligaci&oacute;n de protegerla, guardar y mantener el secreto de los datos de car&aacute;cter personal que su titular ha autorizado entregar. Est&aacute; prohibida la retenci&oacute;n, grabaci&oacute;n, utilizaci&oacute;n, aprovechamiento o divulgaci&oacute;n con cualquier prop&oacute;sito. Si por error recibe este mensaje, por favor destruya su contenido y avise a su remitente.                             \n" +
                " </td>\n</tr>\n</table>\n</td>\n</tr>\n</table>     \n</body> \n</html>";
        correoDto.setMessageText(plantilla);                              
        UtilCorreo.getCorreoElectronico(correoDto);
    }
    
    public static String LeerParametroCorreo(String parametro) throws java.lang.Exception{
        String properties = getProperties(getProperties("Correo.properties"),parametro);
        return properties;
    }
    
    
}
