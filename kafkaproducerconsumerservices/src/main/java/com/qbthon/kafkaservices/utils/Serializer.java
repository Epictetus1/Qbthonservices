package com.qbthon.kafkaservices.utils;

import java.io.Closeable;
import java.util.Map;

public interface Serializer extends Closeable {
	  void configure(Map<String, ?> var1, boolean var2);
	  <T> byte[] serialize(String var1, T var2);
	  void close();
	}