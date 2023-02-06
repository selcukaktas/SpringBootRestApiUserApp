package com.aktas.selcuk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="KULLANICI")
@Data // lombok kütüphanesinden setter getter tostring vb. tüm özellikleri oto ekliyor. istenirse ayrı ayrı @getter @setter verilebiliyor.
//@Setter @Getter
public class User1 extends BaseEntity {
    @Id
    @SequenceGenerator(name="user_seq_gen", sequenceName = "user_gen", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq_gen")
    @Column(name = "ID")
    private Long id;
    @Column(name = "ADI",length = 100)
    private String firstName;
    @Column(name = "SOYADI",length = 100)
    private String lastName;

}
