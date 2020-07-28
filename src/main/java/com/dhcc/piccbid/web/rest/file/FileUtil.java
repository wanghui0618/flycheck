package com.dhcc.piccbid.web.rest.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Annotation:
 * 删除文件或者目录
 *
 * @Author: Adam Ming
 * @Date: 2018/5/8 4:51 PM
 */
public class FileUtil {
    private static Log logger = LogFactory.getLog(FileUtil.class);

    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            logger.info(filePath + " does not exists!");
            return false;
        }

        if (file.isFile()) {
            return deleteFile(filePath);
        } else {
            return deleteDirectory(filePath);
        }
    }

    /**
     * Annotation:
     * 删除单个文件
     *
     * @Author: Adam Ming
     * @Date: 2018/5/8 5:00 PM
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);

        if (file.isFile() && file.exists()) {
            file.delete();
            logger.info(fileName + " delete file success!");

            return true;
        } else {
            logger.info(fileName + " delete file failure!");

            return false;
        }
    }

    /**
     * Annotation:
     * 删除目录（文件夹）以及目录下的文件
     *
     * @Author: Adam Ming
     * @Date: 2018/5/9 9:20 AM
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir 不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir += File.separator;
        }
        File dirFile = new File(dir);

        // 如果 dir 对应的文件夹不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            logger.info(dirFile + " delete directory failure!");

            return false;
        }

        boolean flag = true;

        // 删除文件夹下所有文件（包括目录）——迭代
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (file.isFile()) { // 删除子文件
                flag = deleteFile(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else { // 删除子目录
                flag = deleteDirectory(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            logger.info(dirFile + " delete directory failure!");

            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            logger.info(dirFile + " delete directory success!");

            return true;
        } else {
            logger.info(dirFile + " delete directory failure!");

            return false;
        }
    }

    /**
     * Annotation:
     * 获取图片缩略图
     *
     * @Author: Adam Ming
     * @Date: 2018/5/9 11:34 AM
     */
    public static String getThumbnail(String picUrl) {
        return picUrl.substring(0, picUrl.lastIndexOf(".")) + "small" + picUrl.substring(picUrl.lastIndexOf("."));
    }

    /*
     * Annotation:
     * 文件上传
     *
     * @Author: Adam Ming
     * @Date: Jan 22, 2019 at 4:11:58 PM
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
