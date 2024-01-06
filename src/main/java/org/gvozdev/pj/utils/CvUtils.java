package org.gvozdev.pj.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

/**
 * Утилиты для работы с библиотекой OpenCV.
 *
 * @author Roman Gvozdev
 */
public final class CvUtils {
    private CvUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Преобразование {@link Mat} в {@link BufferedImage}.
     *
     * @param mat матрица OpenCV
     * @return изображение BufferedImage
     */
    public static BufferedImage mat2BufferedImage(final Mat mat) {
        if (mat == null || mat.empty()) {
            throw new IllegalArgumentException("Mat is null or empty");
        }

        Mat preparedMat = switch (mat.depth()) {
            case CvType.CV_8U -> mat.clone();
            case CvType.CV_16U -> {
                Mat mat16 = new Mat();
                mat.convertTo(mat16, CvType.CV_8U, 255. / 65535.);
                yield mat16;
            }
            case CvType.CV_32F -> {
                Mat mat32 = new Mat();
                mat.convertTo(mat32, CvType.CV_8U, 255.);
                yield mat32;
            }
            default -> throw new IllegalStateException("Unsupported Mat depth");
        };

        int type = switch (preparedMat.channels()) {
            case 1 -> BufferedImage.TYPE_BYTE_GRAY;
            case 3 -> BufferedImage.TYPE_3BYTE_BGR;
            case 4 -> BufferedImage.TYPE_4BYTE_ABGR;
            default -> throw new IllegalStateException("Unsupported number of Mat channels");
        };

        byte[] buf = new byte[preparedMat.channels() * preparedMat.cols() * preparedMat.rows()];
        preparedMat.get(0, 0, buf);
        if (type == BufferedImage.TYPE_4BYTE_ABGR) {
            BGRA2ABGR(buf); // BGRA -> ABGR
        }
        BufferedImage image = new BufferedImage(preparedMat.cols(), preparedMat.rows(), type);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buf, 0, data, 0, buf.length);
        return image;
    }

    /**
     * Преобразование {@link Mat} в {@link BufferedImage}.
     *
     * @param image изображение BufferedImage
     * @return матрица OpenCV
     */
    public static Mat bufferedImage2Mat(final BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException();
        }

        int type = switch (image.getType()) {
            case BufferedImage.TYPE_BYTE_GRAY -> CvType.CV_8UC1;
            case BufferedImage.TYPE_3BYTE_BGR -> CvType.CV_8UC3;
            case BufferedImage.TYPE_4BYTE_ABGR -> CvType.CV_8UC4;
            default -> throw new IllegalStateException("Unsupported type of BufferedImage");
        };

        Mat mat = new Mat(image.getHeight(), image.getWidth(), type);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        if (type == CvType.CV_8UC4) {
            byte[] buf = Arrays.copyOf(data, data.length);
            ABGR2BGRA(buf); // ABGR -> BGRA
            mat.put(0, 0, buf);
        } else {
            mat.put(0, 0, data);
        }
        return mat;
    }

    /**
     * Преобразование BGRA -> ABGR байтового буфера изображения.
     *
     * @param buf байтовый буфер, который является представлением изображения с 4 каналами BGRA
     */
    public static void BGRA2ABGR(byte[] buf) {
        if (buf.length % 4 != 0) {
            throw new IllegalArgumentException("Byte buffer size is not a multiple of 4");
        }

        byte tmp;
        for (int i = 0; i < buf.length; i += 4) {
            tmp = buf[i + 3];
            buf[i + 3] = buf[i + 2];
            buf[i + 2] = buf[i + 1];
            buf[i + 1] = buf[i];
            buf[i] = tmp;
        }
    }

    /**
     * Преобразование ABGR -> BGRA байтового буфера изображения.
     *
     * @param buf байтовый буфер, который является представлением изображения с 4 каналами ABGR
     */
    public static void ABGR2BGRA(byte[] buf) {
        if (buf.length % 4 != 0) {
            throw new IllegalArgumentException("Byte buffer size is not a multiple of 4");
        }

        byte tmp;
        for (int i = 0; i < buf.length; i += 4) {
            tmp = buf[i];
            buf[i] = buf[i + 1];
            buf[i + 1] = buf[i + 2];
            buf[i + 2] = buf[i + 3];
            buf[i + 3] = tmp;
        }
    }
}
