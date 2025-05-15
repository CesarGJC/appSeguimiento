package com.moxos.uab.business.service.impl;


import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageCompressionService {

    public byte[] compressImage(InputStream originalImageStream, int width, int height, float quality) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalImageStream);
        if (originalImage == null) {
            throw new IOException("No se pudo leer la imagen.");
        }

        // Escalar la imagen a un thumbnail
        BufferedImage resizedImage = resizeImage(originalImage, width, height);

        // Comprimir la imagen a JPEG
        ByteArrayOutputStream compressedOutput = new ByteArrayOutputStream();
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();

        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(quality); // 0.0 = más comprimido, 1.0 = mejor calidad

        jpgWriter.setOutput(new MemoryCacheImageOutputStream(compressedOutput));
        jpgWriter.write(null, new IIOImage(resizedImage, null, null), jpgWriteParam);
        jpgWriter.dispose();

        return compressedOutput.toByteArray();
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image scaledInstance = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaledInstance, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}