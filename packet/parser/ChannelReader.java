package org.triemarant.slave.exchange.packet.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ChannelReader {


    private Queue<Byte> nextQueues = new PriorityQueue<>();
    private byte[] data;

    public ChannelReader(byte[] data) {
        this.data = data;
        this.refill();
    }

    public int getInt(){
        byte[] r;
        getByteOn(r=new byte[4], 4);
        return r[0]<<24 | r[1]<<16 | r[2]<<8 | r[3];
    }

    public short getShort(){
        byte[] r;
        getByteOn(r=new byte[2], 2);
        return (short) (r[0]<<8 & 0xff| r[1] & 0xff);
    }

    public String getString(){
        int length = getInt();
        byte[] r;
        getByteOn(r=new byte[length], length);
        return new String(r);
    }

    public List<String> getArrayString(){
        int lengthArray = getInt();
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < lengthArray; i++)
            lists.add(getString());
        return lists;
    }

    private void getByteOn(byte[] bytes, int length){
        for (int i = 0; i < length; i++)
            bytes[i] = nextQueues.remove();
    }

    public void refill(){
        nextQueues.clear();
        for (byte b : data)
            nextQueues.add(b);
    }

}
