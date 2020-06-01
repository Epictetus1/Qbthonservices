package com.qbthon.kafkaservices.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbthon.kafkaservices.models.MultipleChoiceQuestion;



@CrossOrigin
@RestController
public class ProducerController {

	public boolean isZookeeperUp() {

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
				zookeeper_running = true;
				return zookeeper_running;
			}
			else {
				return zookeeper_running;
			}
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
			return zookeeper_running;
		}


	}
	
	

	public boolean isKafkaServerRunning() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("connections.max.idle.ms", 10000);
		properties.put("request.timeout.ms", 5000);
		try (AdminClient client = KafkaAdminClient.create(properties))
		{
			ListTopicsResult topics = client.listTopics();
			Set<String> names = topics.names().get();
			if (names.isEmpty())
			{

			}
			return true;
		}
		catch (InterruptedException | ExecutionException e)
		{
			return false;
		}
	}
	
	public String runCommand(String command) {
		String s =  "success";
		try {
            
		    // run the Unix "ps -ef" command
	            // using the Runtime exec method:
			     
			Process p = Runtime.getRuntime().exec(command);
	            
	          
         
	            BufferedReader stdError = new BufferedReader(new 
	                 InputStreamReader(p.getErrorStream()));

	          
	            
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
	            
	            System.exit(0);
	        }
	        catch (IOException e) {
	            System.out.println("exception happened - here's what I know: ");
	            e.printStackTrace();
	            s= "exception "+e.getMessage();
	            System.exit(-1);
	        }
		return s;
	    }
	

	@RequestMapping(value = "/submittopic/{topicname}/", method = RequestMethod.POST,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> submitPractiseMcq(HttpServletRequest request,@RequestBody MultipleChoiceQuestion multipleChoiceQuestion){
		//step1 start zookepper if not running already



		//step 2 start kafka server if not running already



		//step 3 create topic if not eists already


		//step4 produce topic

		//return the response
		
		return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
