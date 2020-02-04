package org.triemarant.slave.exchange.packet;

import org.triemarant.api.api.exchange.Latitude;
import org.triemarant.api.api.exchange.Longitude;
import org.triemarant.slave.exchange.packet.data.CraftLatitude;
import org.triemarant.slave.exchange.packet.data.CraftLongitude;
import org.triemarant.slave.exchange.packet.parser.ChannelReader;
import org.triemarant.slave.exchange.packet.parser.ChannelWriter;
import org.triemarant.slave.exchange.packet.parser.Packet;

public class LocationInPacket implements Packet {

    private Longitude longitude;
    private Latitude latitude;

    public LocationInPacket(Longitude longitude, Latitude latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationInPacket(){

    }

    @Override
    public void read(ChannelReader channelRead) {
        this.longitude = new CraftLongitude(
                channelRead.getShort(),
                channelRead.getShort(),
                channelRead.getShort()
        );
        this.latitude = new CraftLatitude(
                channelRead.getShort(),
                channelRead.getShort(),
                channelRead.getShort()
        );
    }

    @Override
    public void write(ChannelWriter channelWrite) {
        channelWrite.writeShort(this.longitude.getDegree());
        channelWrite.writeShort(this.longitude.getMinute());
        channelWrite.writeShort(this.longitude.getSecond());

        channelWrite.writeShort(this.latitude.getDegree());
        channelWrite.writeShort(this.latitude.getMinute());
        channelWrite.writeShort(this.latitude.getSecond());
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }
}
