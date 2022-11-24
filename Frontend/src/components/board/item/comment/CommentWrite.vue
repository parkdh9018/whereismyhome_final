<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <textarea id="comment" name="comment" v-model="modify_comment.comment" cols="35" rows="2"></textarea>
      <button class="small" @click="updateCommentCancel">취소</button>
      <button class="small" @click="updateComment">수정</button>
    </div>
    <div v-else>
      <b-row>
      <b-col sm="11">
        <b-form-textarea id="comment" name="comment " v-model="comment" rows="3" max-rows="8"></b-form-textarea>
      </b-col>
    <b-col sm="1">
      <b-button variant="btn btn-outline-info ml-2" size="sm" @click="registComment">등록</b-button>
    </b-col>
      </b-row>


      
  
    </div>
    <!-- <b-form-textarea id="comment" name="comment " v-model="comment" rows="3" max-rows="8"></b-form-textarea>
    <b-button variant="btn btn-outline-info ml-2" size="sm" @click="registComment">등록</b-button> -->
  </div>
</template>

<script>
import { updateComments, registComment } from "@/api/boardApi";
import { dispatch } from "d3";
import { mapGetters, mapMutations } from "vuex";
export default {
  name: "CommentWrite",
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      userid: "",
      comment: "",
      comments: {},
      modify_comment: this.modifyComment
    };
  },
 
  computed: {
    ...mapGetters("member", ["checkUserInfo"]),
  },
  props: {
    articleno: { type: String },
    modifyComment: { type: Object }
  },
  methods: {
    ...mapMutations("board",["addComments"]),
    registComment() {
      
      let param = {
        userid: this.checkUserInfo.userid,
        comment: this.comment,
        articleno: this.$route.query.articleno

      };

      console.log(this.userid);
      console.log(this.comment);
      console.log(this.$route.query.articleno);
      registComment(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.comment = "";
          // 도서평(댓글) 얻기에서 계속 오류 나고 있음.
          // this.addComments("",this.comment);
          this.$store.dispatch("board/getComments", `/comment/${this.$route.query.articleno}`);
        
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateComment() {
      let param = {
        commentno: this.modifyComment.commentno,
        comment: this.modifyComment.comment
      };
      updateComments(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$store.dispatch("board/getComments", `/comment/${this.$route.query.articleno}`);
          this.$emit("modify-comment-cancel", false);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getComment(){
      getComments(
            this.$route.query.articleno,
            ({ data }) => {
              this.comments = data;
            },
            (error) => {
              console.log(error);
            }
          );
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

/* b-button {
  float: right;
} */

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>
