<template>
    <b-card no-body>
     
    <b-row class="mt-4">
      <b-col>
        <stats-card  type="gradient-red" :sub-title="board.subject" :title="board.regtime" icon="ni ni-active-40" class="mb-4">
            <template slot="footer">
              <span class="text-nowrap">{{board.content}}</span>
            </template>
          </stats-card>
       
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button class="btn btn-outline-success-sm float-righ" variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
      </b-col>
    </b-row>

    <!-- 댓글 -->
    <comment-write :articleno="this.articleno" />
    <comment-write
      v-if="isModifyShow && this.modifyComment != null"
      :modifyComment="this.modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment v-for="(comment, index) in comments" :key="index" :comment="comment" @modify-comment="onModifyComment" />

    </b-card>
</template>
<script>

import { mapGetters } from "vuex";
import CommentWrite from "@/components/board/item/comment/CommentWrite.vue";
import Comment from "@/components/board/item/comment/Comment.vue";
import { Table, TableColumn } from 'element-ui'
import http from "@/api/http";
  export default {
    name: 'light-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      CommentWrite,
      Comment
    },

    name: "BoardList",
    computed: {
      ...mapGetters([ "comments", "board"]),
        message() {
        if (this.board.content) return this.board.content.split("\n").join("<br>");
        return "";
      },
    },
    created() {
      this.articleno = this.$route.query.articleno;
      this.$store.dispatch("getBoard", `/board/${this.articleno}`);
      this.$store.dispatch("getComments", `/comment/${this.articleno}`);
    },
    methods: {
      moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.board.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.board.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        http.delete(`/board/${this.board.articleno}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/board");
        });
      }
    },
    moveList() {
      this.$router.push("/board");
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    }
        
    },
    data() {
      return {
        boards,
        currentPage: 1,
        articleno: "",
        isModifyShow: false,
        modifyComment: Object
      };
    }
  }
</script>
