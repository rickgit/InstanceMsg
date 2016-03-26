package edu.ptu.instancemsg.smack;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 * Created by WangAnshu on 16/3/26.
 */
public class SmackUtils {

    private XMPPTCPConnection conn;

    public void connect() {
        try {
            XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
            builder.setPort(5222);//5223 by default Client SSL Port in Openfire
            builder.setServiceName("169.254.229.218");
            builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setCompressionEnabled(false);
//            builder.setUsernameAndPassword("rick", "rick093715");
//            builder.setResource("android");


            conn = new XMPPTCPConnection(builder.build());
            conn.connect();

            conn.login("rick", "rick093715", "android");

            ChatManager chatManager = ChatManager.getInstanceFor(conn);
            chatManager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally) {
                    System.out.println("send --");
                }
            });
            Chat chat = chatManager.createChat("admin", new ChatMessageListener() {
                @Override
                public void processMessage(Chat chat, Message message) {
                    System.out.println("Received message: " + message.getBody());
                }
            });

            chat.sendMessage("你好");
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.disconnect();

    }

    public static void main(String[] args) {
        new SmackUtils().connect();
    }
}
