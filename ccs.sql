/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : ccs

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-03-26 20:20:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `classId` int(11) NOT NULL,
  `className` varchar(255) DEFAULT NULL,
  `ofCollege` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1601', '软工一班', '计算机科学学院');
INSERT INTO `class` VALUES ('1602', '软工二班', '计算机科学学院');
INSERT INTO `class` VALUES ('1603', '信管', '计算机科学学院');
INSERT INTO `class` VALUES ('1604', '计创', '计科院');
INSERT INTO `class` VALUES ('1605', '计科一班', '计科院');
INSERT INTO `class` VALUES ('1606', '计科二班', '计科院');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` int(11) NOT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `credit` float(10,0) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数学', '3');
INSERT INTO `course` VALUES ('2', '高级英语', '3');
INSERT INTO `course` VALUES ('3', '高数', '4');
INSERT INTO `course` VALUES ('4', '英语', '3');
INSERT INTO `course` VALUES ('5', '大学外语', '1');
INSERT INTO `course` VALUES ('6', '概率论', '2');

-- ----------------------------
-- Table structure for course_arrange
-- ----------------------------
DROP TABLE IF EXISTS `course_arrange`;
CREATE TABLE `course_arrange` (
  `classId` int(11) NOT NULL,
  `className` varchar(255) DEFAULT NULL,
  `courseId` int(11) NOT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classId`,`courseId`,`teacherId`),
  KEY `courseesid` (`courseId`),
  KEY `teachersid` (`teacherId`),
  CONSTRAINT `classesid` FOREIGN KEY (`classId`) REFERENCES `class` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `courseesid` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teachersid` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_arrange
-- ----------------------------
INSERT INTO `course_arrange` VALUES ('1601', '软工一班', '1', '数学', '123', '张大炮');
INSERT INTO `course_arrange` VALUES ('1601', '软工一班', '2', '高级英语', '123', '张大炮');
INSERT INTO `course_arrange` VALUES ('1602', '软工二班', '1', '数学', '123', '张大炮');
INSERT INTO `course_arrange` VALUES ('1602', '软工二班', '2', '高级英语', '3', '马化腾');
INSERT INTO `course_arrange` VALUES ('1602', '软工二班', '2', '高级英语', '123', '张大炮');
INSERT INTO `course_arrange` VALUES ('1605', '计科一班', '5', '大学外语', '2', '任正非');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `studentId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `studentName` varchar(255) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `grade` int(5) DEFAULT NULL,
  PRIMARY KEY (`studentId`,`courseId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('119', '2', '小水', '高级英语', '99');
INSERT INTO `score` VALUES ('119', '4', '小水', '英语', '77');
INSERT INTO `score` VALUES ('122', '2', '大图', '高级英语', '99');
INSERT INTO `score` VALUES ('122', '4', '大图', '英语', '78');
INSERT INTO `score` VALUES ('123', '2', '张流', '高级英语', '68');
INSERT INTO `score` VALUES ('123', '4', '张流', '英语', '87');
INSERT INTO `score` VALUES ('124', '2', '小内聚', '高级英语', '15');
INSERT INTO `score` VALUES ('124', '4', '小内聚', '英语', '78');
INSERT INTO `score` VALUES ('125', '2', '小水第', '高级英语', '45');
INSERT INTO `score` VALUES ('125', '4', '小水第', '英语', '78');
INSERT INTO `score` VALUES ('126', '2', '沙瑞金', '高级英语', '65');
INSERT INTO `score` VALUES ('126', '4', '沙瑞金', '英语', '89');
INSERT INTO `score` VALUES ('222', '2', '大蓝', '高级英语', '95');
INSERT INTO `score` VALUES ('222', '4', '大蓝', '英语', '12');
INSERT INTO `score` VALUES ('333', '2', '小蓝', '高级英语', '45');
INSERT INTO `score` VALUES ('333', '4', '小蓝', '英语', '99');
INSERT INTO `score` VALUES ('444', '2', '大紫', '高级英语', '78');
INSERT INTO `score` VALUES ('444', '4', '大紫', '英语', '89');
INSERT INTO `score` VALUES ('555', '2', '大白', '高级英语', '77');
INSERT INTO `score` VALUES ('555', '4', '大白', '英语', '65');
INSERT INTO `score` VALUES ('666', '2', '小黄', '高级英语', '98');
INSERT INTO `score` VALUES ('666', '4', '小黄', '英语', '66');
INSERT INTO `score` VALUES ('678', '2', '小黑', '高级英语', '78');
INSERT INTO `score` VALUES ('678', '4', '小黑', '英语', '78');
INSERT INTO `score` VALUES ('789', '2', '小白菜', '高级英语', '78');
INSERT INTO `score` VALUES ('789', '4', '小白', '英语', '41');
INSERT INTO `score` VALUES ('888', '2', '小青', '高级英语', '78');
INSERT INTO `score` VALUES ('888', '4', '小青', '英语', '89');
INSERT INTO `score` VALUES ('999', '2', '大青', '高级英语', '45');
INSERT INTO `score` VALUES ('41612196', '2', '彭卫杰', '高级英语', '58');
INSERT INTO `score` VALUES ('41612198', '2', '张铖', '高级英语', '45');
INSERT INTO `score` VALUES ('41612232', '2', '汪鹏', '高级英语', '78');
INSERT INTO `score` VALUES ('41612263', '2', '张靖漪', '高级英语', '45');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(20) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `yearSchool` int(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('116', '李三', '男', '20', '计算机科学与技术（师范）', '2016', '13598756987', '陕师大唐园', null);
INSERT INTO `student` VALUES ('119', '小水', '女', '19', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('122', '大图', '男', '19', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('123', '张流', '女', '20', '化学', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('124', '小内聚', '男', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('125', '小水第', '女', '19', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('126', '沙瑞金', '女', '19', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('129', '小妮', '男', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('222', '大蓝', '女', '21', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('333', '小蓝', '女', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('444', '大紫', '女', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('555', '大白', '女', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('666', '小黄', '男', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('678', '小黑', '男', '21', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('789', '小白菜', '男', '22', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('888', '小青', '女', '18', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('999', '大青', '男', '21', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('41612161', '沙瑞金', '女', '19', '软件工程', '2016', '15029903277', '陕师大长安校区', null);
INSERT INTO `student` VALUES ('41612196', '彭卫杰', '男 ', '21', '软件工程', '2016', '15029903277', '陕师大长安校区', '/images/upload/pic/student/1477717376126+-1870525713.jpg');
INSERT INTO `student` VALUES ('41612198', '张铖', '男', '21', '软件工程', '2016', '15478786547', '陕师大周园', null);
INSERT INTO `student` VALUES ('41612232', '汪鹏', '男', '23', '软件工程', '2016', '15787459654', '陕师大周园', null);
INSERT INTO `student` VALUES ('41612263', '张靖漪', '女', '20', '软件工程', '2016', '15478786547', '陕师大唐园', null);
INSERT INTO `student` VALUES ('416121122', '小黄', '男', '20', '软件工程', '2016', '15029903277', '陕师大长安校区', null);

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class` (
  `studentId` int(20) NOT NULL,
  `studentName` varchar(255) DEFAULT NULL,
  `classId` int(11) NOT NULL,
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentId`,`classId`),
  KEY `classId` (`classId`) USING BTREE,
  CONSTRAINT `sclass_classId` FOREIGN KEY (`classId`) REFERENCES `class` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sclass_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES ('119', '小水', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('122', '大图', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('124', '小内聚', '1602', '软工一班');
INSERT INTO `student_class` VALUES ('125', '小水第', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('126', '沙瑞金', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('222', '大蓝', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('333', '小蓝', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('444', '大紫', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('555', '大白', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('666', '小黄', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('678', '小黑', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('789', '小明', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('888', '小青', '1601', '软工一班');
INSERT INTO `student_class` VALUES ('999', '大青', '1602', '软工一班');
INSERT INTO `student_class` VALUES ('41612161', '沙瑞金', '1603', '信管');
INSERT INTO `student_class` VALUES ('41612196', '彭卫杰', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('41612198', '张铖', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('41612232', '汪鹏', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('41612263', '张靖漪', '1602', '软工二班');
INSERT INTO `student_class` VALUES ('416121122', '小黄', '1603', '信管');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `professionalTitle` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '雷军', '男', '计算机科学学院', '讲师', '博士', null);
INSERT INTO `teacher` VALUES ('2', '任正非', '男', '计算机科学学院', '教授', '博士', null);
INSERT INTO `teacher` VALUES ('3', '马化腾', '男', '计算机', '副教授', '本科', null);
INSERT INTO `teacher` VALUES ('123', '张大炮', '男', '计算机科学学院', '讲师', '博士', '/images/upload/pic/teacher/IMG_20160706_190429.jpg');
INSERT INTO `teacher` VALUES ('456', '火腿肠', '男', '计算机科学学院', '讲师', '博士', null);

-- ----------------------------
-- Table structure for teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(255) DEFAULT NULL,
  `classId` int(11) NOT NULL,
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`,`classId`),
  KEY `tclass_classId` (`classId`),
  CONSTRAINT `tclass_classId` FOREIGN KEY (`classId`) REFERENCES `class` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tclass_teacherId` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_class
-- ----------------------------
INSERT INTO `teacher_class` VALUES ('1', '雷军', '1602', '软工二班');
INSERT INTO `teacher_class` VALUES ('1', '雷军', '1603', '信管');
INSERT INTO `teacher_class` VALUES ('1', '雷军', '1604', '计创');
INSERT INTO `teacher_class` VALUES ('2', '任正非', '1601', '软工一班');
INSERT INTO `teacher_class` VALUES ('2', '任正非', '1605', '计科一班');
INSERT INTO `teacher_class` VALUES ('123', '张大炮', '1601', '软工一班');
INSERT INTO `teacher_class` VALUES ('123', '张大炮', '1602', '软工二班');
INSERT INTO `teacher_class` VALUES ('456', '火腿肠', '1602', '软工二班');
INSERT INTO `teacher_class` VALUES ('456', '火腿肠', '1606', '计科二班');

-- ----------------------------
-- Table structure for teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(255) DEFAULT NULL,
  `courseId` int(11) NOT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`,`courseId`),
  KEY `tc_courseId` (`courseId`),
  CONSTRAINT `tc_courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tc_teacherId` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_course
