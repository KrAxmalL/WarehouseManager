package org.example.Models;

import com.google.common.primitives.UnsignedLong;
import lombok.Data;
import org.example.Utils.CRC;

import java.nio.ByteBuffer;

@Data
public class Packet {

    public static final int META_PART_LENGTH = Byte.BYTES + Byte.BYTES
            + Long.BYTES + Integer.BYTES;

    public static final Byte bMagic = 0x13;
    private Byte bSrc;
    private UnsignedLong bPktId;
    private Integer wLen;
    private Short wCrc16Meta;
    private Message msg;
    private Short wCrc16Message;

    public Packet(Byte bSrc, UnsignedLong bPktId, Message msg) {
        this.bSrc = bSrc;
        this.bPktId = bPktId;
        this.msg = msg;
    }

    public Packet(byte[] encodedPacket) throws Exception {
        //additional buffer needed to check CRC
        byte[] additional = null;
        ByteBuffer additionalCRCBuff = ByteBuffer.wrap(encodedPacket);

        //main buffer
        ByteBuffer buff = ByteBuffer.wrap(encodedPacket);

        //checking magic byte
        byte decodedBMagic = buff.get();
        if(decodedBMagic != bMagic) {
            throw new Exception("Wrong bMagic byte!");
        }

        //no additional checks required for these values
        bSrc = buff.get();
        bPktId = UnsignedLong.fromLongBits(buff.getLong());
        wLen = buff.getInt();

        wCrc16Meta = buff.getShort();
        //checking first CRC using additional buffer
        additional = new byte[META_PART_LENGTH];
        additionalCRCBuff.get(additional);
        short wCrc16MetaRecalculated = (short)CRC.calculateCRC(additional);
        if(wCrc16Meta != wCrc16MetaRecalculated) {
            throw new Exception("Meta information CRC is incorrect! Package was injured during transportation!");
        }

        //getting and decoding message
        msg = new Message();
        msg.setCType(buff.getInt());
        msg.setBUserId(buff.getInt());
        byte[] messageAsBytes = new byte[wLen - msg.TYPE_AND_ID_LENGTH]; //wLen - 8
        buff.get(messageAsBytes);
        msg.setMessage(new String(messageAsBytes));
        msg.decode();

        wCrc16Message = buff.getShort();
        //checking second CRC using additional buffer
        additional = new byte[wLen];
        additionalCRCBuff.getShort(); //skip 2 bytes(first CRC)
        additionalCRCBuff.get(additional);
        short wCrc16MessageRecalculated = (short)CRC.calculateCRC(additional);
        if(wCrc16Message != wCrc16MessageRecalculated) {
            throw new Exception("Message CRC is incorrect! Package was injured during transportation!");
        }
    }

    public byte[] toByteRepresentation() {
        Message currMsg = getMsg();
        currMsg.encode();

        wLen = currMsg.getFullLength(); //encoded string has other length than standard

        byte[] metaPart = ByteBuffer.allocate(META_PART_LENGTH).put(bMagic).put(bSrc)
                          .putLong(bPktId.longValue()).putInt(wLen).array();

        wCrc16Meta = (short) CRC.calculateCRC(metaPart);

        int messagePartLength = currMsg.getFullLength();
        byte[] messagePart = ByteBuffer.allocate(messagePartLength).put(currMsg.toByteRepresentation())
                             .array();

        wCrc16Message = (short) CRC.calculateCRC(messagePart);

        int fullPacketLength = META_PART_LENGTH + wCrc16Meta.BYTES + messagePartLength + wCrc16Message.BYTES;
        return ByteBuffer.allocate(fullPacketLength).put(metaPart).putShort(wCrc16Meta)
                .put(messagePart).putShort(wCrc16Message).array();
    }

}
