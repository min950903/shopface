package kr.ac.sunmoon.shopface.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	@Value("${cloud.aws.accessKey}")
	private String accessKey;
	
	@Value("${cloud.aws.secretKey}")
	private String secretKey;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Bean
	public BasicAWSCredentials awsCredentials() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		
		return awsCredentials;
	}
	
	@Bean
	public AmazonS3 awsS3Client() {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(this.awsCredentials()))
				.build();
		
		return s3Client;
	}
}
