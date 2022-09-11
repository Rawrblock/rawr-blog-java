package com.lws.rawrblogend.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lws.rawrblogend.dto.FileStorageUploadDto;
import com.lws.rawrblogend.exception.BizException;
import com.lws.rawrblogend.exception.ExceptionType;
import com.lws.rawrblogend.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

/**
 * OSS文件上传获取凭证
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("oss.region_id")
    private String regionId;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.access_key_id}")
    private String accessKeyId;

    @Value("${oss.access_key_secret}")
    private String accessKeySecret;

    @Value("${oss.role.arn}")
    private String roleArn;

    @Value(("${oss.bucket_name}"))
    private String bucketName;

    private String roleSessionName = "Rawrblock";

    @Override
    public String getFileUrl(String fileName) {
        // 从STS服务获取临时访问凭证后，您可以通过临时访问密钥和安全令牌生成OSSClient。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build("oss-cn-hangzhou.aliyuncs.com", accessKeyId, accessKeySecret);
        try {
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
            req.setExpiration(expiration);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(req);
            return url.toString();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    // 获取OSS sts凭证
    @Override
    public FileStorageUploadDto initFileUpload() {
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:rawrblock/*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        try {
            DefaultProfile.addEndpoint(regionId, "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(1800L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            // 发送请求获取sts临时token
            FileStorageUploadDto fileStorageUploadDto = new FileStorageUploadDto();
            fileStorageUploadDto.setExpiration(response.getCredentials().getExpiration());
            fileStorageUploadDto.setAccessKeyId(response.getCredentials().getAccessKeyId());
            fileStorageUploadDto.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            fileStorageUploadDto.setStsToken(response.getCredentials().getSecurityToken());
            return fileStorageUploadDto;
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BizException(ExceptionType.INNER_ERROR);
        }
    }
}