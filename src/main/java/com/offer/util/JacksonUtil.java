package com.offer.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liuhao
 */
public class JacksonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        //空值也序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //忽略Json中的属性在java对象上不存在
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //日期统一格式
        MAPPER.setDateFormat(UtilDate.SIMPLE_DATE_FORMAT);
        //转义字符处理
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    /**
     * JavaBean ==> json
     *
     * @param bean
     * @return
     */
    public static <T> String bean2Json(T bean) {
        try {
            return MAPPER.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            LOG.error("JavaBean ===> JSON ERROR : {}", e.getMessage());
        }
        return null;
    }

    /**
     * JavaBean ==> map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> bean2Map(T bean) {
        return json2Map(bean2Json(bean));
    }

    public static <T> Map<String, String> bean2Map1(T bean) {
        return json2Map1(bean2Json(bean));
    }
    public static <T> Map<String, String> bean2Map2(T bean) {
        MAPPER.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            private static final long serialVersionUID = 1L;

            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return method.getName().substring(3);
            }

            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return method.getName().substring(3);
            }
        });
        return json2Map1(bean2Json(bean));
    }

    /**
     * json ==> Object
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("JSON ==> {} ERROR : {}", clazz.getClass().getName(), e.getMessage());
        }
        return null;
    }

    /**
     * json ==> Object[]
     *
     * @param json
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> Class[] json2Array(String json, Class<T>[] classes) {
        try {
            return MAPPER.readValue(json, classes.getClass());
        } catch (IOException e) {
            LOG.error("JSON ==> {} ERROR : {}", classes.getClass().getName(), e.getMessage());
        }
        return null;
    }

    /**
     * json ==> Map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2Map(String json) {
        return json2Obj(json, HashMap.class);
    }

    public static Map<String, String> json2Map1(String json) {
        return json2Obj(json, HashMap.class);
    }

    /**
     * json ==> List
     *
     * @param json
     * @return
     */
    public static List json2List(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List>() {
            });
        } catch (IOException e) {
            LOG.error("JSON ==> List ERROR : {}", e.getMessage());
        }
        return null;
    }

    /**
     * 如果需要构建复杂的JSON格式,找MAPPER吧
     *
     * @return
     */
    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static String list2Json(List list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * json --->  List
     *
     * @param json
     * @param clazz
     * @return
     */
    public static List json2List(String json, Class clazz) {
        JSONArray json2 = JSONArray.fromObject(json);
        return (List) JSONArray.toCollection(json2, clazz);
    }
}