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
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbthon.kafkaservices.configs.AppConfigs;
import com.qbthon.kafkaservices.models.McqSerializer;
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
	
	 public static boolean doestopicExist(String topic) { Properties properties =
			  new Properties(); properties.put("bootstrap.servers", "localhost:9092");
			  properties.put("connections.max.idle.ms", 10000);
			  properties.put("request.timeout.ms", 5000); try (AdminClient client =
			  KafkaAdminClient.create(properties)) { ListTopicsResult topics =
			  client.listTopics(); Set<String> names = topics.names().get();
			  if(!names.isEmpty() && names.contains(topic)) 
			  { return true;
			  
			  }
			  else {
				  return false;
			  }
			  
			  } catch (InterruptedException | ExecutionException e) { return false; }
			  
			  
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
	            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));

	         
		          
		            
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
	          
	            
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
	            
	            //System.exit(0);
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
	public ResponseEntity<Object> submitPractiseMcq(HttpServletRequest request,@RequestBody MultipleChoiceQuestion multipleChoiceQuestion,@PathVariable("topicname") String topicName){

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
				if(!isKafkaServerRunning()) {
					runCommand("D:\\softwares\\confluent-community-5.5.0-2.12\\confluent-5.5.0\\bin\\windows\\kafka-server-start.bat D:\\softwares\\confluent-community-5.5.0-2.12\\confluent-5.5.0\\etc\\kafka\\server.properties");
				}
				else {
					
					if(!doestopicExist(topicName)) {
						System.out.println("topic does not exist "+topicName);
						String stringCommand = "D:\\softwares\\confluent-community-5.5.0-2.12\\confluent-5.5.0\\bin\\windows\\kafka-topics.bat --create --topic "+topicName+" --replication-factor 1 --partitions 1 --bootstrap-server localhost:9092";
						System.out.println("command to create topic"+stringCommand);
						runCommand(stringCommand);
					}
					else {
						System.out.println("topic exist");
						
						Properties properties = new Properties();
				        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
				        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
				        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
				        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, McqSerializer.class);
				        //MultipleChoiceQuestion mcq = new MultipleChoiceQuestion("java", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AAA", null);
				        KafkaProducer<String, MultipleChoiceQuestion> kafkaProducer = new KafkaProducer<>(properties);
				        
				        kafkaProducer.send(new ProducerRecord<>(topicName, multipleChoiceQuestion.getSubmitter(), multipleChoiceQuestion));
				        kafkaProducer.close();
				        
				        
						
					}
				}
			}
			else {
				System.out.println("zookeeper not  running");
				runCommand("D:\\softwares\\confluent-community-5.5.0-2.12\\confluent-5.5.0\\bin\\windows\\zookeeper-server-start.bat D:\\softwares\\confluent-community-5.5.0-2.12\\confluent-5.5.0\\etc\\kafka\\zookeeper.properties");
			}
			return new ResponseEntity<>("Question submitted wait for comments", HttpStatus.OK);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
			System.out.println("zookeeper not  running");
			return new ResponseEntity<>(ignored.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


	
		
	}
}
