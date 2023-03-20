package com.yww.common.utils;

import cn.hutool.core.util.IdUtil;
import com.yww.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *      文件工具类
 * </p>
 *
 * @author  yww
 * @since 2023/3/4
 */
@Slf4j
public class FileUtil {

    /**
     * 保存文件
     *
     * @param file      文件
     * @param filePath  文件保存路径
     */
    public static void saveFile(MultipartFile file, String filePath) {
        String originalFilename = file.getOriginalFilename();
        // 获取文件扩展名
        String ext = getExtName(originalFilename);
        // 生成新的文件名称
        String newName = IdUtil.fastSimpleUUID() + "." + ext;
        String path = filePath + newName;
        File dest = new File(filePath);
        // 若是路径不存在就先创建父级目录
        if (!dest.exists()) {
            cn.hutool.core.io.FileUtil.touch(dest);
        }
        // 保存文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("保存文件出错，保存的文件名称为：{}", originalFilename);
            log.error("保存文件出错，保存的路径为：{}", path);
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 获取文件后缀
     * 参考自hutool的FileUtil.extName()方法
     *
     * @param fileName  文件名称
     * @return          文件后缀
     */
    public static String getExtName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        // 可能会有两个.符号
        int secondToLastIndex = fileName.substring(0, index).lastIndexOf(".");
        return fileName.substring(secondToLastIndex == -1 ? index + 1 : secondToLastIndex + 1);
    }

}
