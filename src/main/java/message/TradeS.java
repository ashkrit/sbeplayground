package message;

import java.io.Serializable;

public class TradeS implements Serializable {

     public enum TradeTypeE {
        Buy,
        Sell
    }

    public long tradeId;
    public long customerId;
    public long qty;
    public TradeS.TradeTypeE tradeType;
    public String symbol;
    public String exchange;
}
