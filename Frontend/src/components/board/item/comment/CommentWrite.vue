<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <textarea id="comment" name="comment" v-model="modify_comment.comment" cols="35" rows="2"></textarea>
      <button class="small" @click="updateCommentCancel">취소</button>
      <button class="small" @click="updateComment">수정</button>
    </div>
    <div v-else class="regist_form">
      <textarea id="comment" name="comment" v-model="comment" cols="35" rows="2"></textarea>
      <button @click="registComment">등록</button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "CommentWrite",
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      userid: "admin",
      comment: "",
      modify_comment: this.modifyComment
    };
  },
  props: {
    articleno: { type: String },
    modifyComment: { type: Object }
  },
  methods: {
    registComment() {
      http
        .post("/comment/", {
          userid: this.userid,
          comment: this.comment,
          articleno: this.articleno
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);

          // 작성글 지우기
          this.comment = "";

          // 도서평(댓글) 얻기.
          this.$store.dispatch("getComments", `/comment/${this.articleno}`);
        });
    },
    updateComment() {
      http
        .put(`/comment`, {
          commentno: this.modifyComment.commentno,
          comment: this.modifyComment.comment
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);

          // 도서평(댓글) 얻기.
          this.$store.dispatch("getComments", `/comment/${this.modifyComment.articleno}`);
          this.$emit("modify-comment-cancel", false);
        });
    },
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    }
  }
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: white;
  padding: 20px;
}

textarea {
  width: 90%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button {
  float: right;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>
