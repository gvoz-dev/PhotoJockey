package org.gvozdev.pj.processing.filters.color;

import org.gvozdev.pj.processing.filters.ImageFilter;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;

import static org.gvozdev.pj.utils.CvUtils.bufferedImage2Mat;
import static org.gvozdev.pj.utils.CvUtils.mat2BufferedImage;

/**
 * Преобразует компоненты цвета изображения в оттенки серого.
 *
 * @author Roman Gvozdev
 */
public class Grayscale implements ImageFilter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        Mat img = bufferedImage2Mat(image);
        // Преобразование исходного изображения в цветовое пространство GRAY
        Mat grayImg = new Mat();
        Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_BGR2GRAY);
        // Обратное преобразование изображения с оттенками серого в цветовое пространство BGR
        Mat bgrImg = new Mat();
        Imgproc.cvtColor(grayImg, bgrImg, Imgproc.COLOR_GRAY2BGR);
        return mat2BufferedImage(bgrImg);
    }
}
