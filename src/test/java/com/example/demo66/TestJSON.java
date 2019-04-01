package com.example.demo66;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

public class TestJSON {
    @Test
    public void jsonNode(){
        ObjectMapper om = new ObjectMapper();
       ObjectNode o = om.createObjectNode();
       o.put("a",1);
       JsonNode o1 = o;
       ((ObjectNode) o1).put("b",2);
       ((ObjectNode) o1).remove("b");
       System.out.println(o.toString());
    }
}
