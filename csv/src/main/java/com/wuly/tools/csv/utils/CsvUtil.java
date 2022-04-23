package com.wuly.tools.csv.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSV工具类
 *
 * @author wuly
 * @since 2022/04/22
 */
public class CsvUtil {
    private CsvUtil() {
    }

    /**
     * 写csv文件
     *
     * @param csvFilePath 文件生成路径
     */
    public static void writeCSV(String csvFilePath) {
        CsvWriter csvWriter = null;
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            csvWriter = new CsvWriter(csvFilePath, ',', StandardCharsets.UTF_8);
            // 写表头
            String[] csvHeaders = { "编号", "姓名", "年龄" };
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (int i = 0; i < 20; i++) {
                String[] csvContent = { i + "000000", "StemQ", "1" + i };
                csvWriter.writeRecord(csvContent);
            }
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvWriter != null) {
                csvWriter.close();
            }
        }
    }

    /**
     * 读取CSV文件
     *
     * @param csvFilePath 文件路径
     */
    public static void readCSV(String csvFilePath) {
        // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
        CsvReader reader = null;
        try {
            // 用来保存数据
            List<String[]> csvFileList = new ArrayList<>();
            reader = new CsvReader(csvFilePath, ',', StandardCharsets.UTF_8);
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            // 遍历读取的CSV文件
            for (String[] strings : csvFileList) {
                // 取得第row行第0列的数据
                System.out.println("=======" + Arrays.toString(strings));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
