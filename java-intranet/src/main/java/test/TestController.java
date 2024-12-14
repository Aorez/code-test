package test;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            // 指定要下载的文件路径
            String filePath = "D:\\Download\\" + filename;
            File file = new File(filePath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // 设置HTTP头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            // 返回ResponseEntity对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常情况
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的字节
            byte[] bytes = file.getBytes();

            // 文件保存的路径
            String folderPath = "D:\\Download\\";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdir();
            }

            // 文件保存的全路径
            File destFile = new File(folderPath + File.separator + fileName);

            // 将文件写入磁盘
            file.transferTo(destFile);
            System.out.println("用户上传");

            return "You have successfully uploaded " + fileName + "!";
        } catch (IOException e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
