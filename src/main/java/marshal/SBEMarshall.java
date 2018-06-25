package marshal;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.persistence.*;

import java.nio.ByteBuffer;

import static sbe.persistence.MessageHeaderDecoder.ENCODED_LENGTH;

public class SBEMarshall implements Marshall {

    private static final TradeDecoder tradeDecoder = new TradeDecoder();
    private static final TradeEncoder tradeEncoder = new TradeEncoder();
    private static final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
    private static final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();

    @Override
    public byte[] write() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        TradeEncoder messageSize = writeTrade(buffer);
        byte[] data = new byte[messageSize.limit()];
        messageSize.buffer().getBytes(0, data);
        readTrade(buffer);
        return data;
    }

    private static void readTrade(ByteBuffer buffer) {

        MutableDirectBuffer directBuffer = new UnsafeBuffer(buffer);

        messageHeaderDecoder.wrap(directBuffer, 0);
        tradeDecoder.wrap(directBuffer, ENCODED_LENGTH,
                messageHeaderDecoder.blockLength(),
                messageHeaderDecoder.version());
        System.out.println(tradeDecoder);

    }

    private static TradeEncoder writeTrade(ByteBuffer buffer) {
        MutableDirectBuffer mutableBuffer = new UnsafeBuffer(buffer);
        tradeEncoder.wrapAndApplyHeader(mutableBuffer, 0, messageHeaderEncoder);
        tradeEncoder
                .tradeId(1)
                .customerId(999)
                .tradeType(TradeType.Buy)
                .qty(100)
                .symbol("GOOG")
                .exchange("NYSE");

        return tradeEncoder;
    }

    @Override
    public int read() {
        return 0;
    }
}
