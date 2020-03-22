package site.pyyf.fileStore.IndependentRunning;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class ProcessMdToEbook {
    private static OssUpload upload =OssUpload.getInstance();
    private static ReplacePathInMardown md2oss = new ReplacePathInMardown();

    private static void replacePath(String ossPath,String suffix, String filePath) throws Exception {
        String fileName = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
        String fileNameWithoutSuffix = fileName.split("\\.")[0];
        String src = ".\\imgs\\" + fileNameWithoutSuffix + "\\";
        String dst = ossPath+suffix + fileNameWithoutSuffix + "\\";
        md2oss.replace(filePath, src, dst);
    }

    private static void uploadDir(String suffix,String imgsPath){
        File file = new File(imgsPath);
        for (File listFile : file.listFiles()) {
            upload.upload(suffix,listFile);
        }
    }


    public static void main(String[] args) throws Exception {
        String filePath = "F:\\OneDrive\\笔记\\项目\\community.md";
        String suffix = "cloudDisk/ebook";
        String ossPath = "https://pyyf.oss-cn-hangzhou.aliyuncs.com/";

        String imgsPath = StringUtils.substringBeforeLast(filePath, "\\") + "\\imgs\\" +
                StringUtils.substringAfterLast(StringUtils.substringBeforeLast(filePath, "."), "\\") ;
        replacePath(ossPath,suffix,filePath);
        uploadDir(suffix,imgsPath);
    }
}
