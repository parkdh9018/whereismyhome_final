<template>
  <b-card no-body>
    <b-container class="bv-example-row mt-3">
  <b-row class="mb-1">
    <b-col class="text-left">
      <b-button variant="outline-primary" @click="moveList">목록</b-button>
    </b-col>
    <b-col class="text-right">
      <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
      <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
    </b-col>
  </b-row>
  <b-row class="mb-1">
    <b-col>
      <b-card
        :header-html="`<h3>${article.articleno}.
        ${article.subject}</h3><div>조회 : ${article.hit}</div><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
        class="mb-2"
        border-variant="dark"
        no-body
      >
      </b-card>
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

  
</b-container>

  </b-card>
</template>
<script>

// import http from "@/api/http";

import CommentWrite from "@/components/board/item/comment/CommentWrite.vue";
import Comment from "@/components/board/item/comment/Comment.vue";
import { getArticle,getComments ,deleteArticle} from "@/api/boardApi";

export default {
name: "BoardDetail",
data() {
  return {
    articleno: "",
    isModifyShow: false,
    modifyComment: Object,
    article: {},
    comments: {}
  };
},

created() {
  let param = this.$route.query.articleno;
  getArticle(
    param,
    ({ data }) => {
      this.article = data;
    },
    (error) => {
      console.log(error);
    }
  );
  getComments(
    param,
    ({ data }) => {
      this.comments = data;
    },
    (error) => {
      console.log(error);
    }
  );
},
components: {
  CommentWrite,
  Comment
},
methods: {
  moveModifyArticle() {
    this.$router.replace({
      name: "boardmodify",
      params: { articleno: this.article.articleno },
    });
    //   this.$router.push({ path: `/board/modify/${this.board.articleno}` });
  },
  deleteArticle() {
    if (confirm("정말로 삭제?")) {
      let param = this.$route.query.articleno;
      deleteArticle(
        param,
        ({ }) => {
          this.$router.push("/tables");
        },
        (error) => {
          console.log(error);
        }
      );
      }
  },
  moveList() {
    this.$router.push("/tables");
  },
  onModifyComment(comment) {
    this.modifyComment = comment;
    this.isModifyShow = true;
  },
  onModifyCommentCancel(isShow) {
    this.isModifyShow = isShow;
  }
},
// filters: {
//   dateFormat(regtime) {
//     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
//   },
// },
};
</script>

<style></style>
