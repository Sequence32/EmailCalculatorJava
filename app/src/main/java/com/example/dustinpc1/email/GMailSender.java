package com.example.dustinpc1.email;


import android.os.AsyncTask;

import java.util.Properties;
import javax.mail.Authenticator;import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailSender extends AsyncTask<Void, Void, Void>{



    @Override
    protected Void doInBackground(Void... Void){
        try
        {
            sendMail();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected void onProgressUpdate() {
        //called when the background task makes any progress
    }

    protected void onPreExecute() {
        //called before doInBackground() is started
    }
    protected void onPostExecute() {
        //called after doInBackground() has finished
    }

    public GMailSender() {



    }

   public void sendMail() {
       {
          String host = "stmp.gmail.com";

           String from = "dkbcraft1@gmail.com";
           String  pass = "Dustin5624094";
           String  to = "dkbcraft4@gmail.com";
           String  sub = "First Android mail app";
           String  mess = "I totally hate you";
         Properties  props = System.getProperties();


           try {

               props.put("mail.smtp.auth", "true");
               props.put("mail.smtp.starttls.enable", "true");
               props.put("mail.smtp.host", host);

               Authenticator auth = new Authenticator() {

                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication("dkbcraft1@gmail.com", "Dustin5624094");
                   }
               };
               Session session = Session.getInstance(props, auth);
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
               message.setSubject(sub);
               message.setText(mess);
               Transport.send(message);


           }catch(Exception e)
           {
               System.out.println(e.getMessage());
           }
       }

   }
}