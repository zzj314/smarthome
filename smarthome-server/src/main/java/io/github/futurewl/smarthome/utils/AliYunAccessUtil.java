package io.github.futurewl.smarthome.utils;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云 权限工具
 *
 * @author weilai
 */
@Slf4j
public final class AliYunAccessUtil {

    /**
     * 获得阿里云账户响应对象
     *
     * @param acsRequest 阿里云请求对象
     * @return 响应对象
     */
    public static AcsResponse getAcsResponse(AcsRequest acsRequest) {
        try {
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI9bFla9gmeY9G", "eo5jtczEOEfPAh6rNDgkGoz3dPhqBn");
            return new DefaultAcsClient(profile).getAcsResponse(acsRequest);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
