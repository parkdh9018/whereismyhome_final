<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col cols="8">
        <h3 class="mb-0">Profile</h3>
      </b-col>
      <b-col cols="4" class="text-right">
        <a @click="updateProfile" class="btn btn-info">회원정보 수정</a>
      </b-col>
    </b-row>

    <b-form>
      <h6 class="heading-small text-muted mb-4">User information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="ID"
              placeholder="ID"
              v-model="user.userid"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              type="email"
              label="Email address"
              placeholder="mike@email.com"
              v-model="user.email"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="Name"
              placeholder="Name"
              v-model="user.username"
            >
            </base-input>
          </b-col>
        </b-row>
      </div>

      <!-- <hr class="my-4" /> -->
      <!-- Address -->
      <!-- <h6 class="heading-small text-muted mb-4">Contact information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col md="12">
            <base-input
              type="text"
              label="Address"
              placeholder="Home Address"
              v-model="user.address"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="City"
              placeholder="City"
              v-model="user.city"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="Country"
              placeholder="Country"
              v-model="user.country"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              label="Postal Code"
              placeholder="ZIP Code"
              v-model="user.postalCode"
            >
            </base-input>
          </b-col>
        </b-row>
      </div> -->

      <hr class="my-4" />
      <!-- Description -->
      <h6 class="heading-small text-muted mb-4">About me</h6>
      <div class="pl-lg-4">
        <b-form-group
          label="About Me"
          label-class="form-control-label"
          class="mb-0"
          label-for="about-form-textaria"
        >
          <!--  <label class="form-control-label">About Me</label> -->
          <b-form-textarea
            rows="4"
            value="A beautiful premium dashboard for BootstrapVue."
            id="about-form-textaria"
            placeholder="A few words about you ..."
          ></b-form-textarea>
        </b-form-group>
      </div>

      <hr class="my-4" />
      <a @click="toggleModal" class="btn btn-info float-right">비밀번호 변경</a>
    </b-form>

    <!-- modal -->
    <b-modal ref="my-modal" hide-footer title="비밀번호 변경">
      <span class="text-danger font-weight-500">
        <small>{{ errorMessage }} </small>
      </span>
      <div class="d-block">
        <base-input
          type="password"
          label="기존 비밀번호"
          v-model="changePw.originpassword"
          placeholder="password"
        >
        </base-input>
        <base-input
          type="password"
          label="변경할 비밀번호"
          v-model="changePw.newpassword"
          placeholder="change password"
        >
        </base-input>
      </div>
      <b-button class="mt-3" variant="outline-danger" block @click="hideModal"
        >확인</b-button
      >
      <b-button class="mt-3" variant="outline-danger" block @click="toggleModal"
        >취소</b-button
      >
    </b-modal>
  </card>
</template>
<script>
import { mapGetters, mapMutations } from "vuex";
import { modifyMember, modifyPassword } from "@/api/memberApi";

export default {
  data() {
    return {
      user: {
        userid: "",
        username: "",
        useremail: "",
        userpwd: "",
        joindate: "",
      },
      changePw: {
        userid: "",
        originpassword: "",
        newpassword: "",
      },
      errorMessage : "",
    };
  },
  computed: {
    ...mapGetters("member", ["checkUserInfo"]),
  },
  mounted() {
    this.user = { ...this.checkUserInfo };
    this.changePw.userid = this.user.userid;
  },
  methods: {
    ...mapMutations("member", ["SET_USER_INFO"]),
    updateProfile() {
      console.log(this.user);
      modifyMember(this.user)
        .then(() => {
          this.SET_USER_INFO(this.user);
        })
        .catch(() => {
          alert("회원정보 수정을 하지못했습니다.");
        });
    },
    showModal() {
      this.$refs["my-modal"].show();
    },
    hideModal() {
      modifyPassword(this.changePw).then((res) => {
        if (res.data == "success") {
          this.$refs["my-modal"].hide();
          alert("변경완료");
        } else {
          this.errorMessage = "정확한 비밀번호를 입력해주세요";
        }
      });
    },
    toggleModal() {
      this.$refs["my-modal"].toggle("#toggle-btn");
    },
  },
};
</script>
<style></style>
