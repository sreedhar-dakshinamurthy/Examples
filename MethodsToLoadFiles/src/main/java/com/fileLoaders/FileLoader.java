package com.fileLoaders;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class FileLoader {

    private Configuration configuration = null;

    public Configuration LoadFileUsingApacheConfiguration() {
        try {
            configuration = new PropertiesConfiguration("sample.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    public Properties LoadFileUsingInputStream() {
        Properties properties = new Properties();
        InputStream inputStream = FileLoader.class.getClassLoader().getResourceAsStream("sample.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Map LoadFileUsingYAML() {
        Yaml yaml = new Yaml();
        Map map = (Map) yaml.load(FileLoader.class.getClassLoader().getResourceAsStream("sample.yaml"));
        return map;
    }
}