package com.camelot.im.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UserGroup extends BaseDomain implements Serializable {

  private static final long serialVersionUID = -5799810323985412392L;

  private String groupName;//用户分组名称

}
