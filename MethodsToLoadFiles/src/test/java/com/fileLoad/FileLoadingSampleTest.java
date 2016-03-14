package com.fileLoad;

import com.sdm.fileLoaders.FileLoader;
import org.apache.commons.configuration.Configuration;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileLoadingSampleTest {
    @Test
    public void LoadFileUsingApacheConfig() {
        Configuration configuration = new FileLoader().LoadFileUsingApacheConfiguration();
        Iterator<String> iterator1 = configuration.getKeys();
        for (Iterator iterator = configuration.getKeys(); iterator.hasNext(); ) {
            System.out.print(iterator1.next()+" = ");
            System.out.println(configuration.getString(iterator.next().toString()));
        }
    }

    @Test
    public void LoadFileUsingInputStream(){
        Properties properties = new FileLoader().LoadFileUsingInputStream();

        Set<String> set = properties.stringPropertyNames();
        Iterator iterator1 = set.iterator();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.print(iterator.next()+" = ");
            System.out.println(properties.getProperty(iterator1.next().toString()));
        }
    }

    @Test
    public void LoadFilesUsingYaml(){
        Map map = new FileLoader().LoadFileUsingYAML();
        Map map1 = map;
        Iterator keyIterator = map.keySet().iterator();
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); ) {
            System.out.print(iterator.next()+" = ");
            System.out.println(map.get(keyIterator.next()));
        }
    }
}
