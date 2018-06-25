package message;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TradeE implements Externalizable {

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeLong(tradeId);
        out.writeLong(customerId);
        out.writeLong(qty);
        out.writeByte(tradeType.ordinal());
        out.writeUTF(symbol);
        out.writeUTF(exchange);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.tradeId = in.readLong();
        this.customerId = in.readLong();
        this.qty = in.readLong();
        int type = in.read();
        this.tradeType = TradeE.TradeTypeE.values()[type];
        this.symbol = in.readUTF();
        this.exchange = in.readUTF();
    }

    public enum TradeTypeE {
        Buy,
        Sell
    }

    public long tradeId;
    public long customerId;
    public long qty;
    public TradeE.TradeTypeE tradeType;
    public String symbol;
    public String exchange;
}
