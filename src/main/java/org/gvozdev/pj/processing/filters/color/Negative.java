package org.gvozdev.pj.processing.filters.color;

import org.gvozdev.pj.processing.filters.ImageFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;

import static org.gvozdev.pj.utils.CvUtils.bufferedImage2Mat;
import static org.gvozdev.pj.utils.CvUtils.mat2BufferedImage;

/**
 * Создаёт эффект негатива.
 *
 * @author Roman Gvozdev
 */
public class Negative implements ImageFilter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        Mat src = bufferedImage2Mat(image);
        // Преобразование исходного изображения в негатив при помощи операции побитового НЕ
        Mat negativeImg = new Mat();
        Core.bitwise_not(src, negativeImg);
        return mat2BufferedImage(negativeImg);
    }
}
