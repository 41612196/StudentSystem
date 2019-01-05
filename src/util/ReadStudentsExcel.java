package util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import main.vo.Score;
import main.vo.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/16
 */
public class ReadStudentsExcel {

    public List readStudentExcel(File file) {
        List<Student> studentList = new ArrayList<Student>();
        try {
            InputStream inputStream = new FileInputStream(file.getAbsolutePath());
            Workbook workbook = Workbook.getWorkbook(inputStream);
            int sheet_size = workbook.getNumberOfSheets();

            List<String> eachStudent = new ArrayList<>();
            Student student = null;
            for (int index = 0; index < sheet_size; index++) {
                Sheet sheet = workbook.getSheet(index);
                for (int i =1 ; i < sheet.getRows(); i++) {
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        System.out.print(cellinfo+'\t');
                        eachStudent.add(j,cellinfo);
                    }
                    student = new Student();
                    student.setStudentId(Integer.parseInt(eachStudent.get(0)));
                    student.setStudentName(eachStudent.get(1));
                    student.setSex(eachStudent.get(2));
                    student.setAge(Integer.parseInt(eachStudent.get(3)));
                    student.setMajor(eachStudent.get(4));
                    student.setYearSchool(Integer.parseInt(eachStudent.get(5)));
                    student.setTelephone(eachStudent.get(6));
                    student.setAddress(eachStudent.get(7));
                    student.setClassId(Integer.parseInt(eachStudent.get(8)));
                    student.setClassName(eachStudent.get(9));

                    studentList.add(student);

                    System.out.println();
                }
                System.out.println("学生表："+studentList);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
