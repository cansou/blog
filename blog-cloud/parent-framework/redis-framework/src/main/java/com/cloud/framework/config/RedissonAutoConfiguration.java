package com.cloud.framework.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonAutoConfiguration {

	/**
	 * 集群模式
	 * @return
	 * @throws IOException
	 */
	@Bean("clusterServersRedissonClient")
	@ConditionalOnMissingBean
	@ConditionalOnResource(resources = {"classpath:redis/redisson-cluster-servers-config.yml"})
	public RedissonClient clusterServersConfig() throws IOException {
		Config config = Config.fromYAML(RedissonAutoConfiguration.class.getClassLoader().getResource("redis/redisson-cluster-servers-config.yml"));
		return Redisson.create(config);
	}


	/**
	 * 云托管模式
	 * @return
	 * @throws IOException
	 */
	@Bean("replicatedServersRedissonClient")
	@ConditionalOnMissingBean
	@ConditionalOnResource(resources = {"classpath:redis/redisson-replicated-servers-config.yml"})
	public RedissonClient replicatedServersConfig() throws IOException {
		Config config = Config.fromYAML(RedissonAutoConfiguration.class.getClassLoader().getResource("redis/redisson-replicated-servers-config.yml"));
		return Redisson.create(config);
	}


	/**
	 * 单Redis节点模式
	 * @return
	 * @throws IOException
	 */
	@Bean("singleServerRedissonClient")
	@ConditionalOnMissingBean
	@ConditionalOnResource(resources = {"classpath:redis/redisson-single-server-config.yml"})
	public RedissonClient singleServerConfig() throws IOException {
		Config config = Config.fromYAML(RedissonAutoConfiguration.class.getClassLoader().getResource("redis/redisson-single-server-config.yml"));
		return Redisson.create(config);
	}


	/**
	 * 哨兵模式
	 * @return
	 * @throws IOException
	 */
	@Bean("sentinelServersRedissonClient")
	@ConditionalOnMissingBean
	@ConditionalOnResource(resources = {"classpath:redis/redisson-sentinel-servers-config.yml"})
	public RedissonClient sentinelServersConfig() throws IOException {
		Config config = Config.fromYAML(RedissonAutoConfiguration.class.getClassLoader().getResource("redis/redisson-sentinel-servers-config.yml"));
		return Redisson.create(config);
	}


	/**
	 * 主从模式
	 * @return
	 * @throws IOException
	 */
	@Bean("masterSlaveServersRedissonClient")
	@ConditionalOnMissingBean
	@ConditionalOnResource(resources = {"classpath:redis/redisson-master-slave-servers-config.yml"})
	public RedissonClient masterSlaveServersConfig() throws IOException {
		Config config = Config.fromYAML(RedissonAutoConfiguration.class.getClassLoader().getResource("redis/redisson-master-slave-servers-config.yml"));
		return Redisson.create(config);
	}

}
