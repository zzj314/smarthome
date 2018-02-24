package io.github.futurewl.smarthome.dto;

import lombok.Data;

/**
 * 返回结果对象
 *
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/20
 * Time: 上午11:35
 * ==========================
 */
@Data
public class ResultInfo {

    private Integer code;
    private String message;
    private Object object;

}
