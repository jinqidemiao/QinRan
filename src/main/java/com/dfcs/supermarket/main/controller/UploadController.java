package com.dfcs.supermarket.main.controller;

import com.dfcs.supermarket.main.common.BaseResponse;
import com.dfcs.supermarket.main.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传控制器
 *
 * @author caoxinyu
 * @date 2020/01/11
 */
@RestController
@RequestMapping("/main/upload")
@Slf4j
public class UploadController {

    @Value("${upload.file.path}")
    private String filePath;
	
	/**
	 * 上传
	 *
	 * @param file 文件
	 *
	 * @return {@link BaseResponse}
	 */
	@PostMapping("upload")
    public BaseResponse upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
	    String fileName = null;
	    if (originalFilename != null) {
		    fileName = originalFilename.substring(0,originalFilename.indexOf(".")) + "_" + System.currentTimeMillis()+originalFilename.substring(originalFilename.indexOf("."));
	    }
	    log.info("fileName  ---->  {}",fileName);
        try {
            UploadUtil.uploadFile(file.getBytes(), filePath, fileName);
            return BaseResponse.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.err("500","文件上传失败");
        }
    }

}
