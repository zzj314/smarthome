package io.github.futurewl.smarthome.utils;

import com.aliyuncs.iot.model.v20170420.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 阿里云 IOT 套件 API 接口工具
 *
 * @author weilai
 */
@Slf4j
public final class AliYunIOTUtil {

    /**
     * 创建产品响应
     *
     * @param productName 产品名称
     * @param productDesc 产品描述
     * @return 创建产品响应对象
     */
    public static CreateProductResponse getCreateProductResponse(String productName, String productDesc) {
        CreateProductRequest request = new CreateProductRequest();
        request.setName(productName);
        if (!"".equals(productDesc)) {
            request.setDesc(productDesc);
        }
        CreateProductResponse createProductResponse = (CreateProductResponse) AliYunAccessUtil.getAcsResponse(request);
        assert createProductResponse != null;
        return createProductResponse;
    }

    /**
     * 更新产品响应方法
     *
     * @param productKey  产品信息秘钥对象
     * @param productName 产品信息名词对象
     * @param productDesc 产品信息描述对象
     * @return 更新产品响应对象
     */
    public static UpdateProductResponse getUpdateProductResponse(String productKey, String productName, String productDesc) {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductKey(productKey);
        request.setProductName(productName);
        request.setProductDesc(productDesc);
        UpdateProductResponse updateProductResponse = (UpdateProductResponse) AliYunAccessUtil.getAcsResponse(request);
        assert updateProductResponse != null;
        return updateProductResponse;
    }

    /**
     * 查询设备响应方法
     *
     * @param productKey 产品 ID
     * @return 查询产品响应
     */
    public static QueryDeviceResponse getQueryDeviceResponse(String productKey, Integer pageSize, Integer currentPage) {
        QueryDeviceRequest queryDeviceRequest = new QueryDeviceRequest();
        queryDeviceRequest.setCurrentPage(currentPage);
        queryDeviceRequest.setProductKey(productKey);
        queryDeviceRequest.setPageSize(pageSize);
        QueryDeviceResponse queryDeviceResponse = (QueryDeviceResponse) AliYunAccessUtil.getAcsResponse(queryDeviceRequest);
        assert queryDeviceResponse != null;
        return queryDeviceResponse;
    }

    /**
     * 通过设备名称查询设备信息方法
     *
     * @param productKey 产品Id
     * @param deviceName 设备名称
     * @return 通过设备名称查询设备信息对象
     */
    public static QueryDeviceByNameResponse getQueryDeviceByNameResponse(String productKey, String deviceName) {
        QueryDeviceByNameRequest request = new QueryDeviceByNameRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        QueryDeviceByNameResponse response = (QueryDeviceByNameResponse) AliYunAccessUtil.getAcsResponse(request);
        assert response != null;
        return response;
    }

    /**
     * 注册设备响应
     *
     * @param productKey 产品ID
     * @return 注册设备响应
     */
    public static RegistDeviceResponse getRegistryDeviceResponse(String productKey) {
        RegistDeviceRequest request = new RegistDeviceRequest();
        request.setProductKey(productKey);
        RegistDeviceResponse registDeviceResponse = (RegistDeviceResponse) AliYunAccessUtil.getAcsResponse(request);
        assert registDeviceResponse != null;
        return registDeviceResponse;
    }

    /**
     * 获取发布消息响应对象方法
     *
     * @param productKey     产品ID
     * @param messageContent 信息内容
     * @param topicFullName  主题全名
     * @param qos            qos
     * @return 获取发布消息响应对象
     */
    public static PubResponse getPubResponse(String productKey, String messageContent, String topicFullName, Integer qos) {
        PubRequest pub = new PubRequest();
        pub.setProductKey(productKey);
        pub.setTopicFullName(topicFullName);
        pub.setMessageContent(Base64Util.encode(messageContent, "utf-8"));
        pub.setQos(qos);
        PubResponse pubResponse = (PubResponse) AliYunAccessUtil.getAcsResponse(pub);
        assert pubResponse != null;
        return pubResponse;
    }

    public static void main(String[] args) {
        AliYunIOTUtil.getPubResponse("Yy2T9M7mKHJ", "Y", "/Yy2T9M7mKHJ/test1/get", 1);
    }

    /**
     * 获取Rrpc 响应对象
     *
     * @param productKey        产品ID
     * @param deviceName        设备名称
     * @param requestBase64Byte 消息内容
     * @param timeout           超时时间
     * @return 响应对象
     */
    public static RRpcResponse getRrpcResponse(String productKey, String deviceName, String requestBase64Byte, Integer timeout) {
        RRpcRequest rrpcRequest = new RRpcRequest();

        rrpcRequest.setProductKey(productKey);
        rrpcRequest.setDeviceName(deviceName);
        rrpcRequest.setRequestBase64Byte(Base64Util.encode(requestBase64Byte, "utf-8"));
        rrpcRequest.setTimeout(timeout);

        RRpcResponse rrpcResponse = (RRpcResponse) AliYunAccessUtil.getAcsResponse(rrpcRequest);

        assert rrpcResponse != null;
        return rrpcResponse;
    }

