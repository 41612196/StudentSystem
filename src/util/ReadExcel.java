package util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import main.vo.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/16
 */
public class ReadExcel {

    public List readExcel(File file) {
        List<Score> scoreList = new ArrayList<Score>();
        try {
            InputStream inputStream = new FileInputStream(file.getAbsolutePath());
            Workbook workbook = Workbook.getWorkbook(inputStream);
            int sheet_size = workbook.getNumberOfSheets();

            List<String> eachScore = new ArrayList<>();
            Score score = null;
            for (int index = 0; index < sheet_size; index++) {
                Sheet sheet = workbook.getSheet(index);
                for (int i =1 ; i < sheet.getRows(); i++) {
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        System.out.print(cellinfo+'\t');
                        eachScore.add(j,cellinfo);
                    }
                    score = new Score();
                    score.setStudentId(Integer.parseInt(eachScore.get(0)));
                    score.setCourseId(Integer.parseInt(eachScore.get(1)));
                    score.setStudentName(eachScore.get(2));
                    score.setCourseName(eachScore.get(3));
                    score.setGrade(Integer.parseInt(eachScore.get(4)));
                    scoreList.add(score);

                    System.out.println();
                }
                System.out.println("表："+scoreList);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}
