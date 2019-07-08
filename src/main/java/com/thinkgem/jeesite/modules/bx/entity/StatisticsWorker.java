package com.thinkgem.jeesite.modules.bx.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class StatisticsWorker extends DataEntity<StatisticsWorker> {
    private String name;
    private String workType;
    private String finishi;
    private String unfinish;
    private String allmession;
    @ExcelField(align=1,title="等级",sort=6)
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    private String rank;

    @ExcelField(align=1,title="工人类型",sort=2)
    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
    @ExcelField(align=1,title="工人姓名",sort=1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(align=1,title="已完成",sort=3)
    public String getFinishi() {
        return finishi;
    }

    public void setFinishi(String finishi) {
        this.finishi = finishi;
    }
    @ExcelField(align=1,title="未完成",sort=4)
    public String getUnfinish() {
        return unfinish;
    }

    public void setUnfinish(String unfinish) {
        this.unfinish = unfinish;
    }
    @ExcelField(align=1,title="任务总和",sort=5)
    public String getAllmession() {
        return allmession;
    }

    public void setAllmession(String allmession) {
        this.allmession = allmession;
    }
}
