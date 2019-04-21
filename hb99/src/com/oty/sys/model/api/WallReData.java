package com.oty.sys.model.api;
 
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal; 

/**
 * 商家余额记录 
 *
 */
@ApiModel(value="商家余额记录",description="商家余额记录")
public class WallReData implements Serializable{
	
    private static final long serialVersionUID = 1L; 

    /**
     * 本月支出
     */
	@Getter @Setter
    private BigDecimal thisExpenditure;
    /**
     * 上月支出
     */
	@Getter @Setter
    private BigDecimal lastExpenditure;
 
}
