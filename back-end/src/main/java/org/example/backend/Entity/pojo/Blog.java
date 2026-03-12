package org.example.backend.Entity.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Blog {
    private Integer id,authorId,watches,likes,favourites;
    private  String title,content;
    private Date updateDate;
    private short isPublic;
    public Blog(){}


    public Blog( String title,Integer authorId, Date updateDate,String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
    }


}
