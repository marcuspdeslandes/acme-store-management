package com.acme.store.persist.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;

@Entity(name = "extra_data")
@AuditTable(value = "extra_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtraData {

  @Id
  @Column(name = "store_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
  private Store store;

  @Column(name = "special_field_1")
  private String specialField1;

  @Column(name = "special_field_2")
  private String specialField2;
}
