package com.example.SpringCURD.controller;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnection {

private Session session;

    public SSHConnection() {
        try {
            System.out.println("ssh called");
            JSch jsch = new JSch();

            String user = "ec2-user";
            String host = "ec2-15-206-163-75.ap-south-1.compute.amazonaws.com";
            int port = 3306;
            String privateKey = "C:/Users/NEURO EQUILIBRIUM/Downloads/My_KP1.pem";

            jsch.addIdentity(privateKey);
            //jsch.
            System.out.println("identity added ");

             session = jsch.getSession(user, host, port);
            System.out.println("session created.");

            // disabling StrictHostKeyChecking may help to make connection but makes it insecure
            // see http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
            //
            // java.util.Properties config = new java.util.Properties();
            // config.put("StrictHostKeyChecking", "no");
            // session.setConfig(config);

            session.connect();
            System.out.println("session connected.....");

            Channel channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
            System.out.println("shell channel connected....");

            /*ChannelSftp c = (ChannelSftp) channel;

            String fileName = "test.txt";
            c.put(fileName, "./in/");
            c.exit();
            System.out.println("done");*/
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void closeSSH ()
    {
        session.disconnect();
    }
}