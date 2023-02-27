package org.ashe.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * 分页dto 默认第1页，展示10条数据
 *
 * @author 王富贵
 * @since 2022-04-13 14:18:29
 */
@Setter
@Getter
public class PageDTO {

    /**
     * 页面大小
     */
    @Min(value = 1, message = "页面最小为1")
    @NotNull(message = "页面大小(要么选择传，要么不传，不能传null)")
    private Integer size = 10;

    /**
     * 第几页
     */
    @Min(value = 1, message = "页码最小为1")
    @NotNull(message = "第几页(要么选择传，要么不传，不能传nul)")
    private Integer current = 1;
}
