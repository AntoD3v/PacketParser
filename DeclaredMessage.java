package org.triemarant.slave.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.triemarant.api.api.exchange.Connection;
import org.triemarant.slave.exception.ConnectionException;
import org.triemarant.slave.exception.ShutdownException;
import org.triemarant.slave.exchange.packet.NetworkMessageConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DeclaredMessage {

    private final Connection connectionInformation;
    private Channel channel;

    public DeclaredMessage(Connection connection) {
        this.connectionInformation = connection;
    }

    public void declareChannel() throws IOException {
        this.channel.basicConsume("general", new NetworkMessageConsumer(this.channel));
    }

    public Channel getChannel() {
        return channel;
    }

    public void stop() throws ShutdownException {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ShutdownException();
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new ShutdownException();
        }
    }

    public void connect() throws ConnectionException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(this.connectionInformation.getHost());
        connectionFactory.setUsername(this.connectionInformation.getUser());
        connectionFactory.setPassword(this.connectionInformation.getPass());
        try(com.rabbitmq.client.Connection connection = connectionFactory.newConnection()){
            this.channel = connection.createChannel();
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new ConnectionException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException();
        }
    }
}