    /**
     * 批量获取设备状态响应
     *
     * @param productKey 产品ID
     * @param devices    设备列表
     * @return 批量获取设备状态响应
     */
    public static BatchGetDeviceStateResponse getBatchGetDeviceStateResponse(String productKey, DeviceNameList devices) {
        BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();
        request.setProductKey(productKey);
        request.setDeviceNames(devices.getNameList());
        BatchGetDeviceStateResponse response = (BatchGetDeviceStateResponse) AliYunAccessUtil.getAcsResponse(request);
        assert response != null;
        return response;
    }

    /**
     * 查询批量插入设备信息请求
     *
     * @param productKey 产品ID
     * @param deviceList 设备列表
     * @return 查询批量插入设备信息请求
     */
    public static ApplyDeviceWithNamesResponse getApplyDeviceWithNamesResponse(String productKey, List<String> deviceList) {
        // 生成写入物联网套件请求
        ApplyDeviceWithNamesRequest request = new ApplyDeviceWithNamesRequest();
        // 拼接写入参数
        request.setProductKey(productKey);
        request.setDeviceNames(deviceList);
        // 生成写入物联网套件响应
        ApplyDeviceWithNamesResponse applyDeviceWithNamesResponse = (ApplyDeviceWithNamesResponse) AliYunAccessUtil.getAcsResponse(request);
        assert applyDeviceWithNamesResponse != null;
        return applyDeviceWithNamesResponse;
    }

    /**
     * 批量插入设备状态响应
     *
     * @param applyId 申请ID
     * @return 批量插入设备状态响应
     */
    public static QueryApplyStatusResponse getApplyStatusResponse(Long applyId) {
        QueryApplyStatusRequest queryApplyStatusRequest = new QueryApplyStatusRequest();
        queryApplyStatusRequest.setApplyId(applyId);
        QueryApplyStatusResponse queryApplyStatusResponse = (QueryApplyStatusResponse) AliYunAccessUtil.getAcsResponse(queryApplyStatusRequest);
        assert queryApplyStatusResponse != null;
        return queryApplyStatusResponse;
    }

    /**
     * 查询批量生成的设备信息
     *
     * @param applyId     申请ID
     * @param currentPage 设备列表显示页面当前页数
     * @param size        设备列表显示页面大小
     * @return 查询批量生成的设备信息
     */
    public static QueryPageByApplyIdResponse getQueryPageByApplyIdResponse(Long applyId, int currentPage, int size) {
        QueryPageByApplyIdRequest queryPageByApplyIdRequest = new QueryPageByApplyIdRequest();
        queryPageByApplyIdRequest.setApplyId(applyId);
        queryPageByApplyIdRequest.setCurrentPage(currentPage);
        queryPageByApplyIdRequest.setPageSize(size);
        QueryPageByApplyIdResponse queryPageByApplyIdResponse = (QueryPageByApplyIdResponse) AliYunAccessUtil.getAcsResponse(queryPageByApplyIdRequest);
        assert queryPageByApplyIdResponse != null;
        return queryPageByApplyIdResponse;
    }

    /**
     * 发布广播响应对象方法
     *
     * @param productKey     产品 ID
     * @param messageContent 消息内容
     * @param topicFullName  主题全名
     * @return 发布广播响应对象
     */
    public static PubBroadcastResponse getPubBroadcastResponse(String productKey, String messageContent, String topicFullName) {
        PubBroadcastRequest request = new PubBroadcastRequest();

        request.setProductKey(productKey);
        request.setMessageContent(messageContent);
        request.setTopicFullName(topicFullName);

        PubBroadcastResponse pubBroadcastResponse = (PubBroadcastResponse) AliYunAccessUtil.getAcsResponse(request);

        assert pubBroadcastResponse != null;
        return pubBroadcastResponse;
    }

    /**
     * 获取设备影子响应方法
     *
     * @param productKey 产品 ID
     * @param deviceName 设备名称
     * @return 获取设备影子响应对象
     */
    public static GetDeviceShadowResponse getGetDeviceShadowResponse(String productKey, String deviceName) {
        GetDeviceShadowRequest shadowRequest = new GetDeviceShadowRequest();

        shadowRequest.setProductKey(productKey);
        shadowRequest.setDeviceName(deviceName);

        GetDeviceShadowResponse shadowResponse = (GetDeviceShadowResponse) AliYunAccessUtil.getAcsResponse(shadowRequest);

        assert shadowResponse != null;
        return shadowResponse;
    }

    /**
     * 更新设备影子响应方法
     *
     * @param productKey    产品ID
     * @param deviceName    设备名称
     * @param shadowMessage 影子信息
     * @return 更新设备影子响应对象
     */
    public static UpdateDeviceShadowResponse getUpdateDeviceShadowResponse(String productKey, String deviceName, String shadowMessage) {
        UpdateDeviceShadowRequest shadowRequest = new UpdateDeviceShadowRequest();
        shadowRequest.setProductKey(productKey);
        shadowRequest.setDeviceName(deviceName);
        shadowRequest.setShadowMessage(shadowMessage);
        UpdateDeviceShadowResponse shadowResponse = (UpdateDeviceShadowResponse) AliYunAccessUtil.getAcsResponse(shadowRequest);
        assert shadowResponse != null;
        return shadowResponse;
    }

    @Data
    class DeviceNameList {
        private List<String> nameList;
    }
}
