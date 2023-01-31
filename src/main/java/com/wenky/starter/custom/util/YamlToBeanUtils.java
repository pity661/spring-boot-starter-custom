package com.wenky.starter.custom.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import org.springframework.core.io.Resource;

public class YamlToBeanUtils {

    private static final ObjectMapper YAML_MAPPER =
            new ObjectMapper(new YAMLFactory())
                    .findAndRegisterModules()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T parser(Resource resource, Class<T> clazz) throws IOException {
        return parser(resource.getFile(), clazz);
    }

    public static <T> T parser(Resource resource, Class<T> clazz, String rootName)
            throws IOException {
        return parser(resource.getFile(), clazz, rootName);
    }

    public static <T> T parser(File file, Class<T> clazz) throws IOException {
        return YAML_MAPPER.readValue(file, clazz);
    }

    public static <T> T parser(File file, Class<T> clazz, String rootName) throws IOException {
        return YAML_MAPPER.readerFor(clazz).withRootName(rootName).readValue(file);
    }
}
