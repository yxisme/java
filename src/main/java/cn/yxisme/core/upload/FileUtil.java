package cn.yxisme.core.upload;

import cn.yxisme.core.exception.CodeMessageDef;
import cn.yxisme.core.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by yangxiong on 2019/3/11.
 */
@Configuration
public class FileUtil {

    @Autowired
    UploadFileConfig config;

    /**
     * 上传文件
     * @param file
     * @return
     * @throws MyException
     */
    public String upload(MultipartFile file) throws MyException {
        if (file == null || file.isEmpty()) {
            throw new MyException(CodeMessageDef.FILE_IS_NULL);
        }

        System.out.println(file.getSize());
        if (file.getSize() > config.getUploadMaxSize() * 1000 * 1000) {
            throw new MyException(CodeMessageDef.FILE_IS_TOO_LARGE);
        }

        String filename = new Date().getTime() + getSuffix(file.getOriginalFilename());
        File dest = new File(config.getUploadFolder() + filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new MyException(CodeMessageDef.FILE_UPLOAD_FAIL);
        }

        return filename;
    }

    /**
     * 获取文件后缀名
     * @param originalFilename
     * @return
     * @throws MyException
     */
    private String getSuffix(String originalFilename) throws MyException {
        if (StringUtils.isEmpty(originalFilename)) {
            throw new MyException(CodeMessageDef.PARAMETER_ERROR);
        }

        int suffixIndex = originalFilename.lastIndexOf(".");
        if (suffixIndex == -1) {
            throw new MyException(CodeMessageDef.FILE_FORMAT_ERROR);
        }

        return originalFilename.substring(suffixIndex);
    }
}
