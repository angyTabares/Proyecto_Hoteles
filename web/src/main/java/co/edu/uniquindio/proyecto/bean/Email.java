package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

public class Email implements Serializable {

    @Getter
    @Setter
    private static String correoPassword, correoComentario, correoRespuesta;


    public static String randomPassword() {
        String[] symbols = {"0", "1", "-", "*", "%", "$", "a", "b", "c"};
        int length = 10;
        Random random;
        String password = "123456789";
        try {
            random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int indexRandom = random.nextInt(symbols.length);
                sb.append(symbols[indexRandom]);
            }
            password = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }


    public static void sendEmailPassword(String usuario, String subject, String to, String from, String password) {

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("unilocal2021@gmail.com", "AngyDaniel2021");
            }


        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            correoPassword = correoPaswordUsuario(usuario, password);

            //adding text to message
            m.setContent(correoPassword, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailComentario(String usuarioPropietario, String subject, String to, String from, String usuarioComentario, String lugar, String comentario) {

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("unilocal2021@gmail.com", "AngyDaniel2021");
            }


        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            correoComentario = correoComentario(usuarioPropietario, usuarioComentario, lugar, comentario);

            //adding text to message
            m.setContent(correoComentario, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailRespuesta(String usuarioPropietario, String subject, String to, String from, String usuarioRespuesta, String lugar, String respuesta) {

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("unilocal2021@gmail.com", "AngyDaniel2021");
            }


        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            correoRespuesta = correoRespuesta(usuarioPropietario, usuarioRespuesta, lugar, respuesta);

            //adding text to message
            m.setContent(correoRespuesta, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String correoPaswordUsuario(String usuario, String password) {
        return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Email</title>\n" + "</head>\n" + "<body>\n" + "<h1>\n" + "    " + "<strong" + ">Unilocal</strong>\n" + "</h1>\n" + "\n" + "<h2>\n" + "    Hola, <strong>" + usuario + "!</strong>\n" + "</h2>\n" + "<div>\n" + "    <p>Has olvidado tu contraseña. <br>No te " + "preocupes puedes restablecer tu contraseña<br>\n" + "        ingresando la contraseña temporal que esta mas abajo la proxima vez que inicies sesion<br>\n" + "        recuerda " + "cambiarla en la pestaña Mi perfil</p>\n" + "\n" + "    <p>Contraseña temporal:" + password + "</p>\n" + "</div>\n" + "</body>\n" + "</html>";
    }

    public static String correoComentario(String usuarioPropietario, String usuarioComentario, String lugar, String comentario) {
        return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Comentarios</title>\n" + "</head>\n" + "<body>\n" + "<h1>\n" + "    " + "<strong>Unilocal</strong>\n" + "</h1>\n" + "\n" + "<h2>\n" + "    Hola, <strong>" + usuarioPropietario + "!</strong>\n" + "</h2>\n" + "<div>\n" + "    Tienes un nuevo comentario " + "en" + " uno de tus " + "lugares\n" + "    <p></p>\n" + "    <p>El usuario <strong>" + usuarioComentario + "</strong> ha comentado en <strong>" + lugar + "</strong></p>\n" + "\n" + "    " + "<p>" + comentario + "</p>\n" + "</div>\n" + "</body>\n" + "</html>";
    }

    public static String correoRespuesta(String usuarioPropietario, String usuarioRespuesta, String lugar, String respuesta) {
        return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Respuesta</title>\n" + "</head>\n" + "<body>\n" + "<h1>\n" + "    " + "<strong>Unilocal</strong>\n" + "</h1>\n" + "\n" + "<h2>\n" + "    Hola, <strong>" + usuarioRespuesta + "!</strong>\n" + "</h2>\n" + "<div>\n" + "    Tienes una nueva respuesta a " + "uno de tus " + "comentarios\n" + "    <p></p>\n" + "    <p>El Dueño <strong>" + usuarioPropietario + "</strong> del lugar <strong>" + lugar + "</strong> ha respondido tu " + "comentario</p>\n" + "\n" + "    <p>" + respuesta + "</p>\n" + "</div" + ">\n" + "</body>\n" + "</html>";
    }

}
