/**
 * 
 */
package cn.org.citycloud.srdz.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Sang-Hyup Lee
 *
 */
public class Base64EncodeDecode {


    /**
     * convert base64 to ASCII
     * 
     * @param encodeBytes
     * @return
     */
    public static String encode(byte[] encodeBytes) {
        byte[] buf = null;
        String strResult = null;

        BASE64Encoder base64Encoder = new BASE64Encoder();
        ByteArrayInputStream bin = new ByteArrayInputStream(encodeBytes);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            base64Encoder.encodeBuffer(bin, bout);
            buf = bout.toByteArray();
            strResult = new String(buf).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    /**
     * convert ASCII to base64
     * 
     * @param strDecode
     * @return
     */
    public static byte[] decode(String strDecode) {
        byte[] buf = null;

        BASE64Decoder base64Decoder = new BASE64Decoder();
        ByteArrayInputStream bin = new ByteArrayInputStream(strDecode.getBytes());
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            base64Decoder.decodeBuffer(bin, bout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf = bout.toByteArray();
        return buf;
    }
    
}
