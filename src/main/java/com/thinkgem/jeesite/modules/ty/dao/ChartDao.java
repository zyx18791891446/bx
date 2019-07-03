package com.thinkgem.jeesite.modules.ty.dao;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.Chart;
import com.thinkgem.jeesite.modules.ty.entity.Expert;
/**
 * 图表DAO接口
 * @author jpd
 * @version 2019-03-09
 */
@MyBatisDao
public interface ChartDao extends CrudDao<Chart>  {

	List<Chart> expense(Chart chart);
}
