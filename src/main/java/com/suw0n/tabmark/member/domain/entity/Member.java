package com.suw0n.tabmark.member.domain.entity;

import com.suw0n.tabmark.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "tbl_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    private String imgUrl;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Member(String name, String email, String imgUrl) {
        Objects.requireNonNull(name, "Name can not be null");
        Objects.requireNonNull(email, "Email can not be null");
        Objects.requireNonNull(imgUrl, "ImgUrl can not be null");

        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Member(Long id, String name, String email, String imgUrl) {
        Objects.requireNonNull(id, "Id can not be null");
        Objects.requireNonNull(name, "Name can not be null");
        Objects.requireNonNull(email, "Email can not be null");
        Objects.requireNonNull(imgUrl, "ImgUrl can not be null");

        this.id = id;
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public void updateOnLogin(String name, String imgUrl) {
        Objects.requireNonNull(name, "Name can not be null");
        Objects.requireNonNull(imgUrl, "ImgUrl can not be null");

        this.name = name;
        this.imgUrl = imgUrl;
    }

}