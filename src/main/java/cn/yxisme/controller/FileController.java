package cn.yxisme.controller;

import cn.yxisme.core.upload.FileUtil;
import cn.yxisme.core.web.GlobalHandler;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.core.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangxiong on 2019/3/11.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController extends GlobalHandler {

    @Autowired
    FileUtil util;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws MyException
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) throws MyException {
       return new ResultBean(util.upload(file));
    }
}
