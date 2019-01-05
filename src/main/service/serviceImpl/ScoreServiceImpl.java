package main.service.serviceImpl;

import main.dao.ScoreDao;
import main.dao.daoImpl.ScoreDaoImpl;
import main.service.ScoreService;
import main.vo.Page;
import main.vo.Score;
import util.PageUtil;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/27
 */
public class ScoreServiceImpl implements ScoreService {
    private ScoreDao scoreDao = new ScoreDaoImpl();
    @Override
    public Page pageScores(String currentPage) throws Exception {
        int allRowsAmount =scoreDao.getAllRowsAmount();
        PageUtil pageUtil =new PageUtil();
        pageUtil.setAllRowsAmount(allRowsAmount);
        if(currentPage !=null){
            pageUtil.setCurrentPage(Integer.parseInt(currentPage));
        }
        pageUtil.calculatePage();
        List<Score> list =scoreDao.getScoreByCurrentPage(pageUtil.getCurrentPageStartRow(), pageUtil.getPageSize());
        Page page =new Page();
        page.setPrevPage(pageUtil.getPrevPage());
        page.setNextPage(pageUtil.getNextPage());
        page.setShowScores(list);
        page.setShowPageNums(pageUtil.getShowPageNums());
        page.setCurrentPage(pageUtil.getCurrentPage());
        return page;

    }
}
