package icu.rolin.easy.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

public class Base64ToImageUtil implements MultipartFile {

    private final byte[] imageContent;
    private final String header;

    public Base64ToImageUtil(byte[] imageContent,String header) {
        this.imageContent = imageContent;
        this.header=header;
    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64ToImageUtil(b,baseStrs[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imageContent == null || imageContent.length == 0;
    }

    @Override
    public long getSize() {
        return imageContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imageContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imageContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imageContent);
    }

}