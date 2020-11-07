package shch91.com.util.serialize;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;


public class ProtostuffUtil  {
	public static <T> byte[] serialize(T t, Class<T> clazz) {
		return ProtobufIOUtil.toByteArray(t, RuntimeSchema.createFrom(clazz),
				LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
	}

	public static <T> T deserialize(byte[] data, Class<T> clazz) {
		RuntimeSchema<T> runtimeSchema = RuntimeSchema.createFrom(clazz);
		T t = runtimeSchema.newMessage();
		ProtobufIOUtil.mergeFrom(data, t, runtimeSchema);
		return t;
	}

}
