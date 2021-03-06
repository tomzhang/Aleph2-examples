/*******************************************************************************
 * Copyright 2015, The IKANOW Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.ikanow.aleph2.storm.samples.bolts;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SampleGeoIPEnrichmentBolt extends BaseRichBolt {
	private static final long serialVersionUID = -4199415469537649302L;
	private OutputCollector _collector;

	@Override
	public void execute(Tuple tuple) {
		@SuppressWarnings("unchecked")
		Map<String, Object> parsed_entry = (Map<String, Object>) tuple.getValue(0);
		if ( parsed_entry != null) {
			parsed_entry.put("ip_geo_location", getGeoLookup( (String) parsed_entry.get(SampleProxyLogEntry.R_IP)) );		
			_collector.emit(tuple, new Values(parsed_entry));			
		}
		//always ack the tuple to acknowledge we've processed it, otherwise a fail message will be reported back
		//to the spout
		_collector.ack(tuple);
	}

	private String getGeoLookup(String ip_address) {
		String geo_location = null;
		if ( ip_address != null ) {
			if ( ip_address.equals("194.28.157.31"))
				geo_location = "Pakistan";
		}
		return geo_location;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("proxy_parsed"));
	}

}
