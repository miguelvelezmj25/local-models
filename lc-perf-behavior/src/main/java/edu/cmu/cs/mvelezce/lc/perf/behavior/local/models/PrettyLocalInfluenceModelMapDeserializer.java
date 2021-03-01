package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrettyLocalInfluenceModelMapDeserializer
    extends JsonDeserializer<Map<Set<String>, String>> {

  private static final Pattern CONFIG_PATTERN = Pattern.compile("\\[(.*?)\\]");

  @Override
  public Map<Set<String>, String> deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
    JsonNode node = mapper.readTree(jsonParser);
    Iterator<Map.Entry<String, JsonNode>> iter = node.fields();
    Map<Set<String>, String> result = new HashMap<>();
    while (iter.hasNext()) {
      Map.Entry<String, JsonNode> entry = iter.next();
      result.put(this.toConfig(entry.getKey()), entry.getValue().textValue());
    }
    return result;
  }

  private Set<String> toConfig(String configString) {
    Matcher matcher = CONFIG_PATTERN.matcher(configString);
    if (!matcher.find()) {
      throw new RuntimeException("Could not match config");
    }
    String[] selectedOptions = matcher.group(1).split(",");
    Set<String> config = new HashSet<>();
    for (String selectedOption : selectedOptions) {
      config.add(selectedOption.trim());
    }
    return config;
  }
}
