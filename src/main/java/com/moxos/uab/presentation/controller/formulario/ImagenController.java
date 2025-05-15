package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.business.service.impl.PhotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
@RequestMapping("/imagenes")
public class ImagenController {

    private final PhotoStorageService photoStorageService;

    @GetMapping("/{directorio}/{nombreImagen}")
    @ResponseBody
    public Resource obtenerImagen(@PathVariable String nombreImagen, @PathVariable String directorio) throws MalformedURLException {
        return photoStorageService.download("/investigacion/" + directorio, nombreImagen);
    }
}
