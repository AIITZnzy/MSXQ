package org.example.backend.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.backend.Entity.pojo.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Insert("INSERT INTO tb_blogs(title,authorId,updateDate,content)VALUES(#{title},#{authorId},#{updateDate},#{content})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertNewBlog(Blog blog);

    @Select("SELECT authorId,title,substring(content,1,200) AS content FROM tb_blogs ORDER BY id DESC LIMIT 50")
    List<Blog> getBlogsOrderById();

    @Select("SELECT * FROM tb_blogs WHERE id=#{id}")
    Blog getBlogById(int id);
}
