package com.acme.store.persist.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "store_seasons")
@AuditTable(value = "store_seasons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreSeason {

  @Id
  @Column(name = "store_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
  private Store store;

  @Column(name = "season")
  private String season;

}
