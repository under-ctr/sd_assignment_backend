package com.example.project.entitys;


import lombok.*;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
@Data
@Table(name = "Client")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "Role", nullable = false)
    private String role;

   /* @OneToOne(mappedBy = "client")
    private Device device;*/









}
