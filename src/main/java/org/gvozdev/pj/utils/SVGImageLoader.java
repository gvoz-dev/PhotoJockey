package org.gvozdev.pj.utils;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.XMLAbstractTranscoder;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.file.CloseFileAction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SVGImageLoader {
  private static final Logger logger = LogManager.getLogger(CloseFileAction.class);

  public static BufferedImage loadSVG(URL url, float width, float height) throws IOException {
    SVGTranscoder transcoder = new SVGTranscoder();
    transcoder.setTranscodingHints(getTranscodingHints(width, height));
    try {
      TranscoderInput input = new TranscoderInput(url.openStream());
      transcoder.transcode(input, null);
    } catch (TranscoderException e) {
      throw new IOException("SVG file parsing error", e);
    }
    BufferedImage image = transcoder.getImage();
    var fileName = new File(url.getFile()).getName();
    logger.info(String.format("Read '%s' SVG image", fileName));
    return image;
  }

  private static TranscodingHints getTranscodingHints(float width, float height) {
    TranscodingHints hints = new TranscodingHints();
    hints.put(XMLAbstractTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
    hints.put(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
    hints.put(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
    hints.put(SVGAbstractTranscoder.KEY_WIDTH, width);
    hints.put(SVGAbstractTranscoder.KEY_HEIGHT, height);
    return hints;
  }

  private static class SVGTranscoder extends ImageTranscoder {
    private BufferedImage image = null;

    @Override
    public BufferedImage createImage(int width, int height) {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      return image;
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
    }

    BufferedImage getImage() {
      return image;
    }
  }
}
