package com.qbthon.kafkaservices.controllers;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestClass {

	public static void main(String args[]) {
		boolean zookeeper_running = false;
		CuratorFramework curatorFramework ;
		curatorFramework = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.namespace("")
				.build();


		// start connection
		curatorFramework.start();
		// wait 3 second to establish connect
		try {
			curatorFramework.blockUntilConnected(3, TimeUnit.SECONDS);
			if (curatorFramework.getZookeeperClient().isConnected()) {
				
				System.out.println("zookeeper running");
			}
			else {
				System.out.println("zookeeper not  running");
			}
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
			System.out.println("zookeeper not  running");
		}


	}
}
