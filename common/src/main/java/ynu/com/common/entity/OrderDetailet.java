package ynu.com.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderdetailet")
public class OrderDetailet {
    @TableId(type = IdType.AUTO)
    private Integer odId;
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;

    private Food food;
}
