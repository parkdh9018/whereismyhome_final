<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">

     
      <b-col class="text-left">
        <b-button variant="btn btn-outline-success float-righ" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${board.articleno}.
          ${board.subject}</h3><div>조회 : ${board.hit}</div><div><h6>${board.userid}</div><div>${board.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>



    
  </b-container>
</template>

<script>

// import http from "@/api/http";
import { mapGetters } from "vuex";
import CommentWrite from "@/components/board/item/comment/CommentWrite.vue";
import Comment from "@/components/board/item/comment/Comment.vue";
import http from "@/api/http";
export default {
  name: "BoardDetail",
  data() {
    return {
      articleno: "",
      isModifyShow: false,
      modifyComment: Object
    };
  },
  computed: {
    // ...mapGetters("board",["comments","board"]),
    message() {
      if (this.board.content) return this.board.content.split("\n").join("<br>");
      return "";
    },

  },
  created() {
    this.articleno = this.$route.query.articleno;
    this.$store.dispatch("getBoard", `/board/${this.articleno}`);
    this.$store.dispatch("getComment", `/comment/${this.articleno}`);

    // http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
    //   this.article = data;
    // });
  },
  components: {
    CommentWrite,
    Comment
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
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
