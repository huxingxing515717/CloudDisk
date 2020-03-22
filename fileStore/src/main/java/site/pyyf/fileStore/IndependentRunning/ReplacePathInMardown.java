package site.pyyf.fileStore.IndependentRunning;

import java.io.*;

public class ReplacePathInMardown {

    /**
     * Created by "gepeng" on 2020-03-82 08:34:18.
     * @Description 将filePath处的markdown文件中的src替换为dst，同时src处的图片将被复制到markdown同级目录的imgs文件夹下
     * @param [filePath, src, dst]
     * @return void
     */
    public static void replace(String filePath, String src, String dst) throws Exception {

        String fileName = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
        String fileNameWithoutSuffix = fileName.split("\\.")[0];
        String suffix = fileName.split("\\.")[1];
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String buffer = null;
        while ((buffer = bfr.readLine()) != null)
            content.append(buffer).append("\n");
        StringBuilder result = new StringBuilder();
        int p1 = 0;
        int p2 = 0;
        while (p1 < content.length() && p2 < content.length()) {
            int start = 0;
            //当第一个字母满足条件时
            while (p2 < content.length() && start < src.length()) {
                if (content.charAt(p2) == src.charAt(start)) {
                    p2++;
                    start++;
                } else {
                    break;
                }
            }
            if (start == src.length()) {
                result.append(dst);
                p1 = p1 + start;
                boolean findPng = false;
                int p3 = p1;
                while (p3 < content.length()) {
                    if (p3 == p1 + 40)
                        break;
                    if (content.charAt(p3) == '.' && p3 + 3 < content.length()) {

                        if (content.charAt(p3 + 1) == 'p' && content.charAt(p3 + 2) == 'n' && content.charAt(p3 + 3) == 'g') {
                            p3 = p3 + 3;
                            findPng = true;
                            break;
                        }
                    }
                    p3++;

                }
                if (findPng) {
                    StringBuilder imgName = new StringBuilder();
                    for (int i = p1; i <= p3; i++)
                        imgName.append(content.charAt(i));

                    if (!new File(new File(filePath).getParent() + "\\" + "imgs\\" + fileNameWithoutSuffix).exists())
                        new File(new File(filePath).getParent() + "\\" + "imgs\\" + fileNameWithoutSuffix).mkdirs();
                    copyFile(src + imgName, new File(filePath).getParent() + "\\" + "imgs\\" + fileNameWithoutSuffix + "\\" + imgName);
                }

            } else {
                result.append(content.charAt(p1));
                p1++;
            }
            p2 = p1;

        }

        final File file = new File(new File(filePath).getParent() + "//" + fileNameWithoutSuffix + "_copy" + "." + suffix);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    private static void copyFile(String oriPath, String dstPath) {
        try (InputStream is = new FileInputStream(oriPath);
             OutputStream os = new FileOutputStream(dstPath)) {
            //3、操作 (分段读取)
            byte[] flush = new byte[1024]; //缓冲容器
            int len = -1; //接收长度
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len); //分段写出
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath = "F:\\Projects\\Java\\tianti\\ebook\\src\\main\\resources\\容器.md";
        String fileName = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
        String fileNameWithoutSuffix = fileName.split("\\.")[0];
        String src = "C:\\Users\\Freya\\AppData\\Roaming\\Typora\\typora-user-images\\";
        String dst = "https://pyyf.oss-cn-hangzhou.aliyuncs.com/ebook/" + fileNameWithoutSuffix + "\\";
        replace(filePath,src,dst);
    }


}
