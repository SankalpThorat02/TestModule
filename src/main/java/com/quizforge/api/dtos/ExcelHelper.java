package com.quizforge.api.dtos;

import com.quizforge.api.dtos.QuestionUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {
    public static boolean hasExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType);
    }

    public static List<QuestionUpload> excelToQuestions(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            List<QuestionUpload> questions = new ArrayList<>();
            int rowNumber = 0;

            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                QuestionUpload dto = new QuestionUpload();

                dto.setQuestionText(row.getCell(0).getStringCellValue());
                dto.setOptionA(row.getCell(1).getStringCellValue());
                dto.setOptionB(row.getCell(2).getStringCellValue());
                dto.setOptionC(row.getCell(3).getStringCellValue());
                dto.setOptionD(row.getCell(4).getStringCellValue());
                dto.setCorrectOption(row.getCell(5).getStringCellValue());

                questions.add(dto);
            }

            workbook.close();
            return questions;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}