package org.triemarant.slave.exchange.packet.data;

import org.triemarant.api.api.exchange.Longitude;

public class CraftLongitude implements Longitude {

    private short degree;
    private short minute;
    private short second;

    public CraftLongitude(short degree, short minute, short second) {
        this.degree = degree;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public Short getDegree() {
        return degree;
    }

    @Override
    public Short getMinute() {
        return minute;
    }

    @Override
    public Short getSecond() {
        return second;
    }

}
