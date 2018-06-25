package marshal;

import message.TradeE;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ExternalizableMarshal implements Marshall {
    @Override
    public byte[] write() {

        TradeE exJavaTrade = new TradeE();
        exJavaTrade.customerId = 999;
        exJavaTrade.tradeId = 1;
        exJavaTrade.tradeType = TradeE.TradeTypeE.Buy;
        exJavaTrade.qty = 100;
        exJavaTrade.symbol = "GOOG";
        exJavaTrade.exchange = "NYSE";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
            objectOutputStream.writeObject(exJavaTrade);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int read() {
        return 0;
    }


}
