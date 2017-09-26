package org.thiki.datavalidator.foundation.util;

import java.nio.charset.Charset;

public class CharsetUtil {

    private CharsetUtil() {
    }
    
    public static Charset UTF8 = Charset.forName("utf-8");
    
    public static Charset GBK = Charset.forName("gbk");
    
    public static Charset ISO88591 = Charset.forName("iso-8859-1");
    
}
