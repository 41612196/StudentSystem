package main.service;

import main.vo.Score;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public interface StudentService {
    List<Score> queryScore(int studentId);
}
