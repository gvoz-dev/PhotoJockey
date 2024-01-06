package org.gvozdev.pj.processing.filters.color;

import org.gvozdev.pj.processing.filters.ImageFilter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;

import static org.gvozdev.pj.utils.CvUtils.bufferedImage2Mat;
import static org.gvozdev.pj.utils.CvUtils.mat2BufferedImage;

/**
 * Создаёт эффект сепии.
 *
 * @author Roman Gvozdev
 */
public class Sepia implements ImageFilter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        Mat img = bufferedImage2Mat(image);
        // Матрица трансформации
        Mat kernel = new Mat(3, 3, CvType.CV_32F);
        kernel.put(0, 0,
                // blue = b * b1 + g * g1 + r * r1
                0.131, 0.534, 0.272,
                // green = b * b2 + g * g2 + r * r2
                0.168, 0.686, 0.349,
                // red = b * b3 + g * g3 + r * r3
                0.189, 0.769, 0.393);
        // Преобразование компонентов цвета исходного изображения в сепию при помощи матрицы трансформации
        Mat sepiaImg = new Mat();
        Core.transform(img, sepiaImg, kernel);
        return mat2BufferedImage(sepiaImg);
    }
}
