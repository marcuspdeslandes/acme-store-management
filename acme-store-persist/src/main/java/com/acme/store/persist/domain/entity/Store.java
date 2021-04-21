package com.acme.store.persist.domain.entity;

import com.acme.store.persist.domain.model.enums.StoreTypeConverter;
import com.acme.store.persist.domain.model.enums.StoreTypeEnum;
import lombok.*;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "store")
@AuditTable(value = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_generator")
  @SequenceGenerator(
    name = "store_generator",
    sequenceName = "store_sequence",
  initialValue = 1000,
  allocationSize = 1)
  @Column(name = "id")
  private Long id;
  @Column(name = "code", unique = true)
  private String code;
  @Column(name = "description")
  private String description;
  @Column(name = "name")
  private String name;
  @Column(name = "openingDate")
  private Date openingDate;
  @Column(name = "storeType")
  @Convert(converter = StoreTypeConverter.class)
  private StoreTypeEnum storeType;
  @OneToOne(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private StoreSeason storeSeason;
  @OneToOne(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private ExtraData extraData;
}
