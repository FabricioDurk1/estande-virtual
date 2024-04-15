package com.ufc.pi.webservice.utils;

import java.util.HashMap;

public class ParamReplacer {
  public static String fillParams(String parameterizedCommand, HashMap<String, String> params){
    String filledCommand = parameterizedCommand;

    for (var param : params.entrySet()) {
      filledCommand = filledCommand.replace(":" + param.getKey(), param.getValue());
    }

    return filledCommand;
  }
}
