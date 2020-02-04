package org.triemarant.slave.exchange.packet.parser;

public interface Packet {

    void read(ChannelReader channelRead);
    void write(ChannelWriter channelWrite);

}
