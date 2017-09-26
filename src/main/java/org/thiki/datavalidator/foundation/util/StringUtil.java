package org.thiki.datavalidator.foundation.util;

import java.nio.charset.Charset;

public class StringUtil {
 
	private static final byte[] EMPTY_BYTES = new byte[0];

    /**
	 * 是否空白
	 * @param x
	 * @return
	 */
    public static boolean isEmpty(String x) {
        int len;
        if (x == null || (len = x.length()) == 0)
            return true;

        while (len-- > 0) {
            if (!Character.isWhitespace(x.charAt(len)))
                return false;
        }

        return true;
    }
    
	/**
	 * 判断一个字符串是否由0-9组成
	 *
	 * @param x
	 * @return
	 */
	public static boolean isNumber(String x) {
		int len;
		if (x == null || (len = x.length()) == 0)
			return false;
		while (len-- > 0) {
			char temp = x.charAt(len);
			if (temp > 57 || temp < 48)
				return false;
		}
		return true;
	}
	
	/**
	 * trim
	 *
	 * @param x
	 * @return
	 */
	public static String trim(String x) {
		if (x == null)
			return null;

		return x.trim();
	}
	
    public static byte[] getBytes(final String string, final Charset charset) {
        if (string == null) {
            return EMPTY_BYTES;
        }
        return string.getBytes(charset);
    }

    public static byte[] getBytesUtf8(final String string) {
        return getBytes(string, CharsetUtil.UTF8);
    }
    
    public static String newStringUtf8(final byte[] bytes) {
        return newString(bytes, CharsetUtil.UTF8);
    }
    
    public static String newString(final byte[] bytes, final Charset charset) {
        return bytes == null ? "" : new String(bytes, charset);
    }
    
    public static boolean isUTF8MB4(String s) {
        int l = s.length();
        return l > s.codePointCount(0, l);
    }
	
	public static String format(String pattern, Object arg1, Object arg2){
		return StringFormatter.format(pattern, arg1, arg2);
	}
	public static String format(String pattern, Object[] argArray){
		return StringFormatter.arrayFormat(pattern, argArray);
	}
    public static String format(String pattern, Object arg){
		return StringFormatter.format(pattern, arg);
    }
    
}
