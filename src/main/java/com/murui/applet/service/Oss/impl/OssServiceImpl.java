package com.murui.applet.service.Oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.murui.applet.common.BaseErrorCode;
import com.murui.applet.exception.ServiceException;
import com.murui.applet.service.Oss.OssService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
public class OssServiceImpl implements OssService {

    @Value("${oss.endpoint}")
    public String endpoint;

    @Value("${oss.bucketName}")
    public String bucketName;

    @Value("${oss.accessId}")
    public String accessId;

    @Value("${oss.secret}")
    public String secret;

    @Value("${oss.dir}")
    public String dir;

    @Override
    public void uploadString(){
        // 创建 OSS 实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, secret);
        try {
            // 填写字符串。
            String content = "test oss";
            // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
            String objectName = "test.txt";
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

            // 上传字符串。
            ossClient.putObject(putObjectRequest);
        } catch (Exception  e) {
            throw new ServiceException(BaseErrorCode.OSS_UPLOAD_FAIL);
        }finally {
            if (ossClient != null)
                ossClient.shutdown();
        }
    }

    /**
     * 网页上传文件
     *
     * @param file
     * @return java.lang.String
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 11:16 AM 7/4/2022
     **/
    @Override
    public String uploadMultipartFile(MultipartFile file){
        //创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, secret);

        System.out.println(dir);
        String url = null;
        try {
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            System.out.println(fileName);
            //保证文件名唯一
            String uuid = UUID.randomUUID().toString();
            fileName = dir + uuid + fileName;

            ossClient.putObject(bucketName, fileName, inputStream);

            // 把上传后把文件url返回
            // https://xppll.oss-cn-beijing.aliyuncs.com/01.jpg
            url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception  e) {
            throw new ServiceException(BaseErrorCode.OSS_UPLOAD_FAIL);
        } finally {
            //关闭OSSClient
            if (ossClient != null)
                ossClient.shutdown();
        }
    }

    /**
     * 生成临时访问路径的签名 url
     *
     * @param filePath 访问文件相对 bucket 的位置，如 test/tifa.png
     * @return
     */
    @Override
    public String getUrl(String filePath) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, secret);
        try {
            // 设置URL过期时间为1小时
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            GeneratePresignedUrlRequest generatePresignedUrlRequest =new GeneratePresignedUrlRequest(bucketName, filePath);
            generatePresignedUrlRequest.setExpiration(expiration);
            // 生成 url
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        }
        catch (Exception  e) {
            throw new ServiceException(BaseErrorCode.OSS_UPLOAD_FAIL);}
        finally {
            if (ossClient != null)
                ossClient.shutdown();
        }
    }

    /**
     * 返回 policy 和 签名
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 7:01 PM 7/5/2022
     **/
    @Override
    public Map<String, String> getPolicy(){
        // 开启OSS客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, secret);
        try {
            long expireTime = 3600;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            // 根据到期时间生成policy
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            // 对policy进行UTF-8编码后转base64
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            // 生成signature
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> resultMap = new LinkedHashMap<String, String>();
            resultMap.put("accessid", accessId);
            resultMap.put("policy", encodedPolicy);
            resultMap.put("signature", postSignature);
            resultMap.put("dir", dir);
            resultMap.put("host", "https://" + bucketName + "." + endpoint);
            resultMap.put("expire", String.valueOf(expireEndTime / 1000));
            return resultMap;
        }
        catch (Exception  e) {
            throw new ServiceException(BaseErrorCode.OSS_UPLOAD_FAIL);
        } finally {
            if (ossClient != null)
                ossClient.shutdown();
        }
    }

}
