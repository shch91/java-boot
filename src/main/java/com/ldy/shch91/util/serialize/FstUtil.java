package com.ldy.shch91.util.serialize;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by xiongps on 2018/5/23.
 */
public class FstUtil implements RedisSerializer<Object> {

    private static ThreadLocal<FSTConfiguration> confs = new ThreadLocal() {
        public FSTConfiguration initialValue() {
            return FSTConfiguration.createDefaultConfiguration();
        }
    };

    private static FSTConfiguration getFST() {
        return confs.get();
    }

    public static <T> byte[] serializer(Object o) {
        return getFST().asByteArray(o);
    }


    public static <T> T deserializer(byte[] bytes) {
        return (T) getFST().asObject(bytes);
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
         return getFST().asByteArray(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return getFST().asObject(bytes);
    }
}
 
