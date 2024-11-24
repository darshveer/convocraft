import com.convocraft.chatroom.ActiveMQConnection;

public class ActiveMQConnectionManager{
    
    createChatroomTopic(String topicName) {
        ActiveMQConnection newConnection = new ActiveMQConnection(topicName, adminIp, adminPort);
    }

    joinChatroomTopic(String topicName, String hostIp, String hostPort) {
        ActiveMQConnection newConnection = new ActiveMQConnection(topicName, adminIp, adminPort);

    }
}