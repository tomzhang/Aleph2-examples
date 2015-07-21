/******************************************************************************
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
 ******************************************************************************/
package com.ikanow.aleph2.example.flume_harvester.data_model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Per bucket configuration
 * @author alex
 */
public class FlumeBucketConfigBean {

	public String substitution_prefix() { return substitution_prefix; }
	public Map<String, String> flume_config() { return flume_config; }
	public Map<String, Object> morphlines_config() { return morphlines_config; }
	public String flume_config_str() { return flume_config_str; }
	public String morphlines_config_str() { return morphlines_config_str; }
	
	public static class OutputConfig {
		public static class JsonConfig {
			public JsonPolicy json_policy() { return Optional.ofNullable(json_policy).orElse(JsonPolicy.body_plus_headers); }
			public String include_body_with_name() { return include_body_with_name; }
			public String add_time_with_name() { return add_time_with_name; }	
			
			enum JsonPolicy { body, body_plus_headers, event, event_no_body };
			private JsonPolicy json_policy;
			private String include_body_with_name;
			private String add_time_with_name;
		}
		public static class CsvConfig {
			public String separator() { return Optional.ofNullable(separator).orElse(","); }
			public List<String> header_fields() { return header_fields; }
			public String ignore_regex() { return ignore_regex; }
			public String escape_char() { return Optional.ofNullable(escape_char).orElse("\\"); }
			public String quote_char() { return Optional.ofNullable(quote_char).orElse("\""); }
			
			private String separator;
			private List<String> header_fields;
			private String ignore_regex;
			private String escape_char;
			private String quote_char;
		}
		public OutputConfig output() { return output; }
		public JsonConfig json() { return json; }
		public CsvConfig csv() { return csv; }
		
		private OutputConfig output;
		private JsonConfig json;
		private CsvConfig csv;
	}
	
	private String substitution_prefix;
	private Map<String, String> flume_config;
	private Map<String, Object> morphlines_config;	
	private String flume_config_str;
	private String morphlines_config_str;
}
