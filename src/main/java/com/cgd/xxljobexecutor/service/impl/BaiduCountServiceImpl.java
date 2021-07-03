package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.SiteTrendDao;
import com.cgd.xxljobexecutor.model.DTO.VisitDTO;
import com.cgd.xxljobexecutor.model.DTO.VisitVO;
import com.cgd.xxljobexecutor.model.eCharts.*;
import com.cgd.xxljobexecutor.service.BaiduCountService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:51
 */
@Service
public class BaiduCountServiceImpl implements BaiduCountService {

    @Autowired
    private SiteTrendDao siteTrendDao;

    @Override
    public VisitVO getVisitData() {
        List<VisitDTO> list = siteTrendDao.selectVisitInfo();
        List<String> monthList = list.stream().map(VisitDTO::getMonth).sorted().collect(Collectors.toList());
        VisitVO vo = new VisitVO();
        vo.setTitle(new Title("站点趋势统计图","center","1px"));
        vo.setTooltip(new Tooltip("axis"));
        vo.setLegend(new Legend(Arrays.asList("浏览量(PV)","访问次数","访客数(UV)","新访客数","IP数"),"30px"));
        vo.setGrid(new Grid("3%","4%","3%",true));
        vo.setXAxis(new XAxis("category",false,monthList));
        vo.setYAxis(new YAxis("value"));
        List<Series<Integer>> seriesList = new ArrayList<>();
        List<Integer> pvList = list.stream().map(VisitDTO::getPvCount).collect(Collectors.toList());//浏览量(PV
        List<Integer> visitList = list.stream().map(VisitDTO::getVisitCount).collect(Collectors.toList());//访问次数
        List<Integer> uvList = list.stream().map(VisitDTO::getVisitorCount).collect(Collectors.toList());//访客数(UV)
        List<Integer> newVisitorList = list.stream().map(VisitDTO::getNewVisitorCount).collect(Collectors.toList());//新访客数
        List<Integer> ipList = list.stream().map(VisitDTO::getIpCount).collect(Collectors.toList());//IP数
        seriesList.add(new Series<Integer>("浏览量(PV)","line","总量",pvList));
        seriesList.add(new Series<Integer>("访问次数","line","总量",visitList));
        seriesList.add(new Series<Integer>("访客数(UV)","line","总量",uvList));
        seriesList.add(new Series<Integer>("新访客数","line","总量",newVisitorList));
        seriesList.add(new Series<Integer>("IP数","line","总量",ipList));
        Feature feature = new Feature(new MagicType(true,Arrays.asList("line","bar","stack")),new SaveAsImage("png"),new Restore(true));
        vo.setToolbox(new Toolbox(feature));
        vo.setSeries(seriesList);
        return vo;
    }
}
