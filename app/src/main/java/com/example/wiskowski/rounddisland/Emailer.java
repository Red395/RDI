package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer extends AsyncTask<Void, Void, Void> {
    // USE BELOW CODE TO CALL
    /*Emailer run = new Emailer(mContext, EmailConfig.EMAIL, "Test", "message");
    if (run.getConnected()) {run.execute();}*/

    private Context lContext;
    private Session lsession;

    private String email;

    private String subject;
    private String message;

    private ProgressDialog progressDialog; // loading bar

    private boolean connectedWifi = false;

    // should take context from the page its called on, should take the generated key
    public Emailer(Context page, String email, String subject, String key) {
        this.lContext = page;
        this.email = email;
        this.subject = subject;
        this.message = key;

        checkConnection();
    }

    public boolean getConnected() {return connectedWifi;}

    public void checkConnection() {
        ConnectivityManager cm = (ConnectivityManager) lContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        try {
            boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI; // checks to see if the user is connected to wifi

            if (activeNetwork.isConnected() && isWifi)
                connectedWifi = true; // sets a variable to true if the device is connected to a working wifi signal
        } catch (NullPointerException e) {
            Toast.makeText(lContext, "No Connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        // shows spinning circle while loading
        super.onPreExecute();
        progressDialog = ProgressDialog.show(lContext, "Sending Message", "Please Wait", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss(); // closes progress dialog when the email is sent
        Toast.makeText(lContext, "Email Sent", Toast.LENGTH_LONG).show(); // shows that the email has been sent
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties p = new Properties();

        // Sets the properties to work with gmail
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");

        lsession = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailConfig.EMAIL, EmailConfig.EPASS);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(lsession);
            mm.setFrom(new InternetAddress(EmailConfig.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);

            // Sends the email
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