-- ----------------------------
INSERT INTO `teacher_course` VALUES ('1', '雷军', '2', '高级英语');
INSERT INTO `teacher_course` VALUES ('1', '雷军', '5', '大学外语');
INSERT INTO `teacher_course` VALUES ('1', '雷军', '6', '概率论');
INSERT INTO `teacher_course` VALUES ('2', '任正非', '3', '高数');
INSERT INTO `teacher_course` VALUES ('2', '任正非', '5', '大学外语');
INSERT INTO `teacher_course` VALUES ('3', '马化腾', '6', '概率论');
INSERT INTO `teacher_course` VALUES ('123', '张大炮', '1', '数学');
INSERT INTO `teacher_course` VALUES ('123', '张大炮', '2', '高级英语');
INSERT INTO `teacher_course` VALUES ('123', '张大炮', '3', '高数');
INSERT INTO `teacher_course` VALUES ('123', '张大炮', '4', '英语');
INSERT INTO `teacher_course` VALUES ('456', '火腿肠', '6', '概率论');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `superuser` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('000', '000', '3');
INSERT INTO `user` VALUES ('123', '123', '2');
INSERT INTO `user` VALUES ('1234', '1', '1');
INSERT INTO `user` VALUES ('41612196', '123', '1');
INSERT INTO `user` VALUES ('41612198', '123', '1');
INSERT INTO `user` VALUES ('41612232', '123', '1');
INSERT INTO `user` VALUES ('41612263', '123', '1');
INSERT INTO `user` VALUES ('444', '444', '1');
