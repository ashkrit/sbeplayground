package marshal;

import message.TradeS;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class SerializableMarshal implements Marshall {
    @Override
    public byte[] write() {
        TradeS oldJavaTrade = new TradeS();
        oldJavaTrade.customerId = 999; // 8
        oldJavaTrade.tradeId = 1; // 16
        oldJavaTrade.tradeType = TradeS.TradeTypeE.Buy; // 17
        oldJavaTrade.qty = 100; // 25
        oldJavaTrade.symbol = "GOOG"; // 2 + 4 = 31
        oldJavaTrade.exchange = "NYSE"; // 37

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
            objectOutputStream.writeObject(oldJavaTrade);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();

    }

    @Override
    public int read() {
        return 0;
    }

}
