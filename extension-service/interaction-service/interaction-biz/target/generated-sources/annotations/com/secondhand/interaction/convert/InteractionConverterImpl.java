package com.secondhand.interaction.convert;

import com.secondhand.interaction.dto.admin.PostAdminRespDTO;
import com.secondhand.interaction.dto.req.CommentPublishReqDTO;
import com.secondhand.interaction.dto.req.LikeReqDTO;
import com.secondhand.interaction.dto.req.PostPublishReqDTO;
import com.secondhand.interaction.dto.resp.CommentRespDTO;
import com.secondhand.interaction.dto.resp.PostRespDTO;
import com.secondhand.interaction.dto.resp.PostSyncEsDTO;
import com.secondhand.interaction.pojo.domain.Comment;
import com.secondhand.interaction.pojo.domain.LikeRecord;
import com.secondhand.interaction.pojo.domain.Post;
import com.secondhand.user.dto.UserInfoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T12:03:10+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class InteractionConverterImpl implements InteractionConverter {

    @Override
    public LikeRecord toLikeRecord(LikeReqDTO reqDTO, Long userId) {
        if ( reqDTO == null && userId == null ) {
            return null;
        }

        LikeRecord likeRecord = new LikeRecord();

        if ( reqDTO != null ) {
            likeRecord.setBizId( reqDTO.getBizId() );
            likeRecord.setBizType( mapBizType( reqDTO.getBizType() ) );
        }
        likeRecord.setUserId( userId );

        return likeRecord;
    }

    @Override
    public Post toPost(PostPublishReqDTO reqDTO, Long userId) {
        if ( reqDTO == null && userId == null ) {
            return null;
        }

        Post post = new Post();

        if ( reqDTO != null ) {
            post.setContent( reqDTO.getContent() );
            List<String> list = reqDTO.getImages();
            if ( list != null ) {
                post.setImages( new ArrayList<String>( list ) );
            }
            post.setRelateSpuId( reqDTO.getRelateSpuId() );
            post.setTitle( reqDTO.getTitle() );
        }
        post.setUserId( userId );
        post.setStatus( com.secondhand.interaction.enums.PostStatusEnum.NORMAL );
        post.setViewCount( 0 );
        post.setLikeCount( 0 );
        post.setCommentCount( 0 );

        return post;
    }

    @Override
    public Comment toComment(CommentPublishReqDTO reqDTO, Long userId) {
        if ( reqDTO == null && userId == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( reqDTO != null ) {
            comment.setBizId( reqDTO.getBizId() );
            comment.setBizType( mapBizType( reqDTO.getBizType() ) );
            comment.setContent( reqDTO.getContent() );
            comment.setParentId( reqDTO.getParentId() );
            comment.setReplyToUserId( reqDTO.getReplyToUserId() );
        }
        comment.setUserId( userId );
        comment.setLikeCount( 0 );

        return comment;
    }

    @Override
    public PostRespDTO toPostRespDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostRespDTO postRespDTO = new PostRespDTO();

        postRespDTO.setId( post.getId() );
        postRespDTO.setUserId( post.getUserId() );
        postRespDTO.setTitle( post.getTitle() );
        postRespDTO.setContent( post.getContent() );
        postRespDTO.setImages( post.getImages() );
        postRespDTO.setViewCount( post.getViewCount() );
        postRespDTO.setLikeCount( post.getLikeCount() );
        postRespDTO.setCommentCount( post.getCommentCount() );
        postRespDTO.setCreateTime( post.getCreateTime() );

        return postRespDTO;
    }

    @Override
    public CommentRespDTO toCommentRespDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentRespDTO commentRespDTO = new CommentRespDTO();

        commentRespDTO.setId( comment.getId() );
        commentRespDTO.setBizType( comment.getBizType() );
        commentRespDTO.setBizId( comment.getBizId() );
        commentRespDTO.setParentId( comment.getParentId() );
        commentRespDTO.setUserId( comment.getUserId() );
        commentRespDTO.setReplyToUserId( comment.getReplyToUserId() );
        commentRespDTO.setContent( comment.getContent() );
        commentRespDTO.setLikeCount( comment.getLikeCount() );
        commentRespDTO.setCreateTime( comment.getCreateTime() );

        return commentRespDTO;
    }

    @Override
    public PostAdminRespDTO toPostAdminRespDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostAdminRespDTO postAdminRespDTO = new PostAdminRespDTO();

        postAdminRespDTO.setId( post.getId() );
        postAdminRespDTO.setUserId( post.getUserId() );
        postAdminRespDTO.setTitle( post.getTitle() );
        postAdminRespDTO.setContent( post.getContent() );
        postAdminRespDTO.setImages( post.getImages() );
        postAdminRespDTO.setViewCount( post.getViewCount() );
        postAdminRespDTO.setLikeCount( post.getLikeCount() );
        postAdminRespDTO.setCommentCount( post.getCommentCount() );
        postAdminRespDTO.setCreateTime( post.getCreateTime() );

        postAdminRespDTO.setStatus( post.getStatus() != null ? post.getStatus().getCode() : null );

        return postAdminRespDTO;
    }

    @Override
    public List<PostRespDTO> toPostRespList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostRespDTO> list = new ArrayList<PostRespDTO>( posts.size() );
        for ( Post post : posts ) {
            list.add( toPostRespDTO( post ) );
        }

        return list;
    }

    @Override
    public List<PostAdminRespDTO> toPostAdminRespDTOList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostAdminRespDTO> list = new ArrayList<PostAdminRespDTO>( posts.size() );
        for ( Post post : posts ) {
            list.add( toPostAdminRespDTO( post ) );
        }

        return list;
    }

    @Override
    public List<CommentRespDTO> toCommentRespList(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentRespDTO> list = new ArrayList<CommentRespDTO>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toCommentRespDTO( comment ) );
        }

        return list;
    }

    @Override
    public PostSyncEsDTO toPostSyncEsDTO(Post post, UserInfoDTO user) {
        if ( post == null && user == null ) {
            return null;
        }

        PostSyncEsDTO postSyncEsDTO = new PostSyncEsDTO();

        if ( post != null ) {
            postSyncEsDTO.setId( post.getId() );
            postSyncEsDTO.setUserId( post.getUserId() );
            postSyncEsDTO.setCreateTime( post.getCreateTime() );
            postSyncEsDTO.setTitle( post.getTitle() );
            postSyncEsDTO.setContent( post.getContent() );
            List<String> list = post.getImages();
            if ( list != null ) {
                postSyncEsDTO.setImages( new ArrayList<String>( list ) );
            }
            postSyncEsDTO.setRelateSpuId( post.getRelateSpuId() );
            postSyncEsDTO.setViewCount( post.getViewCount() );
            postSyncEsDTO.setLikeCount( post.getLikeCount() );
            postSyncEsDTO.setCommentCount( post.getCommentCount() );
        }
        if ( user != null ) {
            if ( user.getNickname() != null ) {
                postSyncEsDTO.setNickname( user.getNickname() );
            }
            else {
                postSyncEsDTO.setNickname( "已注销用户" );
            }
            postSyncEsDTO.setAvatar( user.getAvatar() );
        }
        postSyncEsDTO.setStatus( post.getStatus() != null ? post.getStatus().getCode() : null );

        return postSyncEsDTO;
    }
}
