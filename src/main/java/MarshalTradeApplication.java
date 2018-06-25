import marshal.ExternalizableMarshal;
import marshal.Marshall;
import marshal.SBEMarshall;
import marshal.SerializableMarshal;

public class MarshalTradeApplication {


    public static void main(String... args) throws Exception {

        Marshall[] marshalls = {new SerializableMarshal(), new ExternalizableMarshal(), new SBEMarshall()};
        for (Marshall m : marshalls) {
            System.out.println(String.format("Type %s -> size %s", m.getClass(), m.write().length));
        }
    }

}
