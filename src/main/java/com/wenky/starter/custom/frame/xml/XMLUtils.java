package com.wenky.starter.custom.frame.xml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-06-09 14:39
 */
public class XMLUtils {

    public static <T> String toXML(T t) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (Exception e) {

        }
        return null;
    }

    public static <T> void toXMLFile(T t, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer = new FileWriter(filePath);
            marshaller.marshal(t, writer);
        } catch (Exception e) {

        }
    }

    public static <T> T toObj(Class<T> tClass, String content) {
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(content);
            return (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {

        }
        return null;
    }

    public static <T> T fileToObj(Class<T> tClass, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader reader = new FileReader(filePath);
            return (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        User user = new User(26, "wenky");
        // System.out.println(toXML(user));
        // System.out.println(toObj(User.class, toXML(user)));

        String filePath = "/Users/huwenqi/Desktop/example.xml";
        toXMLFile(user, filePath);
        System.out.println(fileToObj(User.class, filePath));

        // Computer computer = new Computer();
        // computer.setName("11");
        // computer.setUser(user);
        // System.out.println(toXML(computer));
        // System.out.println(toObj(Computer.class, toXML(computer)));

    }
}
