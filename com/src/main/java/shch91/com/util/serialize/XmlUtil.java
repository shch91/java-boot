package shch91.com.util.serialize;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.TimeZone;

public class XmlUtil {
    
    private static final Logger logger= LoggerFactory.getLogger(XmlUtil.class);

    private static final String ENCODING = "UTF-8";

    public static final XmlFriendlyNameCoder nameCoder = new XmlFriendlyNameCoder("-_", "_");

    private static final DomDriver fixDriver = new DomDriver(ENCODING, nameCoder);

    // 通用解析器
    public static final XStream XSTREAM = new XStream(fixDriver);

    private static final String CHINA_TIME_ZONE = "Asia/Shanghai";

    static {
        // 时区处理
        TimeZone zone = TimeZone.getTimeZone(CHINA_TIME_ZONE); //获得时区
        XSTREAM.registerConverter(new DateConverter(zone), XStream.PRIORITY_NORMAL);
        XSTREAM.autodetectAnnotations(true); //开启序列化的注解形式
        XSTREAM.setMode(XStream.NO_REFERENCES);//取消引用,如果没有这一步,会出现xml引用格式reference

    }


    //xml to bean
    public static<T> Object fromXML(String xml,Class<T> t) {
        Object target = null;
        XStream xstream = XSTREAM;
        xstream.processAnnotations(t);//开启此类的解析,否则无法解析
        try {
            target = xstream.fromXML(xml);
        } catch (Exception e) {
            logger.error( "{}",e);
        }
        return target;
    }



    public static void toXML(Object obj, OutputStream out) {
        XStream xstream = XSTREAM;
        xstream.toXML(obj,out);
        logger.info("输出成功");
    }

    //文件流 to bean
    public static <T> Object fromXML(InputStream in,Class<T> t) {
        Object target = null;
        XStream xstream = XSTREAM;
        xstream.processAnnotations(t);
        try {
            target =  xstream.fromXML(in);
            logger.info("输入成功");
        } catch (Exception e) {
            logger.error( "{}",e);
        }
        return target;
    }


    //bean to xml
    public  static String toXML(Object obj) {
        XStream xstream = XSTREAM;
        String xml= xstream.toXML(obj);
        return xml;
    }

}
