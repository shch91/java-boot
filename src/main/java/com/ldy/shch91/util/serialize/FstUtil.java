package com.ldy.shch91.util.serialize;

import org.nustaq.serialization.FSTConfiguration;
 
/**
 * Created by xiongps on 2018/5/23.
 */
public class FstUtil {

    private static ThreadLocal<FSTConfiguration> confs = new ThreadLocal() {
        public FSTConfiguration initialValue() {
            return FSTConfiguration.createDefaultConfiguration();
        }
    };

    private static FSTConfiguration getFST() {
        return confs.get();
    }

    public static <T> byte[] serializer(T t) {
        return getFST().asByteArray(t);
    }


    public static <T> T deserializer(byte[] bytes) {
        return (T) getFST().asObject(bytes);
    }
}
 
