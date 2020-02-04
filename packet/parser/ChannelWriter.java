package org.triemarant.slave.exchange.packet.parser;

import com.google.common.primitives.Bytes;

import java.util.ArrayList;
import java.util.List;

public class ChannelWriter {

    List<Byte> bytes = new ArrayList<>();

    public void writeShort(Short value) {
        bytes.add((byte)(value & 0xff));
        bytes.add((byte)((value >> 8) & 0xff));
    }

    public void writeString(String value){
        this.writeInt(value.length());
        for (byte b : value.getBytes())
            bytes.add(b);
    }

    public void writeInt(int value){
        bytes.add((byte) (value >> 24));
        bytes.add((byte) (value >> 16));
        bytes.add((byte) (value >> 8));
        bytes.add((byte) (value));
    }

    public void writeArrayList(List<String> lists){
        this.writeInt(lists.size());
        for (String list : lists)
            this.writeString(list);
    }

    public byte[] getBytes() {
        return Bytes.toArray(bytes);
    }
}
