package ua.com.javarush.quest.drogunov.quest.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ua.com.javarush.quest.drogunov.quest.service.ImageService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        String requestURI = req.getRequestURI();
        String target = req.getContextPath() + "/images/";
        String nameImages = requestURI.replace(target, "");
        Optional<Path> file = imageService.getImagePath(nameImages);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            }
        }
    }
}
