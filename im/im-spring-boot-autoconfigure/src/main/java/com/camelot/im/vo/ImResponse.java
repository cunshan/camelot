package com.camelot.im.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class ImResponse<T> implements Serializable {

  private static final long serialVersionUID = -8677679337443584141L;

  public static final String CODE_SUCCESS = "0";
  public static final String CODE_ERROR = "1";

  private String code = "0";
  private String msg;

  private T data;

}
