package com.camelot.im.domain;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
@Data
public class BaseDomain {

  @Id
  @GeneratedValue
  private Long id;//ID

  @CreatedDate
  private Date createdDate;
  private Date updatedDate;
  private String createdBy;
  private String updatedBy;

}
