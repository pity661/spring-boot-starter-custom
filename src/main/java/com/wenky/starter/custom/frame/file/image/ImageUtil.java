package com.wenky.starter.custom.frame.file.image;

import com.wenky.starter.custom.util.Base64Utils;
import com.wenky.starter.custom.util.LoggerUtils;
import com.wenky.starter.custom.util.StringUtils;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;

/**
 * @program: olading-invoice
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-09 09:53
 */
public class ImageUtil {

    public static void main(String[] args) throws Exception {
        imageUrl2Base64Test();
    }

    public static void imageUrl2Base64Test() {
        //    String imageUrl = "https://www.google.com/intl/en_ALL/images/logo.gif";
        String imageUrl =
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F4k%2Fs%2F01%2F2109241100552a4-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649404736&t=8c78f7828a97716d355496ce35635b63";
        String result = imageUrl2Base64(imageUrl);
        System.out.println(result);
    }

    /**
     * 本地读取图片切割后输出
     *
     * @throws IOException
     */
    public static void fileCutTest() throws IOException {
        File file = new File("/Users/huwenqi/Desktop/123.png");
        InputStream source = new FileInputStream(file);
        source = new BufferedInputStream(source);
        source.mark(Integer.MAX_VALUE);
        BufferedImage cutBufferedImage = cutImage(source, 0, 0, 2234, 466);
        source.reset();
        String mediaType = new Tika().detect(source);
        if (cutBufferedImage != null) {
            ImageIO.write(
                    cutBufferedImage,
                    StringUtils.regexSplit(mediaType, "^.*?/"),
                    new File("/Users/huwenqi/Desktop/1231.jpg"));
        }
    }

    /**
     * 切割图片并装化为InputStream格式
     *
     * @param source
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     * @return
     */
    public static InputStream cutImage2InputStream(
            InputStream source, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        try {
            // 1 markSupported false -> true
            if (!source.markSupported()) {
                source = new BufferedInputStream(source);
            }
            // 2
            source.mark(Integer.MAX_VALUE);
            // 第一次读取
            BufferedImage bufferedImage =
                    cutImage(source, topLeftX, topLeftY, bottomRightX, bottomRightY);
            // 3 reset to 0
            source.reset();
            // 第二次读取
            return bufferedImage2InputStream(bufferedImage, new Tika().detect(source));
        } catch (Exception e) {
            LoggerUtils.exception("图片剪裁发生异常: ", e);
        }
        return null;
    }

    /**
     * 切割图片
     *
     * @param source
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     * @return
     */
    public static BufferedImage cutImage(
            InputStream source, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        try {
            BufferedImage sourceImage = ImageIO.read(source);
            BufferedImage cutBufferedImage =
                    sourceImage.getSubimage(topLeftX, topLeftY, bottomRightX, bottomRightY);
            return cutBufferedImage;
        } catch (Exception e) {
            LoggerUtils.exception("图片剪裁发生异常: ", e);
        }
        return null;
    }

    /**
     * 将bufferedImage转化为指定格式输出
     *
     * @param bufferedImage
     * @param mediaType
     * @return
     */
    public static InputStream bufferedImage2InputStream(
            BufferedImage bufferedImage, String mediaType) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, mediaType, os);
            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
            return inputStream;
        } catch (Exception e) {
            LoggerUtils.exception("图片剪裁发生异常: ", e);
        }
        return null;
    }

    /**
     * 获取网络图片数据转化为base64字符串
     *
     * @param imageURL
     * @return
     */
    public static String imageUrl2Base64(String imageURL) {
        if (StringUtils.isBlank(imageURL)) {
            return null;
        }

        try {
            URL url = new URL(imageURL);
            return Base64Utils.encode(IOUtils.toByteArray(url));
        } catch (Exception e) {
            LoggerUtils.exception(String.format("imageURL: %s, convert error", imageURL), e);
        }
        return null;
    }
}
