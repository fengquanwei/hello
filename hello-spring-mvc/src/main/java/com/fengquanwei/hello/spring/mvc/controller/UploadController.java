package com.fengquanwei.hello.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * UploadController
 *
 * @author fengquanwei
 * @create 2018/6/5 10:17
 **/
@Controller
@RequestMapping("/upload")
public class UploadController {
    // 显示上传页面
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    /**
     * 使用 byte[] 参数的形式接受文件上传，需要在 Servlet 中 setMultipartConfig
     */
    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    public String upload1(@RequestPart byte[] imageFile, @RequestParam String name) {
        System.out.println(imageFile.length);
        return "redirect:/info/" + name;
    }

    /**
     * 使用 MultipartFile 参数的形式接受文件上传，需要在 Servlet 中 setMultipartConfig，需要配置 MultipartResolver bean
     */
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public String upload2(@RequestPart MultipartFile imageFile, @RequestParam String name) {
        System.out.println(imageFile);

        // 保存图片
        try {
            imageFile.transferTo(new File("/opt/tmp/image_" + imageFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/info/" + name;
    }

    /**
     * 使用 Part 参数的形式接受文件上传，需要在 Servlet 中 setMultipartConfig，不需要配置 MultipartResolver bean
     */
    @RequestMapping(value = "/upload3", method = RequestMethod.POST)
    public String upload3(@RequestPart Part imageFile, @RequestParam String name) {
        System.out.println(imageFile);

        // 保存图片
        try {
            String filepath = "/opt/tmp/image_default_" + imageFile.getName();

            String header = imageFile.getHeader("content-disposition");
            if (header != null && header.length() > 0) {
                String filename = header.substring(header.indexOf("filename"));
                if (filename != null) {
                    filename = filename.replace("filename=\"", "");
                    filename = filename.substring(0, filename.indexOf("\""));
                    filepath = "/opt/tmp/image_" + filename;
                }
            }

            imageFile.write(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/info/" + name;
    }
}
