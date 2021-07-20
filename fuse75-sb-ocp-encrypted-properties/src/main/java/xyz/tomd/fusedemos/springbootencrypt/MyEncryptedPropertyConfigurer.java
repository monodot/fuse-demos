package xyz.tomd.fusedemos.springbootencrypt;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
public class MyEncryptedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static String PREFIX = "{cats}";

    @Override
    protected String convertPropertyValue(String originalValue) {
        System.out.println("HELLO");
        if (originalValue.startsWith(PREFIX)) {
            return "DECRYPTED" + originalValue;
        }
        return originalValue;
    }

}
