/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package net.hyjuki.smgen.base;

import java.io.*;

public class FileUtils {
    /**
     * Writes, no overwrites, the contents of the specified file.
     * @param file
     * @param content
     * @param fileEncoding
     * @throws IOException Signals that an I/O exception has occurred.
                */
    public static boolean writeFile(File file, String content, String fileEncoding) throws IOException {
        if (file.exists()) {
            System.out.println("==文件["+ file.getAbsolutePath() +
                    "]已经存在，为防止损失，请先备份文件并清空目录。==");
            return false;
        }
        if (!file.getParentFile().exists()) {
            boolean result = file.getParentFile().mkdirs();
            if (!result) {
                System.out.println("文件目录创建失败");
            }
        }

        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        try (BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(content);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Writes, no overwrites, the contents of the specified file.
     * @param fileName
     * @param content
     * @param fileEncoding
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static boolean writeFile(String fileName, String content, String fileEncoding) throws IOException {
        File file = new File(fileName);
        return writeFile(file, content, fileEncoding);
    }

    public static boolean writeFile(File file, String content) throws IOException {
        return writeFile(file, content, "UTF-8");
    }

    public static boolean writeFile(String fileName, String content) {
        File file = new File(fileName);
        try {
            return writeFile(file, content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
